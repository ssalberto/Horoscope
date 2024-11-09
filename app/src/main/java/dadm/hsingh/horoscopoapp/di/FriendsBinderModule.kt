package dadm.hsingh.horoscopoapp.di

import dadm.hsingh.horoscopoapp.data.friend.FriendsDataSource
import dadm.hsingh.horoscopoapp.data.friend.FriendsDataSourceImpl
import dadm.hsingh.horoscopoapp.data.friend.FriendsRepository
import dadm.hsingh.horoscopoapp.data.friend.FriendsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FriendsBinderModule {

    @Binds
    abstract fun bindFriendsDataSource(impl: FriendsDataSourceImpl): FriendsDataSource

    @Binds
    abstract fun bindFriendsRepository(impl: FriendsRepositoryImpl): FriendsRepository

}
