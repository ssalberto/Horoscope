package dadm.hsingh.horoscopoapp.data.horoscope.weekly

import dadm.hsingh.horoscopoapp.data.horoscope.model.WeeklyHoroscopeDto
import retrofit2.Response

interface WeeklyHoroscopeDataSource {
    suspend fun getWeeklyHoroscope( sign : String ): Response<WeeklyHoroscopeDto>

}