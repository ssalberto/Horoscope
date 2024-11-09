package dadm.hsingh.horoscopoapp.data.friend

import androidx.room.Database
import androidx.room.RoomDatabase
import dadm.hsingh.horoscopoapp.data.friend.model.FriendDto


@Database(entities = [FriendDto::class], version = 1)
abstract class FriendsDatabase : RoomDatabase() {
    abstract fun friendsDao(): FriendsDao
}
