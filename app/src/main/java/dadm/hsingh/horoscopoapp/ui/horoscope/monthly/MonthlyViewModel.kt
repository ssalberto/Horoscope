package dadm.hsingh.horoscopoapp.ui.horoscope.monthly

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.hsingh.horoscopoapp.data.friend.FriendsRepository
import dadm.hsingh.horoscopoapp.data.horoscope.monthly.MonthlyHoroscopeRepository
import dadm.hsingh.horoscopoapp.data.settings.SettingsRepository
import dadm.hsingh.horoscopoapp.domain.model.MonthlyHoroscope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MonthlyViewModel @Inject() constructor(
    private val rep : FriendsRepository,
    private val monthlyRep : MonthlyHoroscopeRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _monthlyHoroscope : MutableStateFlow<MonthlyHoroscope?> = MutableStateFlow(null)
    val monthlyHoroscope get() = _monthlyHoroscope.asStateFlow()

    private val _showError : MutableStateFlow<Throwable?> = MutableStateFlow(null)
    val showError get() = _showError.asStateFlow()

    private val _language : MutableStateFlow<String> = MutableStateFlow("")
    val language get() = _language.asStateFlow()

    val profile_sign = rep.getFriendById("USUARIO")

    fun getMonthlyHoroscope(sign: String) {
        viewModelScope.launch {
            monthlyRep.getMonthlyHoroscope(sign).fold(
                onSuccess = {_monthlyHoroscope.value = it},
                onFailure = {_showError.value = it}
            )
        }
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
    fun getDate() : String{
        val locale = if (language.value == "en") Locale.ENGLISH else Locale("es", "ES")
        val dateFormat = SimpleDateFormat("MMMM dd, yyyy", locale)
        val date = dateFormat.format(Date())
        return date.substring(0, 1).uppercase(Locale.getDefault()) + date.substring(1)
    }
}