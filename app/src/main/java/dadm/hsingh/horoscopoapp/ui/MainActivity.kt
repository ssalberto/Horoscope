package dadm.hsingh.horoscopoapp.ui

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationBarView
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.ActivityMainBinding
import dadm.hsingh.horoscopoapp.ui.settings.SettingsViewModel
import dadm.hsingh.horoscopoapp.utils.createNotificationChannel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var sharedPreferences: SharedPreferences
    private val settingsViewModel : SettingsViewModel by  viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.SPANISH)
            .build()
        val englishSpanishTranslator = Translation.getClient(options)
        lifecycle.addObserver(englishSpanishTranslator)

        var conditions = DownloadConditions.Builder()
            .requireWifi()
            .build()
        englishSpanishTranslator.downloadModelIfNeeded(conditions)
            .addOnSuccessListener {
                // Model downloaded successfully. Okay to start translating.
                // (Set a flag, unhide the translation UI, etc.)
            }
            .addOnFailureListener { exception ->
                // Model couldn’t be downloaded or other internal error.
                // ...
            }

        val options2 = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.SPANISH)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build()
        val spanishEnglishTranslator = Translation.getClient(options2)
        lifecycle.addObserver(spanishEnglishTranslator)

        spanishEnglishTranslator.downloadModelIfNeeded(conditions)
            .addOnSuccessListener {
                // Model downloaded successfully. Okay to start translating.
                // (Set a flag, unhide the translation UI, etc.)
            }
            .addOnFailureListener { exception ->
                // Model couldn’t be downloaded or other internal error.
                // ...
            }



        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        binding.toolbar.setTitleTextAppearance(this, R.style.ToolbarTitleText)
        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        navController = binding.NavHost.getFragment<NavHostFragment>().navController
        val navigationBarView = binding.bottomNavigationView as NavigationBarView
        navigationBarView.setupWithNavController(navController)
        val appBar = AppBarConfiguration(setOf(
            R.id.profileFragment,
            R.id.horoscopeFragment,
            R.id.compatibilityFragment,
            // R.id.settingsFragment (lo excluimos para que aparezca tenga flecha hacia atrás)
        ))
        setupActionBarWithNavController(navController, appBar)

        sharedPreferences = getSharedPreferences("onBoarding", MODE_PRIVATE)

        // Comprueba si es la primera vez que se ejecuta la aplicación
        val isFirstRun = sharedPreferences.getBoolean("isFirstRun", true)

        if (isFirstRun) {
            val intent = Intent(this, OnBoardingActivity::class.java)
            startActivity(intent)
            finish()
        }

        modoOcuro()
        servicioNotificaciones()
    }

    // Soporte a la flecha hacia atrás en los Settings
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() // || super.onSupportNavigateUp()
    }


    // Activa el modo oscuro si está habilitado
    private fun modoOcuro() {
        // DarkMode. Hemos de activarlo aquí si queremos que se vea al iniciar la app
        lifecycleScope.launch {
            settingsViewModel.darkMode.collect { isDarkModeEnabled ->
                if (isDarkModeEnabled) {
                    // Cambiar al tema oscuro
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    // Cambiar al tema claro
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }
    }

    // Permite las  notificaciones
    private fun servicioNotificaciones() {
        createNotificationChannel(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS),0 )
            }
        }
    }



}
