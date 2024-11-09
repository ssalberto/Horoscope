package dadm.hsingh.horoscopoapp.utils
import android.Manifest
import android.app.AlarmManager
import android.app.AlarmManager.INTERVAL_DAY
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.ui.MainActivity
import java.util.Calendar

private val CHANEL_ID = "HOROSCOTIFICACION"
private val NOTIFICATION_ID = 856

private const val NOTIFICACCION = "RECORDATORIO_HOROSCOPO"
private const val CUMPLEACCION = "RECORDATORIO_CUMPLEAÑOS"
private const val EXTRA_BIRTHDAY_NAME = "BIRTHDAY_NAME"


// --------- NOTIFICACIONES ---------

fun createNotificationChannel(context: Context) {
        val name = context.getString(R.string.notification_channel_name)
        val channelId = CHANEL_ID
        val descriptionText = context.getString(R.string.notification_channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        val channel = NotificationChannel(channelId, name, importance).apply {
            description = descriptionText
        }

        val nm: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm.createNotificationChannel(channel)
}

fun generateNotificationReminder(context: Context?) {
    if (context == null) return
    val channelId = CHANEL_ID
    val largeIcon = BitmapFactory.decodeResource( // (2)
        context.resources,
        R.drawable.estrella_flor
    )

    val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)


    val notification = NotificationCompat.Builder(context, channelId) // (3)
        .setLargeIcon(largeIcon) // (4)
        .setSmallIcon(R.drawable.estrella_flor) // (5)
        .setContentTitle(context.getString(R.string.reminder)) // (6)
        .setContentText(context.getString(R.string.reminderContent)) // (7)
        .setContentIntent(pendingIntent)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT) // (9)
        .build()

    with(NotificationManagerCompat.from(context)) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return@with
        }
        notify(NOTIFICATION_ID, notification)
    }
}

fun generateNotificationBirthday(context: Context?, friendName: String) {
    if (context == null) return
    val channelId = CHANEL_ID
    val contentTitle = context.getString(R.string.hay_cumple)
    val contextText = context.getString(R.string.recuerda_felicitar) + " " + friendName + "."
    val largeIcon = BitmapFactory.decodeResource( // (2)
        context.resources,
        R.drawable.estrella_flor
    )

    val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)


    val notification = NotificationCompat.Builder(context, channelId) // (3)
        .setLargeIcon(largeIcon) // (4)
        .setSmallIcon(R.drawable.estrella_flor) // (5)
        .setContentTitle(contentTitle) // (6)
        .setContentText(contextText) // (7)
        .setContentIntent(pendingIntent)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT) // (9)
        .build()

    with(NotificationManagerCompat.from(context)) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return@with
        }
        notify(NOTIFICATION_ID, notification)
    }
}



// --------- ALARMAS ---------

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            NOTIFICACCION -> {
                generateNotificationReminder(context)
            }
            CUMPLEACCION -> {
                val name = intent.getStringExtra(EXTRA_BIRTHDAY_NAME)?:"???"
                generateNotificationBirthday(context, name)

                val alarmService = AlarmService(context)
                val nextBirthday = Calendar.getInstance()
                nextBirthday.add(Calendar.YEAR, 1)
                alarmService.setNextBirthdayAlarm(nextBirthday.timeInMillis, name)
            }
        }
    }
}


class AlarmService (private val context : Context) {

    private val alarmManager: AlarmManager? =
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager?
    private var birthdayAlarmNumber = 1


    // Programa una alarma diaria a las 9:00h
    fun setReminderAlarm() {
        val time = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 9)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }
        val alarmIntent = Intent(context, AlarmReceiver::class.java).apply {
            action = NOTIFICACCION
        }
        val pendingIntent: PendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE )
        alarmManager?.let {
            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                time.timeInMillis,
                INTERVAL_DAY,
                pendingIntent
            )
        }
    }


    fun setNextBirthdayAlarm(nextBirthDayInMillis : Long, birthdayName: String) {
        val alarmIntent = Intent(context, AlarmReceiver::class.java).apply {
            action = CUMPLEACCION
            putExtra(EXTRA_BIRTHDAY_NAME, birthdayName)
        }
        val pendingIntent: PendingIntent = PendingIntent.getBroadcast(context, birthdayAlarmNumber, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE )
        birthdayAlarmNumber++
        alarmManager?.let {
            alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                nextBirthDayInMillis,
                pendingIntent
            )
        }
    }

    fun cancelAlarms() {
        val alarmIntent = Intent(context, AlarmReceiver::class.java).apply {
            action = NOTIFICACCION
        }
        val pendingIntent: PendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE )
        alarmManager?.cancel(pendingIntent)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            alarmManager?.cancelAll()
        }
    }


}
