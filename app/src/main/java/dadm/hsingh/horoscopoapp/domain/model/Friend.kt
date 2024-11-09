package dadm.hsingh.horoscopoapp.domain.model

import java.time.LocalDate
import java.time.LocalTime

data class Friend(
    val id: String,
    val name: String,
    val dateBirth: LocalDate,
    val timeBirth: LocalTime,
    val placeBirth: String,
    val zodiacSign: String,
    val defaultImage: Int,
    val imageUri : String?
)
