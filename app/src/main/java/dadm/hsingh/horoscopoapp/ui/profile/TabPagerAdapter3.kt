package dadm.hsingh.horoscopoapp.ui.profile

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import dadm.hsingh.horoscopoapp.ui.profile.userDescription.userDescriptionFragment
import dadm.hsingh.horoscopoapp.ui.profile.userInfo.userInfoFragment

class TabPagerAdapter3(
    fragment: Fragment
) : FragmentStateAdapter(fragment)  {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> userInfoFragment()
            else -> userDescriptionFragment()
        }
    }
}