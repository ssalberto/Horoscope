package dadm.hsingh.horoscopoapp.data.friend

import dadm.hsingh.horoscopoapp.data.friend.model.toDatabaseDto
import dadm.hsingh.horoscopoapp.data.friend.model.toDomain
import dadm.hsingh.horoscopoapp.domain.model.Friend
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FriendsRepositoryImpl@Inject constructor(
    private val dataSource: FriendsDataSource
) : FriendsRepository{
    override suspend fun addFriend(friend: Friend) {
        dataSource.addFriend(friend.toDatabaseDto())
    }

    override suspend fun deleteFriend(friend: Friend) {
        dataSource.deleteFriend(friend.toDatabaseDto())
    }

    override fun getAllFriend(): Flow<List<Friend>> {
        return dataSource.getAllFriend().map { list -> list.map { it.toDomain() } }
    }

    override fun getFriendById(id: String): Flow<Friend?> {
        return dataSource.getFriendById(id).map { it?.toDomain() }
    }

    override suspend fun deleteAllFriend() {
        dataSource.deleteAllFriend()
    }

    override suspend fun updateFriend(friend: Friend) {
        dataSource.updateFriend(friend.toDatabaseDto())
    }

}