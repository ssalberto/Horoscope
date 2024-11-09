package dadm.hsingh.horoscopoapp.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.preference.PreferenceFragmentCompat
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.data.settings.SettingsPreferenceDataStore
import dadm.hsingh.horoscopoapp.databinding.FragmentProfileBinding
import dadm.hsingh.horoscopoapp.databinding.FragmentSettingsBinding
import dadm.hsingh.horoscopoapp.ui.onboarding.ViewPagerFragment
import dadm.hsingh.horoscopoapp.utils.AlarmService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@AndroidEntryPoint
class PreferencesFragment () : PreferenceFragmentCompat() {




    @Inject
    lateinit var settingsPreferenceDataStore: SettingsPreferenceDataStore

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        preferenceManager.preferenceDataStore = settingsPreferenceDataStore
        setPreferencesFromResource(R.xml.preferences_settings, rootKey)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




    }


}