package dadm.hsingh.horoscopoapp.ui.horoscope.monthly

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FragmentMonthlyBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.Month

@AndroidEntryPoint

class MonthlyFragment : Fragment(R.layout.fragment_monthly){
    private var _binding : FragmentMonthlyBinding? = null
    private val binding get() = _binding!!

    private val viewModel : MonthlyViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMonthlyBinding.bind(view)
        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.SPANISH)
            .build()

        val englishSpanishTranslator = Translation.getClient(options)
        lifecycle.addObserver(englishSpanishTranslator)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.profile_sign.collect {user ->
                    if (user != null) {
                        viewModel.getMonthlyHoroscope(user.zodiacSign)
                    }

                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.monthlyHoroscope.collect{monthlyHoroscope ->
                    if (monthlyHoroscope != null) {
                        viewModel.getLanguage()
                        viewModel.language.collect { language ->
                            if (language.isNotEmpty()) {
                                binding.textViewTitle.text = viewModel.getDate().split(" ")[0]
                                val text1 =  "Days ${monthlyHoroscope.challengingDays[0]}, ${monthlyHoroscope.challengingDays[1]} and ${monthlyHoroscope.challengingDays[2]} are considered challenging for you. During these days, you could face obstacles or situations that require extra effort. You may have to overcome difficulties or make important decisions. Stay alert and be prepared to meet challenges with determination1"
                                val text2 = "Days ${monthlyHoroscope.standoutDays[0]}, ${monthlyHoroscope.standoutDays[1]} and ${monthlyHoroscope.standoutDays[2]} are especially significant for you this month. During these days, you can expect remarkable experiences or moments of importance. It may be a period when you feel inspired, make significant breakthroughs, or encounter exceptional opportunities. Make the most of these exceptional days."

                                val desafio = "Challenging Days"
                                val destacado = "Highlighted Days"

                                if (language == "es") {
                                    englishSpanishTranslator.translate(monthlyHoroscope.monthlyHoroscopeText)
                                        .addOnSuccessListener { translatedText ->
                                            // Translation successful.
                                            binding.textViewParagraph.text = translatedText
                                        }
                                        .addOnFailureListener { exception ->
                                            // Error.
                                            // ...
                                        }

                                    englishSpanishTranslator.translate(text1)
                                        .addOnSuccessListener { translatedText ->
                                            // Translation successful.
                                            binding.desafiante.text = translatedText
                                        }
                                        .addOnFailureListener { exception ->
                                            // Error.
                                            // ...
                                        }

                                    englishSpanishTranslator.translate(text2)
                                        .addOnSuccessListener { translatedText ->
                                            // Translation successful.
                                            binding.destacado.text = translatedText
                                        }
                                        .addOnFailureListener { exception ->
                                            // Error.
                                            // ...
                                        }

                                    binding.desafianteTitulo.text = "Dias Desafiantes"


                                    binding.destacadoTitulo.text = "Dias Destacados"


                                }
                                else {
                                    binding.textViewParagraph.text =
                                        monthlyHoroscope.monthlyHoroscopeText

                                    binding.desafiante.text = text1
                                    binding.destacado.text = text2

                                    binding.desafianteTitulo.text = desafio
                                    binding.destacadoTitulo.text = destacado
                                }
                            }
                        }
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.showError.collect{ show ->
                    if (show != null){
                        Snackbar.make(binding.root, show.toString(), Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}