package dadm.hsingh.horoscopoapp.ui.settings

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.data.settings.SettingsPreferenceDataStore
import dadm.hsingh.horoscopoapp.databinding.FragmentSettingsBinding
import dadm.hsingh.horoscopoapp.ui.compatibility.CompatibilityViewModel
import dadm.hsingh.horoscopoapp.ui.compatibility.friends.formFriends.FriendFormFragment
import dadm.hsingh.horoscopoapp.utils.AlarmService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment: Fragment(R.layout.fragment_settings) {


    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private val viewModel : SettingsViewModel by viewModels()
    private lateinit var alarmService : AlarmService
    private var notifReminder = false
    private var notifBirthdays = false


    @Inject
    lateinit var settingsPreferenceDataStore: SettingsPreferenceDataStore


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        alarmService = AlarmService(requireContext())

        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentSettingsBinding.bind(view)

        val preferencesFragment = PreferencesFragment()

        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(binding.settingsFrame.id, preferencesFragment)
        transaction.commit()

        // Dark mode
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.darkMode.collect { isDarkModeEnabled ->
                    if (isDarkModeEnabled) {
                        // Cambiar al tema oscuro
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    } else {
                        // Cambiar al tema claro
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                }
            }
        }

        // Notificacion recordatorio
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.notificationReminder.collect { isEnabled ->
                    if (isEnabled) {
                        notifReminder = true
                        alarmService.setReminderAlarm()
                    } else {
                        notifReminder = false
                        alarmService.cancelAlarms()
                        if (notifBirthdays) {
                            viewModel.setAllBirthdayAlarms(alarmService)
                        }
                    }
                }
            }
        }

        // Notificaciones cumpleaños
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.notificationBirthdays.collect { isEnabled ->
                    if (isEnabled) {
                        notifBirthdays = true
                        viewModel.setAllBirthdayAlarms(alarmService)
                    } else {
                        notifBirthdays = false
                        alarmService.cancelAlarms()
                        if (notifReminder) {
                            alarmService.setReminderAlarm()
                        }
                    }
                }
            }
        }



        binding.buttonEditUser.setOnClickListener {
            val friendsViewModel: CompatibilityViewModel by activityViewModels()
            val friendUser = viewModel.getFriendUser()
            friendsViewModel.setFriend(friendUser)
            FriendFormFragment(disableNameEdit=true).show(childFragmentManager, "")
        }


    }


}