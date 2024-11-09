package dadm.hsingh.horoscopoapp.domain.model

data class MonthlyHoroscope(
    val monthlyHoroscopeText : String,
    val month: Int,
    val year: Int,
    val challengingDays: IntArray ,
    val standoutDays: IntArray
) : Horoscope(monthlyHoroscopeText)

/**
  Challenging Days (Días Desafiantes):
  Los días 4, 21 y 30 se consideran desafiantes para ti.
  Durante estos días, podrías enfrentar obstáculos o situaciones que requieren esfuerzo adicional.
  Es posible que debas superar dificultades o tomar decisiones importantes.
  Mantente alerta y prepárate para enfrentar los desafíos con determinación1.

  Standout Days (Días Destacados):
  Los días 8, 15 y 17 son especialmente significativos para ti este mes.
  Durante estos días, puedes esperar experiencias notables o momentos de importancia.
  Puede ser un período en el que te sientas inspirado, logres avances significativos o encuentres
  oportunidades excepcionales. ¡Aprovecha al máximo estos días destacados.
 */