package dadm.hsingh.horoscopoapp.data.ranking

import dadm.hsingh.horoscopoapp.data.horoscope.ConnectivityChecker
import dadm.hsingh.horoscopoapp.data.horoscope.daily.DailyHoroscopeDataSource
import dadm.hsingh.horoscopoapp.domain.model.Ranking
import dadm.hsingh.horoscopoapp.utils.NoInternetException
import javax.inject.Inject

class RankingRepositoryImpl @Inject constructor(
    private val dataSource: RankingDataSource,
    private val checker: ConnectivityChecker
) : RankingRepository {
    override suspend fun getRanking(): Result<Ranking> {
        return if (checker.isConnectionAvailable()){
            dataSource.getRanking()
        }else{
            Result.failure(NoInternetException("Internet Connection Failed"))
        }
    }
}