package dadm.hsingh.horoscopoapp.ui.profile.userInfo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FragmentUserInfoBinding
import dadm.hsingh.horoscopoapp.ui.profile.ProfileViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Period

class userInfoFragment : Fragment(R.layout.fragment_user_info){

    private var _binding : FragmentUserInfoBinding? = null
    private val binding get() = _binding!!


    private val viewModel : ProfileViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentUserInfoBinding.bind(view)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.profile_sign.collect {user ->
                    if (user != null) {
                        binding.apply {
                            username.text = user.name
                            dateBirth.text = user.dateBirth.toString()
                            placeBirth.text = user.placeBirth
                            birthTime.text = user.timeBirth.toString()
                            age.text = calculateAge(user.dateBirth).toString()
                            zodiacSign.text = user.zodiacSign
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

    fun calculateAge(birthDate: LocalDate): Int {
        val fechaActual = LocalDate.now()
        val periodo = Period.between(birthDate, fechaActual)
        return periodo.years
    }
}