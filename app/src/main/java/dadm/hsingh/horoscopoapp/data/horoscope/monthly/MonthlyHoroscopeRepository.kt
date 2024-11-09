package dadm.hsingh.horoscopoapp.data.horoscope.monthly

import dadm.hsingh.horoscopoapp.domain.model.MonthlyHoroscope

interface MonthlyHoroscopeRepository {
    suspend fun getMonthlyHoroscope( sign : String ): Result<MonthlyHoroscope>

}