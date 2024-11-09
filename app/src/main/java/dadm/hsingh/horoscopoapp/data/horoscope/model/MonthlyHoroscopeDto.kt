package dadm.hsingh.horoscopoapp.data.horoscope.model

import android.util.Log
import com.squareup.moshi.JsonClass
import dadm.hsingh.horoscopoapp.domain.model.MonthlyHoroscope
import java.time.Month
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@JsonClass(generateAdapter = true)
data class MonthlyHoroscopeDto(
    val data: MonthlyHoroscopeData,
    val status: Int,
    val success: Boolean
) {
    fun toDomain(): MonthlyHoroscope {
        val month = -1
        val year = -1
        try {
            val month = Month.valueOf(data.month.split(" ")[0].uppercase())
            val year = data.month.split(" ")[1].toInt()

            Log.d("mes formateado", month.toString())
            Log.d("año formateado", year.toString())
        } catch (e: Exception) {
            println("Error al parsear la fecha: ${e.message}")
        }

        val standoutDaysList = data.standout_days.split(",").map { it.trim().toInt() }
        val challengingDaysList = data.standout_days.split(",").map { it.trim().toInt() }

        return MonthlyHoroscope(
            monthlyHoroscopeText = data.horoscope_data,
            month = month,
            year = year,
            standoutDays = standoutDaysList.toIntArray(),
            challengingDays = challengingDaysList.toIntArray()
        )
    }
}

@JsonClass(generateAdapter = true)
data class MonthlyHoroscopeData(
    val challenging_days: String,
    val horoscope_data: String,
    val month: String,
    val standout_days: String
)