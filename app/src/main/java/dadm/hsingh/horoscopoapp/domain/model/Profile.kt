package dadm.hsingh.horoscopoapp.domain.model

import java.time.LocalDate
import java.time.LocalTime

data class Profile(
    val name: String,
    val dateBirth: LocalDate,
    val timeBirth: LocalTime,
    val placeBirth: String,
    val zodiacSign: Int
)
