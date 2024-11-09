package dadm.hsingh.horoscopoapp.data.ranking

import android.util.Log
import dadm.hsingh.horoscopoapp.domain.model.Ranking
import dadm.hsingh.horoscopoapp.domain.model.RankingItem
import dadm.hsingh.horoscopoapp.utils.getIconBySign
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import org.jsoup.Jsoup
import javax.inject.Inject


class RankingDataSourceImpl @Inject constructor(): RankingDataSource {
    override suspend fun getRanking(): Result<Ranking> {

        try {

            val job = CoroutineScope(Dispatchers.IO).async {
                return@async Jsoup.connect("https://www.esperanzagraciaoficial.es/horoscopo-semanal/")
                    .get()

            }

            val document = job.await()
            val items = document.select("div.row.ranking")
            val html = Jsoup.parse(items.html().trimIndent())
            val date = html.select("div.header").text()

            val list: MutableList<RankingItem> = mutableListOf()


            // Recorrer cada elemento del ranking
            val ranking = items.select("div.content")
            ranking.select("ul li").forEachIndexed { index, element ->
                val posicion = element.text().substringBefore(" ")
                val signo = element.text().substringAfter(" ").substringBefore(" ")
                //val enlace = element.select("a").attr("href")
                //val imagen = element.select("img").attr("src")

                val item = RankingItem(posicion.toInt(), getIconBySign(signo), signo)
                list.add(item)

            }

            val rankingDto = Ranking(date, list)
            return Result.success(rankingDto)

        } catch (e: Exception){
            e.printStackTrace() // Imprimir el stack trace para depurar posibles errores
            Log.d("Prueba", e.toString())
            return Result.failure(e)
        }

    }
}