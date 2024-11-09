package dadm.hsingh.horoscopoapp.utils

import android.util.Log
import dadm.hsingh.horoscopoapp.R
import java.util.Calendar
import java.util.Date

object SignosZodiacalesImages {
    val ACUARIO = R.drawable.aquarius
    val PISCIS = R.drawable.pisces
    val ARIES = R.drawable.aries
    val TAURO = R.drawable.taurus
    val GEMINIS = R.drawable.gemini
    val CANCER = R.drawable.cancer
    val LEO = R.drawable.leo
    val VIRGO = R.drawable.virgo
    val LIBRA = R.drawable.libra
    val ESCORPIO = R.drawable.scorpio
    val SAGITARIO = R.drawable.sagittarius
    val CAPRICORNIO = R.drawable.capricorn
}

object SignosZodiacales {
    val ACUARIO = "aquarius"
    val PISCIS = "pisces"
    val ARIES = "aries"
    val TAURO = "taurus"
    val GEMINIS = "gemini"
    val CANCER = "cancer"
    val LEO = "leo"
    val VIRGO = "virgo"
    val LIBRA = "libra"
    val ESCORPIO = "scorpio"
    val SAGITARIO = "sagittarius"
    val CAPRICORNIO = "capricorn"
}

fun getZodiacSignImage(birthDate: Date): Int {
    val calendar = Calendar.getInstance()
    calendar.time = birthDate
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    Log.d(month.toString(), "debar")
    Log.d(day.toString(), "debar")

    return when {
        (month == Calendar.JANUARY && day >= 20) || (month == Calendar.FEBRUARY && day <= 18) -> SignosZodiacalesImages.ACUARIO
        (month == Calendar.FEBRUARY && day >= 19) || (month == Calendar.MARCH && day <= 20) -> SignosZodiacalesImages.PISCIS
        (month == Calendar.MARCH && day >= 21) || (month == Calendar.APRIL && day <= 19) -> SignosZodiacalesImages.ARIES
        (month == Calendar.APRIL && day >= 20) || (month == Calendar.MAY && day <= 20) -> SignosZodiacalesImages.TAURO
        (month == Calendar.MAY && day >= 21) || (month == Calendar.JUNE && day <= 20) -> SignosZodiacalesImages.GEMINIS
        (month == Calendar.JUNE && day >= 21) || (month == Calendar.JULY && day <= 22) -> SignosZodiacalesImages.CANCER
        (month == Calendar.JULY && day >= 23) || (month == Calendar.AUGUST && day <= 22) -> SignosZodiacalesImages.LEO
        (month == Calendar.AUGUST && day >= 23) || (month == Calendar.SEPTEMBER && day <= 22) -> SignosZodiacalesImages.VIRGO
        (month == Calendar.SEPTEMBER && day >= 23) || (month == Calendar.OCTOBER && day <= 22) -> SignosZodiacalesImages.LIBRA
        (month == Calendar.OCTOBER && day >= 23) || (month == Calendar.NOVEMBER && day <= 21) -> SignosZodiacalesImages.ESCORPIO
        (month == Calendar.NOVEMBER && day >= 22) || (month == Calendar.DECEMBER && day <= 21) -> SignosZodiacalesImages.SAGITARIO
        else -> SignosZodiacalesImages.CAPRICORNIO
    }
}

fun getZodiacSign(birthDate: Date): String {
    val calendar = Calendar.getInstance()
    calendar.time = birthDate
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    Log.d(month.toString(), "debar")
    Log.d(day.toString(), "debar")

    return when {
        (month == Calendar.JANUARY && day >= 20) || (month == Calendar.FEBRUARY && day <= 18) -> SignosZodiacales.ACUARIO
        (month == Calendar.FEBRUARY && day >= 19) || (month == Calendar.MARCH && day <= 20) -> SignosZodiacales.PISCIS
        (month == Calendar.MARCH && day >= 21) || (month == Calendar.APRIL && day <= 19) -> SignosZodiacales.ARIES
        (month == Calendar.APRIL && day >= 20) || (month == Calendar.MAY && day <= 20) -> SignosZodiacales.TAURO
        (month == Calendar.MAY && day >= 21) || (month == Calendar.JUNE && day <= 20) -> SignosZodiacales.GEMINIS
        (month == Calendar.JUNE && day >= 21) || (month == Calendar.JULY && day <= 22) -> SignosZodiacales.CANCER
        (month == Calendar.JULY && day >= 23) || (month == Calendar.AUGUST && day <= 22) -> SignosZodiacales.LEO
        (month == Calendar.AUGUST && day >= 23) || (month == Calendar.SEPTEMBER && day <= 22) -> SignosZodiacales.VIRGO
        (month == Calendar.SEPTEMBER && day >= 23) || (month == Calendar.OCTOBER && day <= 22) -> SignosZodiacales.LIBRA
        (month == Calendar.OCTOBER && day >= 23) || (month == Calendar.NOVEMBER && day <= 21) -> SignosZodiacales.ESCORPIO
        (month == Calendar.NOVEMBER && day >= 22) || (month == Calendar.DECEMBER && day <= 21) -> SignosZodiacales.SAGITARIO
        else -> SignosZodiacales.CAPRICORNIO
    }
}

public fun getIconBySign(sign : String): Int{
    return when (sign){
        "CAPRICORNIO" -> R.drawable.rank_capricorn
        "LEO" -> R.drawable.rank_leo
        "TAURO" -> R.drawable.rank_taurus
        "SAGITARIO" -> R.drawable.rank_sagittarius
        "ACUARIO" -> R.drawable.rank_aquarius
        "PISCIS" -> R.drawable.rank_pisces
        "GéMINIS" -> R.drawable.rank_gemini
        "VIRGO" -> R.drawable.rank_virgo
        "LIBRA" -> R.drawable.rank_libra
        "CáNCER" -> R.drawable.rank_cancer
        "ESCORPIO" -> R.drawable.rank_scorpio
        "ARIES" -> R.drawable.rank_aries
        else -> -1

    }
}
