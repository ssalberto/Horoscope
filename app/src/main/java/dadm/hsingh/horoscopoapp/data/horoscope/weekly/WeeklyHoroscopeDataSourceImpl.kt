package dadm.hsingh.horoscopoapp.data.horoscope.weekly

import dadm.hsingh.horoscopoapp.data.horoscope.model.WeeklyHoroscopeDto
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

class WeeklyHoroscopeDataSourceImpl @Inject constructor(
    private val retrofit: Retrofit
) : WeeklyHoroscopeDataSource {

    private val retrofitHoroscopeService = retrofit.create(WeeklyHoroscopeRetrofit::class.java)


    override suspend fun getWeeklyHoroscope(sign: String): Response<WeeklyHoroscopeDto> {
        return try {
            retrofitHoroscopeService.getWeeklyHoroscope(sign)
        } catch (e: Exception) {
            Response.error(
                400, // Could be any other code and text, because we are not using it
                ResponseBody.create(MediaType.parse("text/plain"), e.toString())
            )
        }
    }

    interface WeeklyHoroscopeRetrofit {
        @GET("weekly?day=TODAY")
        suspend fun getWeeklyHoroscope(@Query("sign") sign: String): Response<WeeklyHoroscopeDto>
    }
}