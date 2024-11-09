package dadm.hsingh.horoscopoapp.ui.onboarding.screens

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FragmentOnboardingSecondBinding
import dadm.hsingh.horoscopoapp.ui.MainActivity
import dadm.hsingh.horoscopoapp.ui.OnBoardingActivity
import dadm.hsingh.horoscopoapp.ui.compatibility.friends.formFriends.FriendFormFragment
import dadm.hsingh.horoscopoapp.ui.onboarding.OnboardingViewModel
import kotlinx.coroutines.launch
import java.io.File

class OnboardingSecondFragment : Fragment(R.layout.fragment_onboarding_second){
    private var _binding : FragmentOnboardingSecondBinding? = null
    private val binding get() = _binding!!

    private val viewModel: OnboardingViewModel by activityViewModels()
    private var filename: String? = null
    private lateinit var launcher: ActivityResultLauncher<Intent>

    private fun initLauncher() {
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            try {
                if (it != null && it.resultCode == Activity.RESULT_OK) {
                    caseCapture()
                }
            } catch (e: Exception) {
                Log.e("ERR", e.toString())
            }
        }
    }
    private fun caseCapture() {
        filename.let {
            if (it != null) {
                val tempFile = File(it)
                viewModel.setImageUri(tempFile.toUri().toString())
            }
        }
    }
    private fun takePicture() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val image = createTempFile()

        val uri = FileProvider.getUriForFile(
            requireActivity(),
            "dadm.hsingh.horoscopoapp.provider",
            image
        )

        filename = image.absolutePath.replace("/storage/emulated/0", "sdcard")

        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)

        launcher.launch(intent)
    }
    private fun createTempFile(): File {
        val tempDir = File("${requireActivity().externalMediaDirs.first()}/Pictures")

        if (!tempDir.exists()) {
            tempDir.mkdirs()
        }

        return File.createTempFile("capture_", ".png", tempDir)
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        var allGranted = true
        for(isGranted in result.values){
            allGranted = allGranted && isGranted
        }
        if(allGranted){
            Toast.makeText(context, "All permission granted", Toast.LENGTH_LONG).show()
            takePicture()
        }else{
            Toast.makeText(context, "All or some permissions are denied", Toast.LENGTH_LONG).show()

        }
    }

    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){ uri->
        if(uri!=null){
            val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
            context?.contentResolver?.takePersistableUriPermission(uri, flag)
            viewModel.setImageUri(uri.toString())
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentOnboardingSecondBinding.bind(view)
        val viewPager =  activity?.findViewById<ViewPager2>(R.id.viewPager)

        initLauncher()

        binding.finish.setOnClickListener {
            if (!binding.editTextPlaceBirth.text.isNullOrBlank()) {
                viewModel.setBirthPlace(binding.editTextPlaceBirth.text.toString())
            }
            if (viewModel.checkRequiredFields()) {
                viewModel.saveUser()
                onBoardingFinished()
            } else {
                Snackbar.make(view, R.string.rellena_primero, Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.previous.setOnClickListener {
            viewPager?.currentItem = 0
        }

        binding.uploadBt.setOnClickListener{
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
        binding.cameraBt.setOnClickListener{
            Log.d("CAMERA", "Hola")
            if (!allPermissionsGranted()){
                requestPermissionLauncher.launch(REQUIRED_PERMISSIONS)
            }else{
                takePicture()
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.image.collect{uri ->
                    if (uri != null) {
                        binding.imageViewFriend.setImageURI(Uri.parse(uri))
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun onBoardingFinished(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("isFirstRun", false)
        editor.apply()
        val intent = Intent(requireActivity(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()

    }
    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            requireContext(), it
        ) == PackageManager.PERMISSION_GRANTED
    }
    companion object {
        private val REQUIRED_PERMISSIONS = mutableListOf(
            Manifest.permission.CAMERA).apply {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }.toTypedArray()
    }
}