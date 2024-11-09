package dadm.hsingh.horoscopoapp.ui.horoscope

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FragmentHoroscopeBinding
import dadm.hsingh.horoscopoapp.ui.horoscope.monthly.MonthlyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeFragment: Fragment(R.layout.fragment_horoscope) {

    private var _binding : FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!


    private val viewModel : HoroscopeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHoroscopeBinding.bind(view)


        val adapter = TabPagerAdapter(this)

        binding.viewPager2.adapter = adapter


        val tabTitles = arrayListOf(getString(R.string.daily),getString(R.string.weekly), getString(R.string.monthly))

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()



        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(p0: TabLayout.Tab?) {
                if (p0 != null){
                    binding.viewPager2.currentItem = p0.position
                }
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

        })

        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.selectTab((binding.tabLayout.getTabAt((position))))
            }
        })



        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.profile_sign.collect{ profile ->
                    if (profile != null){
                        binding.mainIcon.setImageResource(profile.defaultImage)
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