package dadm.hsingh.horoscopoapp.data.horoscope.weekly

import dadm.hsingh.horoscopoapp.domain.model.WeeklyHoroscope

interface WeeklyHoroscopeRepository {
    suspend fun getWeeklyHoroscope( sign : String ): Result<WeeklyHoroscope>

}