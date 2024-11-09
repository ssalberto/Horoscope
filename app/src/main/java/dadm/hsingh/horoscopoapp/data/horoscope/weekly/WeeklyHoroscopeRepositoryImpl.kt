package dadm.hsingh.horoscopoapp.data.horoscope.weekly

import dadm.hsingh.horoscopoapp.data.horoscope.ConnectivityChecker
import dadm.hsingh.horoscopoapp.data.horoscope.model.toWeeklyDomain
import dadm.hsingh.horoscopoapp.domain.model.WeeklyHoroscope
import dadm.hsingh.horoscopoapp.utils.NoInternetException
import javax.inject.Inject

class WeeklyHoroscopeRepositoryImpl @Inject constructor(
    private val dataSource: WeeklyHoroscopeDataSource,
    private val checker: ConnectivityChecker
) : WeeklyHoroscopeRepository {

    private lateinit var sign: String

    init {
        //Obtener el valor seleccionado
        sign = "leo"
    }

    override suspend fun getWeeklyHoroscope(sign: String): Result<WeeklyHoroscope> {
        if (checker.isConnectionAvailable()) {
            return dataSource.getWeeklyHoroscope(sign).toWeeklyDomain()
        } else {
            return Result.failure(NoInternetException("Internet Connection Failed"))
        }
    }

}
