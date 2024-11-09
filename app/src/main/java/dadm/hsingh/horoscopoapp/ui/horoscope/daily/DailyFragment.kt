package dadm.hsingh.horoscopoapp.ui.horoscope.daily

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.room.util.newStringBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FragmentDailyBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DailyFragment : Fragment(R.layout.fragment_daily){
    private var _binding : FragmentDailyBinding? = null
    private val binding get() = _binding!!

    private val viewModel : DailyViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDailyBinding.bind(view)


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
                        viewModel.getDailyHoroscope(user.zodiacSign)
                    }

                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.dailyHoroscope.collect{dailyHoroscope ->
                    if (dailyHoroscope != null) {
                        viewModel.getLanguage()
                        viewModel.language.collect{language->
                            if(language.isNotEmpty()){
                                binding.textViewTitle.text = viewModel.getDate()
                                if(language == "es"){
                                    englishSpanishTranslator.translate(dailyHoroscope.dailyHoroscopeText)
                                        .addOnSuccessListener { translatedText ->
                                            // Translation successful.
                                            binding.textViewParagraph.text = translatedText

                                        }
                                        .addOnFailureListener { exception ->
                                            // Error.
                                            // ...
                                        }
                                }else{
                                    binding.textViewParagraph.text = dailyHoroscope.dailyHoroscopeText
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