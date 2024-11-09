package dadm.hsingh.horoscopoapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import dadm.hsingh.horoscopoapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_on_boarding)
    }
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        // Deja este método vacío para deshabilitar la funcionalidad de la flecha hacia atrás
    }
}