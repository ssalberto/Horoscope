package dadm.hsingh.horoscopoapp.domain.model

data class Ranking(
    val fecha: String,
    val items: MutableList<RankingItem> = mutableListOf()
)
