package dadm.hsingh.horoscopoapp.ui.compatibility

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FragmentCompatibilityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompatibilityFragment : Fragment(R.layout.fragment_compatibility){

    private var _binding : FragmentCompatibilityBinding? = null
    private val binding get() = _binding!!



    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCompatibilityBinding.bind(view)

        val adapter = TabPagerAdapter2(this)

        binding.viewPager3.adapter = adapter


        val tabTitles = arrayListOf(getString(R.string.calculator), getString(R.string.friends))
        TabLayoutMediator(binding.tabLayout3, binding.viewPager3) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()



        binding.tabLayout3.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(p0: TabLayout.Tab?) {
                if (p0 != null){
                    binding.viewPager3.currentItem = p0.position
                }
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

        })

        binding.viewPager3.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout3.selectTab((binding.tabLayout3.getTabAt((position))))
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}