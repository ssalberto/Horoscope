package dadm.hsingh.horoscopoapp.data.horoscope.daily

import dadm.hsingh.horoscopoapp.data.horoscope.model.DailyHoroscopeDto
import retrofit2.Response

interface DailyHoroscopeDataSource {
    suspend fun getDailyHoroscope( lang : String ): Response<DailyHoroscopeDto>

}