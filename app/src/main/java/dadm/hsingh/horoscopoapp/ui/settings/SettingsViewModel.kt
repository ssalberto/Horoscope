package dadm.hsingh.horoscopoapp.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.hsingh.horoscopoapp.data.friend.FriendsRepository
import dadm.hsingh.horoscopoapp.data.settings.SettingsRepository
import dadm.hsingh.horoscopoapp.domain.model.Friend
import dadm.hsingh.horoscopoapp.utils.AlarmService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.runBlocking
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject() constructor(
    settingsRepo: SettingsRepository,
    private val friendsRepo: FriendsRepository
) : ViewModel()   {

    val darkMode = settingsRepo.getDarkMode().stateIn(
        scope = viewModelScope,
        initialValue = false,
        started = SharingStarted.WhileSubscribed()
    )

    val notificationReminder = settingsRepo.getNotificationHoroscope().stateIn(
        scope = viewModelScope,
        initialValue = false,
        started = SharingStarted.WhileSubscribed()
    )

    val notificationBirthdays = settingsRepo.getNotificationBirthdays().stateIn(
        scope = viewModelScope,
        initialValue = false,
        started = SharingStarted.WhileSubscribed()
    )

    fun getFriendUser() : Friend {
        val friendUserFlow = friendsRepo.getFriendById("USUARIO")
        lateinit var friendUser : Friend
        runBlocking {
            friendUser = friendUserFlow.first()!!
        }
        return friendUser
    }

    fun setAllBirthdayAlarms(alarmService: AlarmService) {

        val allFriendsFlow = friendsRepo.getAllFriend()
        var allFriends : List<Friend>?
        allFriends = null
        runBlocking {
            allFriends = allFriendsFlow.first()
        }

        for (f in allFriends!!) {
            val name = f.name
            val birthDateLocalDate = f.dateBirth
            // val birthDate = Date.from(birthDateLocalDate.atStartOfDay().toInstant(ZoneOffset.ofHours(0)))
            val thisYearBirthday = Calendar.getInstance().apply {
                timeInMillis = System.currentTimeMillis()
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MONTH, birthDateLocalDate.monthValue - 1)
                set(Calendar.DAY_OF_MONTH, birthDateLocalDate.dayOfMonth)
            }
            var nextBirthday = thisYearBirthday.timeInMillis

            val nextDayToBirthday = Calendar.getInstance().apply{
                timeInMillis = thisYearBirthday.timeInMillis
            }
            nextDayToBirthday.add(Calendar.DAY_OF_YEAR, 1)
            if (nextDayToBirthday.timeInMillis < System.currentTimeMillis()) {
                thisYearBirthday.add(Calendar.YEAR, 1)
                nextBirthday = thisYearBirthday.timeInMillis
            }
            alarmService.setNextBirthdayAlarm(nextBirthday, name)
        }

    }



}