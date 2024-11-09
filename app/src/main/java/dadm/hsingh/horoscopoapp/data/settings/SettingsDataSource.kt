package dadm.hsingh.horoscopoapp.data.settings

import kotlinx.coroutines.flow.Flow

interface SettingsDataSource {

    // Username functions
    fun getYourName() : Flow<String>
    suspend fun getYourNameSnapshot() : String
    suspend fun setYourName(userName: String)

    // Language functions
    fun getLanguage() : Flow<String>
    suspend fun getLanguageSnapshot() : String
    suspend fun setLanguage(lang: String)

    // Dark mode functions
    fun getDarkMode() : Flow<Boolean>
    suspend fun getDarkModeSnapshot() : Boolean
    suspend fun setDarkMode(value: Boolean)


    // Daily horoscope notification functions
    fun getNotificationHoroscope() : Flow<Boolean>
    suspend fun getNotificationHoroscopeSnapshot() : Boolean
    suspend fun setNotificationHoroscope(value: Boolean)


    // Birthday notifications functions
    fun getNotificationBirthdays() : Flow<Boolean>
    suspend fun getNotificationBirthdaysSnapshot() : Boolean
    suspend fun setNotificationBirthdays(value: Boolean)

}