package dadm.hsingh.horoscopoapp.data.horoscope.model

import android.util.Log
import androidx.compose.ui.text.toUpperCase
import com.squareup.moshi.JsonClass
import dadm.hsingh.horoscopoapp.domain.model.WeeklyHoroscope
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@JsonClass(generateAdapter = true)
data class WeeklyHoroscopeDto(
    val data: WeeklyHoroscopeData,
    val status: Int,
    val success: Boolean
) {
    fun toDomain(): WeeklyHoroscope {
        val parts = data.week.split(" - ")


        try {
            //Primera fecha
            val startDate = stringToLocalDate(parts[0])

            //Segunda fecha
            val endDate = stringToLocalDate(parts[1])

            return WeeklyHoroscope(
                weeklyHoroscopeText = data.horoscope_data,
                week = data.week,
                startingDate = startDate,
                endDate = endDate
            )
        } catch (e: DateTimeParseException) {
            e.message?.let { Log.e("Error al formatear fecha", it) }
        }

        return WeeklyHoroscope(
            weeklyHoroscopeText = data.horoscope_data,
            week = data.week,
            startingDate = LocalDate.now(),
            endDate = LocalDate.now()
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
data class WeeklyHoroscopeData(
    val horoscope_data: String,
    val week: String
)