package dadm.hsingh.horoscopoapp.domain.model.ZodiacSigns

import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.domain.model.ZodiacSign

class ZodiacSigns() {
    val aries_es: ZodiacSign = ZodiacSign(
        "Aries",
        "Los nacidos bajo el signo de Aries son enérgicos, valientes y optimistas. Son líderes naturales y les gusta asumir desafíos.",
        listOf("Leo", "Sagitario"),
        "21 de marzo",
        "19 de abril",
        listOf("Energía", "Valentía", "Optimismo"),
        listOf("Impulsividad", "Tendencia a la confrontación"),
        "aries.jpg"
    )

    val taurus_es: ZodiacSign = ZodiacSign(
        "Tauro",
        "Los Tauro son personas prácticas y pacientes. Disfrutan de las cosas simples de la vida y son leales y confiables en sus relaciones.",
        listOf("Virgo", "Capricornio"),
        "20 de abril",
        "20 de mayo",
        listOf("Paciencia", "Lealtad", "Estabilidad"),
        listOf("Terquedad", "Reticencia al cambio"),
        "taurus.jpg"
    )

    val gemini_es: ZodiacSign = ZodiacSign(
        "Géminis",
        "Los Géminis son versátiles, curiosos y sociables. Les gusta aprender y comunicarse, y pueden adaptarse fácilmente a diferentes situaciones.",
        listOf("Libra", "Acuario"),
        "21 de mayo",
        "20 de junio",
        listOf("Versatilidad", "Adaptabilidad", "Intelecto"),
        listOf("Superficialidad", "Inconstancia"),
        "gemini.jpg"
    )

    val cancer_es: ZodiacSign = ZodiacSign(
        "Cáncer",
        "Los Cáncer son sensibles, compasivos y protectores. Valoran la seguridad y la familia, y tienen una gran intuición.",
        listOf("Escorpio", "Piscis"),
        "21 de junio",
        "22 de julio",
        listOf("Sensibilidad", "Compasión", "Intuición"),
        listOf("Sensibilidad excesiva", "Nostalgia"),
        "cancer.jpg"
    )

    val leo_es: ZodiacSign = ZodiacSign(
        "Leo",
        "Los Leo son carismáticos, generosos y apasionados. Les gusta estar en el centro de atención y tienen una fuerte voluntad.",
        listOf("Aries", "Sagitario"),
        "23 de julio",
        "22 de agosto",
        listOf("Carisma", "Generosidad", "Pasión"),
        listOf("Orgullo", "Necesidad de reconocimiento"),
        "leo.jpg"
    )

    val virgo_es: ZodiacSign = ZodiacSign(
        "Virgo",
        "Los Virgo son prácticos, analíticos y trabajadores. Son perfeccionistas y prestan atención a los detalles en todo lo que hacen.",
        listOf("Tauro", "Capricornio"),
        "23 de agosto",
        "22 de septiembre",
        listOf("Practicidad", "Analítica", "Trabajador"),
        listOf("Perfeccionismo excesivo", "Crítica"),
        "virgo.jpg"
    )

    val libra_es: ZodiacSign = ZodiacSign(
        "Libra",
        "Los Libra son diplomáticos, amables y sociables. Valoran la armonía y la justicia, y son excelentes mediadores en conflictos.",
        listOf("Géminis", "Acuario"),
        "23 de septiembre",
        "22 de octubre",
        listOf("Diplomacia", "Amabilidad", "Sociabilidad"),
        listOf("Indecisión", "Evitan los conflictos"),
        "libra.jpg"
    )

    val scorpio_es: ZodiacSign = ZodiacSign(
        "Escorpio",
        "Los Escorpio son apasionados, determinados y misteriosos. Tienen una gran profundidad emocional y son leales en sus relaciones.",
        listOf("Cáncer", "Piscis"),
        "23 de octubre",
        "21 de noviembre",
        listOf("Pasión", "Determinación", "Lealtad"),
        listOf("Celos", "Desconfianza"),
        "scorpio.jpg"
    )

    val sagittarius_es: ZodiacSign = ZodiacSign(
        "Sagitario",
        "Los Sagitario son aventureros, optimistas y sinceros. Les gusta explorar y aprender, y tienen un gran sentido del humor.",
        listOf("Aries", "Leo"),
        "22 de noviembre",
        "21 de diciembre",
        listOf("Aventurero", "Optimista", "Sincero"),
        listOf("Impaciencia", "Tendencia a ser imprudente"),
        "sagittarius.jpg"
    )

    val capricorn_es: ZodiacSign = ZodiacSign(
        "Capricornio",
        "Los Capricornio son responsables, disciplinados y ambiciosos. Tienen una gran determinación para alcanzar sus metas a largo plazo.",
        listOf("Tauro", "Virgo"),
        "22 de diciembre",
        "19 de enero",
        listOf("Responsabilidad", "Disciplina", "Ambición"),
        listOf("Rigidez", "Pueden ser pesimistas"),
        "capricorn.jpg"
    )

    val aquarius_es: ZodiacSign = ZodiacSign(
        "Acuario",
        "Los Acuario son originales, independientes y humanitarios. Tienen una mente abierta y les gusta desafiar las normas establecidas.",
        listOf("Géminis", "Libra"),
        "20 de enero",
        "18 de febrero",
        listOf("Originalidad", "Independencia", "Humanitarismo"),
        listOf("Excentricidad", "Falta de compromiso emocional"),
        "aquarius.jpg"
    )

    val pisces_es: ZodiacSign = ZodiacSign(
        "Piscis",
        "Los Piscis son compasivos, intuitivos y soñadores. Son muy sensibles y pueden absorber fácilmente las energías de su entorno.",
        listOf("Cáncer", "Escorpio"),
        "19 de febrero",
        "20 de marzo",
        listOf("Compasión", "Intuición", "Creatividad"),
        listOf("Tendencia a la evasión", "Sensibilidad excesiva"),
        "pisces.jpg"
    )

    val aries_en: ZodiacSign = ZodiacSign(
        "Aries",
        "Those born under the sign of Aries are energetic, brave, and optimistic. They are natural leaders and enjoy taking on challenges.",
        listOf("Leo", "Sagittarius"),
        "March 21",
        "April 19",
        listOf("Energy", "Courage", "Optimism"),
        listOf("Impulsiveness", "Tendency to confrontation"),
        "aries.jpg"
    )

    val taurus_en: ZodiacSign = ZodiacSign(
        "Taurus",
        "Taurus people are practical and patient. They enjoy the simple things in life and are loyal and reliable in their relationships.",
        listOf("Virgo", "Capricorn"),
        "April 20",
        "May 20",
        listOf("Patience", "Loyalty", "Stability"),
        listOf("Stubbornness", "Resistance to change"),
        "taurus.jpg"
    )

    val gemini_en: ZodiacSign = ZodiacSign(
        "Gemini",
        "Geminis are versatile, curious, and sociable. They enjoy learning and communicating, and can easily adapt to different situations.",
        listOf("Libra", "Aquarius"),
        "May 21",
        "June 20",
        listOf("Versatility", "Adaptability", "Intellect"),
        listOf("Superficiality", "Inconstancy"),
        "gemini.jpg"
    )

    val cancer_en: ZodiacSign = ZodiacSign(
        "Cancer",
        "Cancers are sensitive, compassionate, and protective. They value security and family, and have great intuition.",
        listOf("Scorpio", "Pisces"),
        "June 21",
        "July 22",
        listOf("Sensitivity", "Compassion", "Intuition"),
        listOf("Excessive sensitivity", "Nostalgia"),
        "cancer.jpg"
    )

    val leo_en: ZodiacSign = ZodiacSign(
        "Leo",
        "Leos are charismatic, generous, and passionate. They enjoy being in the spotlight and have a strong will.",
        listOf("Aries", "Sagittarius"),
        "July 23",
        "August 22",
        listOf("Charisma", "Generosity", "Passion"),
        listOf("Pride", "Need for recognition"),
        "leo.jpg"
    )

    val virgo_en: ZodiacSign = ZodiacSign(
        "Virgo",
        "Virgos are practical, analytical, and hardworking. They are perfectionists and pay attention to detail in everything they do.",
        listOf("Taurus", "Capricorn"),
        "August 23",
        "September 22",
        listOf("Practicality", "Analytical", "Hardworking"),
        listOf("Excessive perfectionism", "Criticism"),
        "virgo.jpg"
    )

    val libra_en: ZodiacSign = ZodiacSign(
        "Libra",
        "Libras are diplomatic, kind, and sociable. They value harmony and justice, and are excellent mediators in conflicts.",
        listOf("Gemini", "Aquarius"),
        "September 23",
        "October 22",
        listOf("Diplomacy", "Kindness", "Sociability"),
        listOf("Indecision", "Avoid conflicts"),
        "libra.jpg"
    )

    val scorpio_en: ZodiacSign = ZodiacSign(
        "Scorpio",
        "Scorpios are passionate, determined, and mysterious. They have great emotional depth and are loyal in their relationships.",
        listOf("Cancer", "Pisces"),
        "October 23",
        "November 21",
        listOf("Passion", "Determination", "Loyalty"),
        listOf("Jealousy", "Distrust"),
        "scorpio.jpg"
    )

    val sagittarius_en: ZodiacSign = ZodiacSign(
        "Sagittarius",
        "Sagittarians are adventurous, optimistic, and sincere. They enjoy exploring and learning, and have a great sense of humor.",
        listOf("Aries", "Leo"),
        "November 22",
        "December 21",
        listOf("Adventurous", "Optimistic", "Sincere"),
        listOf("Impatience", "Tendency to be reckless"),
        "sagittarius.jpg"
    )

    val capricorn_en: ZodiacSign = ZodiacSign(
        "Capricorn",
        "Capricorns are responsible, disciplined, and ambitious. They have great determination to achieve their long-term goals.",
        listOf("Taurus", "Virgo"),
        "December 22",
        "January 19",
        listOf("Responsibility", "Discipline", "Ambition"),
        listOf("Rigidity", "Can be pessimistic"),
        "capricorn.jpg"
    )

    val aquarius_en: ZodiacSign = ZodiacSign(
        "Aquarius",
        "Aquarians are original, independent, and humanitarian. They have an open mind and enjoy challenging established norms.",
        listOf("Gemini", "Libra"),
        "January 20",
        "February 18",
        listOf("Originality", "Independence", "Humanitarianism"),
        listOf("Eccentricity", "Lack of emotional commitment"),
        "aquarius.jpg"
    )

    val pisces_en: ZodiacSign = ZodiacSign(
        "Pisces",
        "Pisces are compassionate, intuitive, and dreamy. They are very sensitive and can easily absorb energies from their surroundings.",
        listOf("Cancer", "Scorpio"),
        "February 19",
        "March 20",
        listOf("Compassion", "Intuition", "Creativity"),
        listOf("Tendency to evasion", "Excessive sensitivity"),
        "pisces.jpg"
    )

    fun getSignDrawableIcon(signTmp: String): Int {
        val sign = signTmp.lowercase()
        return when {
            sign.equals("aquarius") || sign.equals("acuario") -> R.drawable.rank_aquarius
            sign.equals("pisces") || sign.equals("piscis") -> R.drawable.rank_pisces
            sign.equals("aries") -> R.drawable.rank_aries
            sign.equals("taurus") || sign.equals("tauro") -> R.drawable.rank_taurus
            sign.equals("gemini") || sign.equals("geminis") || sign.equals("géminis") -> R.drawable.rank_gemini
            sign.equals("cancer") || sign.equals("cancer") || sign.equals("cáncer") -> R.drawable.rank_cancer
            sign.equals("leo") -> R.drawable.rank_leo
            sign.equals("virgo") -> R.drawable.rank_virgo
            sign.equals("libra") -> R.drawable.rank_libra
            sign.equals("scorpio") || sign.equals("escorpio") -> R.drawable.rank_scorpio
            sign.equals("sagittarius") || sign.equals("sagitario") -> R.drawable.rank_sagittarius
            sign.equals("capricorn") || sign.equals("capricornio") -> R.drawable.rank_capricorn
            else -> -1
        }
    }

    fun getSignDrawableImage(signTmp: String): Int {
        val sign = signTmp.lowercase()
        return when {
            sign.equals("aquarius") || sign.equals("acuario") -> R.drawable.aquarius
            sign.equals("pisces") || sign.equals("piscis") -> R.drawable.pisces
            sign.equals("aries") -> R.drawable.aries
            sign.equals("taurus") || sign.equals("tauro") -> R.drawable.taurus
            sign.equals("gemini") || sign.equals("geminis") || sign.equals("géminis") -> R.drawable.gemini
            sign.equals("cancer") || sign.equals("cancer") || sign.equals("cáncer") -> R.drawable.cancer
            sign.equals("leo") -> R.drawable.leo
            sign.equals("virgo") -> R.drawable.virgo
            sign.equals("libra") -> R.drawable.libra
            sign.equals("scorpio") || sign.equals("escorpio") -> R.drawable.scorpio
            sign.equals("sagittarius") || sign.equals("sagitario") -> R.drawable.sagittarius
            sign.equals("capricorn") || sign.equals("capricornio") -> R.drawable.capricorn
            else -> -1
        }
    }

    fun getZodiacSign(sign: String, lang: String): ZodiacSign? {
        //lang puede ser "es" o "en"
        val comp = sign + "-" + lang
        return when {
            comp.equals("aquarius-es") -> aquarius_es
            comp.equals("pisces-es") -> pisces_es
            comp.equals("aries-es") -> aries_es
            comp.equals("taurus-es") -> taurus_es
            comp.equals("gemini-es") -> gemini_es
            comp.equals("cancer-es") -> cancer_es
            comp.equals("leo-es") -> leo_es
            comp.equals("virgo-es") -> virgo_es
            comp.equals("libra-es") -> libra_es
            comp.equals("scorpio-es") -> scorpio_es
            comp.equals("sagittarius-es") -> sagittarius_es
            comp.equals("capricorn-es") -> capricorn_es

            comp.equals("aquarius-en") -> aquarius_en
            comp.equals("pisces-en") -> pisces_en
            comp.equals("aries-en") -> aries_en
            comp.equals("taurus-en") -> taurus_en
            comp.equals("gemini-en") -> gemini_en
            comp.equals("cancer-en") -> cancer_en
            comp.equals("leo-en") -> leo_en
            comp.equals("virgo-en") -> virgo_en
            comp.equals("libra-en") -> libra_en
            comp.equals("scorpio-en") -> scorpio_en
            comp.equals("sagittarius-en") -> sagittarius_en
            comp.equals("capricorn-en") -> capricorn_en
            else -> null
        }
    }

}
