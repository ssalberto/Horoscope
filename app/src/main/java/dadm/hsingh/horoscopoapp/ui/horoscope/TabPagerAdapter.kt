package dadm.hsingh.horoscopoapp.ui.horoscope

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import dadm.hsingh.horoscopoapp.ui.horoscope.daily.DailyFragment
import dadm.hsingh.horoscopoapp.ui.horoscope.monthly.MonthlyFragment
import dadm.hsingh.horoscopoapp.ui.horoscope.weekly.WeeklyFragment


class TabPagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment)  {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> DailyFragment()
            1 -> WeeklyFragment()
            else -> MonthlyFragment()
        }
    }
}