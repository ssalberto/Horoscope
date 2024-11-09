package dadm.hsingh.horoscopoapp.ui.compatibility

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import dadm.hsingh.horoscopoapp.ui.compatibility.calculator.CalculatorFragment
import dadm.hsingh.horoscopoapp.ui.compatibility.friends.FriendsFragment


class TabPagerAdapter2(
    fragment: Fragment
) : FragmentStateAdapter(fragment)  {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> CalculatorFragment()
            else -> FriendsFragment()
        }
    }
}