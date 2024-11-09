package dadm.hsingh.horoscopoapp.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FragmentViewpagerBinding
import dadm.hsingh.horoscopoapp.ui.onboarding.screens.OnboardingFirstFragment
import dadm.hsingh.horoscopoapp.ui.onboarding.screens.OnboardingSecondFragment

class ViewPagerFragment: Fragment(R.layout.fragment_viewpager) {
    private var _binding : FragmentViewpagerBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentViewpagerBinding.bind(view)

        val fragmentList = arrayListOf(
            OnboardingFirstFragment(),
            OnboardingSecondFragment()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        binding.viewPager.adapter = adapter


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}