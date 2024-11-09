package dadm.hsingh.horoscopoapp.domain.model

import java.time.LocalDate


data class DailyHoroscope(
    val dailyHoroscopeText : String,
    val date: LocalDate,
) : Horoscope(dailyHoroscopeText)
