package dadm.hsingh.horoscopoapp.data.settings

import dadm.hsingh.horoscopoapp.data.friend.FriendsRepository
import dadm.hsingh.horoscopoapp.domain.model.Friend
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class SettingsRepositoryImpl
@Inject constructor(private val dataSource: SettingsDataSource,
    private val friendsRepo: FriendsRepository)
    : SettingsRepository
{
    // NAME
    override fun getYourName(): Flow<String> = dataSource.getYourName()

    override suspend fun getYourNameSnapshot(): String {
        var yourName = dataSource.getYourNameSnapshot()
        if (yourName.isBlank()) {
            val userInDBFlow = friendsRepo.getFriendById("USUARIO")
            val userInDBName = userInDBFlow.first()?.name?:"Error al obtener el nombre"
            setYourName(userInDBName)
            yourName = userInDBName
        }
        return yourName
    }

    override suspend fun setYourName(userName: String) {
        dataSource.setYourName(userName)
        val oldFriendFlow = friendsRepo.getFriendById("USUARIO")
        val oldFriend = oldFriendFlow.first()!!
        val updatedFriend = Friend(
            id = oldFriend.id,
            name = userName,
            dateBirth = oldFriend.dateBirth,
            timeBirth = oldFriend.timeBirth,
            placeBirth = oldFriend.placeBirth,
            defaultImage = oldFriend.defaultImage,
            zodiacSign = oldFriend.zodiacSign,
            imageUri = oldFriend.imageUri
        )
        friendsRepo.updateFriend(updatedFriend)
    }

    // LANGUAGE
    override fun getLanguage(): Flow<String> = dataSource.getLanguage()

    override suspend fun getLanguageSnapshot(): String = dataSource.getLanguageSnapshot()

    override suspend fun setLanguage(language: String) {
        dataSource.setLanguage(language)
    }

    // DARK MODE
    override fun getDarkMode(): Flow<Boolean> = dataSource.getDarkMode()

    override suspend fun getDarkModeSnapshot(): Boolean = dataSource.getDarkModeSnapshot()

    override suspend fun setDarkMode(value: Boolean) {
        dataSource.setDarkMode(value)
    }

    // HOROSCOPE NOTIFICATIONS
    override fun getNotificationHoroscope(): Flow<Boolean> = dataSource.getNotificationHoroscope()

    override suspend fun getNotificationHoroscopeSnapshot(): Boolean = dataSource.getNotificationHoroscopeSnapshot()

    override suspend fun setNotificationHoroscope(value: Boolean) {
        dataSource.setNotificationHoroscope(value)
    }

    // BIRTHDAY NOTIFICATIONS
    override fun getNotificationBirthdays(): Flow<Boolean> = dataSource.getNotificationBirthdays()

    override suspend fun getNotificationBirthdaysSnapshot(): Boolean = dataSource.getNotificationBirthdaysSnapshot()

    override suspend fun setNotificationBirthdays(value: Boolean) {
        dataSource.setNotificationBirthdays(value)
    }




}