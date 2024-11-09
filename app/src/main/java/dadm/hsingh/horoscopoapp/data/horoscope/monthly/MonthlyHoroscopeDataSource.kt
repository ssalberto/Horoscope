package dadm.hsingh.horoscopoapp.data.horoscope.monthly

import dadm.hsingh.horoscopoapp.data.horoscope.model.MonthlyHoroscopeDto
import retrofit2.Response

interface MonthlyHoroscopeDataSource {
    suspend fun getMonthlyHoroscope( sign : String ): Response<MonthlyHoroscopeDto>

}