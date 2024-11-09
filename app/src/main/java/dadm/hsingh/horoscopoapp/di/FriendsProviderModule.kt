package dadm.hsingh.horoscopoapp.di

import android.content.Context
import androidx.room.Room
import dadm.hsingh.horoscopoapp.data.friend.FriendsContract
import dadm.hsingh.horoscopoapp.data.friend.FriendsDao
import dadm.hsingh.horoscopoapp.data.friend.FriendsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FriendsProviderModule {

    @Provides
    @Singleton
    fun provideFriendsDatabase(@ApplicationContext context: Context): FriendsDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            FriendsDatabase::class.java,
            FriendsContract.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavouritesDao(friendsDatabase: FriendsDatabase): FriendsDao {
        return friendsDatabase.friendsDao()
    }

}