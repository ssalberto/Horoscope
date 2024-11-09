package dadm.hsingh.horoscopoapp.data.horoscope.monthly

import dadm.hsingh.horoscopoapp.data.horoscope.model.MonthlyHoroscopeDto
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

class MonthlyHoroscopeDataSourceImpl @Inject constructor(
    private val retrofit: Retrofit
) : MonthlyHoroscopeDataSource {

    private val retrofitHoroscopeService = retrofit.create(MonthlyHoroscopeRetrofit::class.java)

    override suspend fun getMonthlyHoroscope(sign: String): Response<MonthlyHoroscopeDto> {
        return try {
            retrofitHoroscopeService.getMonthlyHoroscope(sign)
        } catch (e: Exception) {
            Response.error(
                400, // Could be any other code and text, because we are not using it
                ResponseBody.create(MediaType.parse("text/plain"), e.toString())
            )
        }
    }

    interface MonthlyHoroscopeRetrofit {
        @GET("monthly?day=TODAY")
        suspend fun getMonthlyHoroscope(@Query("sign") sign: String): Response<MonthlyHoroscopeDto>
    }
}