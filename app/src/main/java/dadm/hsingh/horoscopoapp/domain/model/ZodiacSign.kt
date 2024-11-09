package dadm.hsingh.horoscopoapp.domain.model

data class ZodiacSign(
    val sign: String,
    val description: String,
    val compatibilities: List<String>,
    val initial_date: String,
    val end_date: String,
    val positive_aspects: List<String>,
    val negative_aspects: List<String>,
    val image: String
)
