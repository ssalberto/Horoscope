package dadm.hsingh.horoscopoapp.data.ranking

import dadm.hsingh.horoscopoapp.domain.model.Ranking

interface RankingDataSource {

    suspend fun getRanking(): Result<Ranking>

}