package dadm.hsingh.horoscopoapp.ui.profile.userDescription

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FragmentDescriptionBinding
import dadm.hsingh.horoscopoapp.domain.model.Friend
import dadm.hsingh.horoscopoapp.domain.model.ZodiacSign
import dadm.hsingh.horoscopoapp.ui.horoscope.monthly.MonthlyViewModel
import dadm.hsingh.horoscopoapp.ui.profile.ProfileViewModel
import dadm.hsingh.horoscopoapp.utils.getZodiacSign
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class userDescriptionFragment : Fragment(R.layout.fragment_description){

    private var _binding : FragmentDescriptionBinding? = null
    private val binding get() = _binding!!


    private val viewModel : ProfileViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDescriptionBinding.bind(view)



        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getLanguage()
                val signTxt  = viewModel.getProfile()?.zodiacSign
                viewModel.language.collect {lang ->


                    val zodiacSign: ZodiacSign? = signTxt?.let { viewModel.getZodiacSign(it, lang) }

                    try {
                        if (zodiacSign != null) {
                            binding.apply {
                                sign.text = zodiacSign.sign
                                signIcon.setImageResource(viewModel.getSignDrawable(signTxt))
                                signDescr.text = zodiacSign.description
                                dateRange.text =
                                    zodiacSign.initial_date + "   -   " + zodiacSign.end_date

                                val comp1 = zodiacSign.compatibilities[0]
                                signComp1.text = comp1
                                signCompIcon1.setImageResource(viewModel.getSignDrawable(comp1))
                                val comp2 = zodiacSign.compatibilities[1]
                                signComp2.text = comp2
                                signCompIcon2.setImageResource(viewModel.getSignDrawable(comp2))


                                posAspects.text =
                                    zodiacSign.positive_aspects[0] + " - " + zodiacSign.positive_aspects[1] + " - " + zodiacSign.positive_aspects[2]
                                negAspects.text =
                                    zodiacSign.negative_aspects[0] + " - " + zodiacSign.negative_aspects[1]
                            }
                        }
                    } catch (e: Exception) {
                        Log.e("userDescrptionFragment","Error al añadir información en algún campo" + e.message)
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