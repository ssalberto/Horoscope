package dadm.hsingh.horoscopoapp.data.friend

import dadm.hsingh.horoscopoapp.domain.model.Friend
import kotlinx.coroutines.flow.Flow

interface FriendsRepository {
    suspend fun addFriend(friend: Friend)
    suspend fun deleteFriend(friend: Friend)
    fun getAllFriend(): Flow<List<Friend>>
    fun getFriendById(id: String): Flow<Friend?>
    suspend fun deleteAllFriend()
    suspend fun updateFriend(friend: Friend)
}