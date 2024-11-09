package dadm.hsingh.horoscopoapp.data.horoscope.monthly

import dadm.hsingh.horoscopoapp.data.horoscope.ConnectivityChecker
import dadm.hsingh.horoscopoapp.data.horoscope.model.toMonthlyDomain
import dadm.hsingh.horoscopoapp.domain.model.MonthlyHoroscope
import dadm.hsingh.horoscopoapp.utils.NoInternetException
import javax.inject.Inject

class MonthlyHoroscopeRepositoryImpl @Inject constructor(
    private val dataSource: MonthlyHoroscopeDataSource,
    private val checker: ConnectivityChecker
) : MonthlyHoroscopeRepository {

    private lateinit var sign: String

    init {
        //Obtener el valor seleccionado
        sign = "leo"
    }

    override suspend fun getMonthlyHoroscope(sign: String): Result<MonthlyHoroscope> {
        if (checker.isConnectionAvailable()) {
            return dataSource.getMonthlyHoroscope(sign).toMonthlyDomain()
        } else {
            return Result.failure(NoInternetException("Internet Connection Failed"))
        }
    }

}
