package dadm.hsingh.horoscopoapp.domain.model

import java.time.LocalDate

data class WeeklyHoroscope(
    val weeklyHoroscopeText : String,
    val week: String,
    val startingDate: LocalDate,
    val endDate: LocalDate
    ) : Horoscope(weeklyHoroscopeText)
