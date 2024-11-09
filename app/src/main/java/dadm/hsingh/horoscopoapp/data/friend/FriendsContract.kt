package dadm.hsingh.horoscopoapp.data.friend

object FriendsContract {
    const val DATABASE_NAME = "friends.db"

    object FriendsTable {
        const val TABLE_NAME = "friends"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_DATEBIRTH = "dateBirth"
        const val COLUMN_TIMEBIRTH = "timeBirth"
        const val COLUMN_PLACEBIRTH = "placeBirth"
        const val COLUMN_ZODIACSIGN = "zodiacSign"
        const val COLUMN_DEFAULT_IMAGE = "defaultImage"
        const val COLUMN_IMAGE_URI = "imageUri"
    }
}