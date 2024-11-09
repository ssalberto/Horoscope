package dadm.hsingh.horoscopoapp.data.horoscope.daily

import dadm.hsingh.horoscopoapp.data.horoscope.ConnectivityChecker
import dadm.hsingh.horoscopoapp.data.horoscope.model.toDailyDomain
import dadm.hsingh.horoscopoapp.data.settings.SettingsRepository
import dadm.hsingh.horoscopoapp.domain.model.DailyHoroscope
import dadm.hsingh.horoscopoapp.utils.NoInternetException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class DailyHoroscopeRepositoryImpl @Inject constructor(
    private val dataSource: DailyHoroscopeDataSource,
    private val checker: ConnectivityChecker,
    private val settingsRepo: SettingsRepository
) : DailyHoroscopeRepository{

    private lateinit var langCode: String
    init {
        CoroutineScope(SupervisorJob()).launch {
            settingsRepo.getLanguage().collect { languageCode ->
                langCode = languageCode.ifEmpty{ "en" }
            }
        }
    }

    override suspend fun getDailyHoroscope(sign: String): Result<DailyHoroscope> {
        if (checker.isConnectionAvailable()) {
            var dailyHoroscope = dataSource.getDailyHoroscope(sign).toDailyDomain()
            if (dailyHoroscope.isSuccess) {
                if (langCode == "eng") {
                    return dailyHoroscope
                } else {
                    return dailyHoroscope
                }
            } else {
                return dailyHoroscope
            }

        } else {
            return Result.failure(NoInternetException("Internet Connection Failed"))
        }
    }

}