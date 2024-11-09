package dadm.hsingh.horoscopoapp.data.friend

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dadm.hsingh.horoscopoapp.data.friend.model.FriendDto
import kotlinx.coroutines.flow.Flow

@Dao
interface FriendsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFriend(friend: FriendDto)

    @Delete
    suspend fun deleteFriend(friend: FriendDto)

    @Query("SELECT * FROM ${FriendsContract.FriendsTable.TABLE_NAME}")
    fun getAllFriend(): Flow<List<FriendDto>>

    @Query("SELECT * FROM ${FriendsContract.FriendsTable.TABLE_NAME} WHERE ${FriendsContract.FriendsTable.COLUMN_ID} = :id")
    fun getFriendById(id: String): Flow<FriendDto?>

    @Query("DELETE FROM ${FriendsContract.FriendsTable.TABLE_NAME}")
    suspend fun deleteAllFriends()
    @Update
    suspend fun updateFriend(friend: FriendDto)
}