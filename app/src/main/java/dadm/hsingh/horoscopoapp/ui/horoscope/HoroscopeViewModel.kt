package dadm.hsingh.horoscopoapp.ui.horoscope

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.hsingh.horoscopoapp.data.friend.FriendsRepository
import dadm.hsingh.horoscopoapp.data.horoscope.weekly.WeeklyHoroscopeRepository
import dadm.hsingh.horoscopoapp.data.settings.SettingsRepository
import dadm.hsingh.horoscopoapp.domain.model.Friend
import dadm.hsingh.horoscopoapp.domain.model.ZodiacSign
import dadm.hsingh.horoscopoapp.domain.model.ZodiacSigns.ZodiacSigns
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HoroscopeViewModel @Inject() constructor(
    private val rep: FriendsRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val zodiacSigns = ZodiacSigns()
    val profile_sign = rep.getFriendById("USUARIO")


    private val _language : MutableStateFlow<String> = MutableStateFlow("")
    val language get() = _language.asStateFlow()



    suspend fun getProfile(): Friend? {
        return profile_sign.first()
    }
    fun getZodiacSign(sign: String, lang: String): ZodiacSign? {
        return zodiacSigns.getZodiacSign(sign, lang)
    }

    fun getSignDrawable(sign: String): Int {
        return zodiacSigns.getSignDrawableIcon(sign)
    }

    fun getSignDrawableImage(sign: String): Int {
        return zodiacSigns.getSignDrawableImage(sign)
    }

    fun getLanguage() {
        viewModelScope.launch {
            settingsRepository.getLanguage().collect { languageCode ->
                _language.update {
                    languageCode.ifEmpty{ "en" }
                }
            }
        }
    }
}