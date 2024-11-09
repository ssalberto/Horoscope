package dadm.hsingh.horoscopoapp.ui.compatibility.friends

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FragmentFriendsBinding
import dadm.hsingh.horoscopoapp.domain.model.Friend
import dadm.hsingh.horoscopoapp.ui.compatibility.CompatibilityViewModel
import dadm.hsingh.horoscopoapp.ui.compatibility.friends.formFriends.FriendFormFragment
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

class FriendsFragment : Fragment(R.layout.fragment_friends){
    private var _binding : FragmentFriendsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CompatibilityViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFriendsBinding.bind(view)

        val adapter = FriendsListAdapter(::onEditClick, ::onDeleteClick)
        binding.textView.adapter = adapter

        binding.addFriend.setOnClickListener {
            FriendFormFragment().show(childFragmentManager, "")
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setSearchQuery(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.setSearchQuery(newText ?: "")
                return true
            }
        })

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.filteredFriends.collect { filteredList ->
                    adapter.submitList(filteredList)
                }
            }
        }
    }

    private fun onEditClick(friend: Friend) {
        viewModel.setFriend(friend)
        FriendFormFragment().show(childFragmentManager, "")
    }

    private fun onDeleteClick(friend: Friend){
        viewModel.removeFriend(friend)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun generateRandomFriendsList(size: Int): List<Friend> {
        val friendsList = mutableListOf<Friend>()
        val names = listOf("Alice", "Bob", "Charlie", "David", "Emma", "Frank", "Grace", "Henry", "Ivy", "Jack")
        val places = listOf("New York", "Los Angeles", "London", "Paris", "Tokyo", "Sydney", "Berlin", "Rome", "Moscow", "Toronto")
        val zodiacImages = listOf(
            R.drawable.aries,
            R.drawable.taurus,
            R.drawable.gemini,
            R.drawable.cancer,
            R.drawable.leo,
            R.drawable.virgo,
            R.drawable.libra,
            R.drawable.scorpio,
            R.drawable.sagittarius,
            R.drawable.capricorn,
            R.drawable.aquarius,
            R.drawable.pisces
        )
        repeat(size) {
            val id = it.toString()
            val name = names.random()
            val dateBirth = generateRandomDate()
            val timeBirth = generateRandomTime()
            val placeBirth = places.random()
            val zodiacSignImage = zodiacImages.random()
        }
        return friendsList
    }

    private fun generateRandomDate(): LocalDate {
        val start = LocalDate.of(1950, 1, 1)
        val end = LocalDate.of(2005, 12, 31)
        val randomDay = start.toEpochDay() + (Math.random() * (end.toEpochDay() - start.toEpochDay())).toLong()
        return LocalDate.ofEpochDay(randomDay)
    }

    private fun generateRandomTime(): LocalTime {
        return LocalTime.of((0..23).random(), (0..59).random(), (0..59).random())
    }
}