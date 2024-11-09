package dadm.hsingh.horoscopoapp.ui.onboarding

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.hsingh.horoscopoapp.data.friend.FriendsRepository
import dadm.hsingh.horoscopoapp.domain.model.Friend
import dadm.hsingh.horoscopoapp.utils.getZodiacSign
import dadm.hsingh.horoscopoapp.utils.getZodiacSignImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val friendsRepo : FriendsRepository
): ViewModel() {
    private val _name = MutableStateFlow<String?>(null)
    val name = _name.asStateFlow()

    private val _birthDate = MutableStateFlow<LocalDate?>(null)
    val birthDate = _birthDate.asStateFlow()

    private val _birthTime = MutableStateFlow<LocalTime?>(null)
    val birthTime = _birthTime.asStateFlow()

    private val _birthPlace = MutableStateFlow<String?>(null)
    val birthPlace = _birthPlace.asStateFlow()

    private val _image = MutableStateFlow<String?>(null)
    val image = _image.asStateFlow()

    fun setName(name: String) {
        _name.update { name }
    }

    fun setBirthDate(birthDate: LocalDate) {
        _birthDate.update { birthDate }
    }

    fun setBirthTime(birthTime: LocalTime) {
        _birthTime.update{ birthTime }
    }

    fun setBirthPlace(birthPlace: String) {
        _birthPlace.update { birthPlace }
    }
    fun setImageUri(uri : String){
        _image.update { uri }
    }


    fun checkRequiredFields(): Boolean {
        Log.d("check value birthDate", _birthDate.value.toString())
        return  _birthDate.value  != null &&
                _birthTime.value != null &&
                !_name.value.isNullOrBlank() &&
                !_birthPlace.value.isNullOrBlank()
    }

    fun saveUser() {
        val zodiacDate: Date = Date.from(_birthDate.value!!.atStartOfDay().toInstant(ZoneOffset.ofHours(0)))
        val user = Friend(
            id = "USUARIO",
            name = _name.value!!,
            dateBirth = _birthDate.value!!,
            timeBirth = _birthTime.value!!,
            placeBirth = _birthPlace.value!!,
            defaultImage = getZodiacSignImage(zodiacDate),
            zodiacSign = getZodiacSign(zodiacDate),
            imageUri = _image.value
        )
        viewModelScope.launch {
            friendsRepo.addFriend(user)
        }

    }
}