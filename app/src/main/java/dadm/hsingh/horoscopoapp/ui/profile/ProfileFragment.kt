package dadm.hsingh.horoscopoapp.ui.profile

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch

class ProfileFragment : Fragment(R.layout.fragment_profile){

    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel : ProfileViewModel by activityViewModels()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)

        val adapter = TabPagerAdapter3(this)
        binding.viewPagerProfile.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.profile_sign.collect {user ->
                    if (user != null) {
                        if (user.imageUri == null){
                            binding.profilePicture.setImageResource(user.defaultImage)
                        }else{
                            binding.profilePicture.setImageURI(Uri.parse(user.imageUri))
                        }
                        binding.username.text = user.name
                    }
                }
            }
        }


        val tabTitles = arrayListOf(getString(R.string.personalInfo), getString(R.string.astralDescription))
        TabLayoutMediator(binding.tabLayoutProfile, binding.viewPagerProfile) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()



        binding.tabLayoutProfile.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(p0: TabLayout.Tab?) {
                if (p0 != null){
                    binding.viewPagerProfile.currentItem = p0.position
                }
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

        })

        binding.viewPagerProfile.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayoutProfile.selectTab((binding.tabLayoutProfile.getTabAt((position))))
            }
        })

        //configurar el boton de ajustes

        binding.settingsButton.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_settingsFragment)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}