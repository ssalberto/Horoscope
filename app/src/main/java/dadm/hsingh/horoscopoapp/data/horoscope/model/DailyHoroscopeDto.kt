package dadm.hsingh.horoscopoapp.data.horoscope.model

import android.util.Log
import com.squareup.moshi.JsonClass
import dadm.hsingh.horoscopoapp.domain.model.DailyHoroscope
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@JsonClass(generateAdapter = true)
data class DailyHoroscopeDto(
    val data : DailyHoroscopeData,
    val status: Int,
    val success: Boolean
){
    fun toDomain(): DailyHoroscope {
        try {
            val date = stringToLocalDate(data.date)
            Log.d("fecha formateada", date.toString())

            return DailyHoroscope(
                dailyHoroscopeText = data.horoscope_data,
                date = date
            )
        } catch (e: DateTimeParseException) {
            println("Error al parsear la fecha: ${e.message}")
        }
        return DailyHoroscope(
            dailyHoroscopeText = data.horoscope_data,
            date = LocalDate.now()
        )
    }

    fun stringToLocalDate(string: String): LocalDate{
        //Recibimos la fecha con forma "MMM dd, AAAA"
        val split = string.split(" ")
        val month: Month = Month.valueOf(split[0].uppercase())
        val day: Int = split[1].dropLast(1).toInt()
        val year: Int = split[2].toInt()
        return LocalDate.of(year, month, day)
    }
}

@JsonClass(generateAdapter = true)
data class DailyHoroscopeData(
    val date: String,
    val horoscope_data: String
)