package dadm.hsingh.horoscopoapp.data.horoscope.model

import retrofit2.Response
import java.io.IOException

fun Response<DailyHoroscopeDto>.toDailyDomain() =
    if (isSuccessful) Result.success((body() as DailyHoroscopeDto).toDomain())
    else Result.failure(IOException())

fun Response<WeeklyHoroscopeDto>.toWeeklyDomain() =
    if (isSuccessful) Result.success((body() as WeeklyHoroscopeDto).toDomain())
    else Result.failure(IOException())

fun Response<MonthlyHoroscopeDto>.toMonthlyDomain() =
    if (isSuccessful) Result.success((body() as MonthlyHoroscopeDto).toDomain())
    else Result.failure(IOException())
