package dadm.hsingh.horoscopoapp.ui.horoscope.weekly

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FragmentWeeklyBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint

class WeeklyFragment : Fragment(R.layout.fragment_weekly){
    private var _binding : FragmentWeeklyBinding? = null
    private val binding get() = _binding!!

    private val viewModel : WeeklyViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWeeklyBinding.bind(view)

        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.SPANISH)
            .build()

        val englishSpanishTranslator = Translation.getClient(options)
        lifecycle.addObserver(englishSpanishTranslator)

        val options2 = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.SPANISH)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build()

        val spanishEnglishTranslator = Translation.getClient(options2)
        lifecycle.addObserver(spanishEnglishTranslator)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.profile_sign.collect {user ->
                    if (user != null) {
                        viewModel.getWeeklyHoroscope(user.zodiacSign)
                    }

                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.weeklyHoroscope.collect{weeklyHoroscope ->
                    if (weeklyHoroscope != null) {
                        viewModel.getLanguage()
                        viewModel.language.collect{language->
                            if(language.isNotEmpty()){
                                //binding.textViewPeriod.text = viewModel.getDate()
                                if(language == "es"){
                                    englishSpanishTranslator.translate(weeklyHoroscope.weeklyHoroscopeText)
                                        .addOnSuccessListener { translatedText ->
                                            // Translation successful.
                                            binding.textViewParagraph.text = translatedText
                                        }
                                        .addOnFailureListener { exception ->
                                            // Error.
                                            // ...
                                        }
                                }else{
                                    binding.textViewParagraph.text = weeklyHoroscope.weeklyHoroscopeText
                                }
                            }

                        }


                    }
                }
            }
        }


        viewModel.getRanking()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.ranking.collect{ ranking ->
                    if (ranking != null) {
                        viewModel.language.collect{language->
                            val items = ranking.items

                            binding.icon1.setImageResource(ranking.items[0].icon)
                            binding.icon2.setImageResource(ranking.items[1].icon)
                            binding.icon3.setImageResource(ranking.items[2].icon)
                            binding.icon4.setImageResource(ranking.items[3].icon)
                            binding.icon5.setImageResource(ranking.items[4].icon)
                            binding.icon6.setImageResource(ranking.items[5].icon)
                            binding.icon7.setImageResource(ranking.items[6].icon)
                            binding.icon8.setImageResource(ranking.items[7].icon)
                            binding.icon9.setImageResource(ranking.items[8].icon)
                            binding.icon10.setImageResource(ranking.items[9].icon)
                            binding.icon11.setImageResource(ranking.items[10].icon)
                            binding.icon12.setImageResource(ranking.items[11].icon)


                            binding.sign1.text = items[0].sign
                            binding.sign2.text = items[1].sign
                            binding.sign3.text = items[2].sign
                            binding.sign4.text = items[3].sign
                            binding.sign5.text = items[4].sign
                            binding.sign6.text = items[5].sign
                            binding.sign7.text = items[6].sign
                            binding.sign8.text = items[7].sign
                            binding.sign9.text = items[8].sign
                            binding.sign10.text = items[9].sign
                            binding.sign11.text = items[10].sign
                            binding.sign12.text = items[11].sign
                            if(language.isNotEmpty()){
                                if(language == "en"){
                                    spanishEnglishTranslator.translate(ranking.fecha.substring(16))
                                        .addOnSuccessListener { translatedText ->
                                            // Translation successful.
                                            binding.rankFecha.text = translatedText
                                            binding.textViewPeriod.text = translatedText
                                        }
                                        .addOnFailureListener { exception ->
                                            // Error.
                                            // ...
                                        }
                                }else{
                                    binding.rankFecha.text = ranking.fecha.substring(16)
                                    binding.textViewPeriod.text = ranking.fecha.substring(16)
                                }
                            }

                        }

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