package dadm.hsingh.horoscopoapp.data.friend.model

import dadm.hsingh.horoscopoapp.domain.model.Friend
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

fun FriendDto.toDomain(): Friend {
    val dateBirthLocalDate = Instant.ofEpochMilli(this.dateBirth.time)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
    val timeBirthLocalTime = Instant.ofEpochMilli(this.timeBirth.time)
        .atZone(ZoneId.systemDefault())
        .toLocalTime()

    return Friend(
        id = this.id,
        name = this.name,
        dateBirth = dateBirthLocalDate,
        timeBirth = timeBirthLocalTime,
        placeBirth = this.placeBirth,
        zodiacSign = this.zodiacSign,
        defaultImage = this.defaultImage,
        imageUri = this.imageUri
    )
}
fun Friend.toDatabaseDto(): FriendDto {
    val dateBirthDate = Date.from(this.dateBirth.atStartOfDay()
        .atZone(ZoneId.systemDefault())
        .toInstant())
    val timeBirthDate = Date.from(this.timeBirth.atDate(LocalDate.ofEpochDay(0))
        .atZone(ZoneId.systemDefault())
        .toInstant())

    return FriendDto(
        id = this.id,
        name = this.name,
        dateBirth = dateBirthDate,
        timeBirth = timeBirthDate,
        placeBirth = this.placeBirth,
        zodiacSign = this.zodiacSign,
        defaultImage = this.defaultImage,
        imageUri = this.imageUri
    )
}