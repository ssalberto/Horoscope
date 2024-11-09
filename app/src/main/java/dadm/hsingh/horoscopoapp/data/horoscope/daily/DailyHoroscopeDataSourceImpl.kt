package dadm.hsingh.horoscopoapp.data.horoscope.daily

import dadm.hsingh.horoscopoapp.data.horoscope.model.DailyHoroscopeDto
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

class DailyHoroscopeDataSourceImpl @Inject constructor(
    private val retrofit: Retrofit
) : DailyHoroscopeDataSource {


    private val retrofitHoroscopeService = retrofit.create(DailyHoroscopeRetrofit::class.java)
    override suspend fun getDailyHoroscope(sign: String): Response<DailyHoroscopeDto> {
        return try {
            retrofitHoroscopeService.getDailyHoroscope(sign)
        } catch (e: Exception) {
            Response.error(
                400, // Could be any other code and text, because we are not using it
                ResponseBody.create(MediaType.parse("text/plain"), e.toString())
            )
        }
    }

    interface DailyHoroscopeRetrofit {
        @GET("daily?day=TODAY")
        suspend fun getDailyHoroscope(@Query("sign") sign: String): Response<DailyHoroscopeDto>
    }
}