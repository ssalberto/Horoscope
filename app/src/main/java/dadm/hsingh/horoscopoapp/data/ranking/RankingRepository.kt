package dadm.hsingh.horoscopoapp.data.ranking

import dadm.hsingh.horoscopoapp.domain.model.Ranking

interface RankingRepository {

    suspend fun getRanking(): Result<Ranking>

}