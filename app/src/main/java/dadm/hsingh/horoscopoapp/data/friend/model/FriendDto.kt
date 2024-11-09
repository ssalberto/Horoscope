package dadm.hsingh.horoscopoapp.data.friend.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import dadm.hsingh.horoscopoapp.data.friend.FriendsContract
import dadm.hsingh.horoscopoapp.utils.DateConverter
import java.util.Date


@Entity(tableName = FriendsContract.FriendsTable.TABLE_NAME)
@TypeConverters(DateConverter::class)
data class FriendDto (
    @PrimaryKey @ColumnInfo(name = FriendsContract.FriendsTable.COLUMN_ID) val id: String,
    @ColumnInfo(name = FriendsContract.FriendsTable.COLUMN_NAME) val name: String,
    @ColumnInfo(name = FriendsContract.FriendsTable.COLUMN_DATEBIRTH) val dateBirth: Date,
    @ColumnInfo(name = FriendsContract.FriendsTable.COLUMN_TIMEBIRTH) val timeBirth: Date,
    @ColumnInfo(name = FriendsContract.FriendsTable.COLUMN_PLACEBIRTH) val placeBirth: String,
    @ColumnInfo(name = FriendsContract.FriendsTable.COLUMN_ZODIACSIGN) val zodiacSign: String,
    @ColumnInfo(name = FriendsContract.FriendsTable.COLUMN_DEFAULT_IMAGE) val defaultImage: Int,
    @ColumnInfo(name = FriendsContract.FriendsTable.COLUMN_IMAGE_URI) val imageUri: String?
    )

