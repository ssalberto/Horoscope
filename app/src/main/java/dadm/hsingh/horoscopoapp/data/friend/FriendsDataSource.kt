package dadm.hsingh.horoscopoapp.data.friend

import dadm.hsingh.horoscopoapp.data.friend.model.FriendDto
import kotlinx.coroutines.flow.Flow

interface FriendsDataSource {
    suspend fun addFriend(friend: FriendDto)
    suspend fun deleteFriend(friend: FriendDto)
    fun getAllFriend(): Flow<List<FriendDto>>
    fun getFriendById(id: String): Flow<FriendDto?>
    suspend fun deleteAllFriend()
    suspend fun updateFriend(friend: FriendDto)
}