package dadm.hsingh.horoscopoapp.data.horoscope.daily

import dadm.hsingh.horoscopoapp.domain.model.DailyHoroscope

interface DailyHoroscopeRepository {
    suspend fun getDailyHoroscope( sign : String ): Result<DailyHoroscope>

}