package dadm.hsingh.horoscopoapp.ui.compatibility.friends.formFriends

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dadm.hsingh.horoscopoapp.R
import dadm.hsingh.horoscopoapp.databinding.FormsFriendBinding
import dadm.hsingh.horoscopoapp.domain.model.Friend
import dadm.hsingh.horoscopoapp.ui.compatibility.CompatibilityViewModel
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class FriendFormFragment(
    private val disableNameEdit: Boolean = false
) : DialogFragment(R.layout.forms_friend){

    private var _binding: FormsFriendBinding? = null
    private  val binding
        get() = _binding!!


    private val viewModel: CompatibilityViewModel by activityViewModels()

    private var filename: String? = null
    private lateinit var launcher: ActivityResultLauncher<Intent>

    private fun initLauncher() {
        launcher = registerForActivityResult(StartActivityForResult()) {
            try {
                if (it != null && it.resultCode == Activity.RESULT_OK) {
                    caseCapture()
                }
            } catch (e: Exception) {
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
        RequestMultiplePermissions()
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

    private val pickMedia = registerForActivityResult(PickVisualMedia()){ uri->
        if(uri!=null){
            val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
            context?.contentResolver?.takePersistableUriPermission(uri, flag)
            viewModel.setImageUri(uri.toString())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FormsFriendBinding.bind(view)
        initLauncher()

        binding.uploadBt.setOnClickListener{
            pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
        }

        binding.cameraBt.setOnClickListener{
            if (!allPermissionsGranted()){
                requestPermissionLauncher.launch(REQUIRED_PERMISSIONS)
            }else{
                takePicture()
            }
        }

        binding.buttonCancel.setOnClickListener {
            viewModel.resetFriend()
            viewModel.resetImageUri()
            this.dismiss()
        }

        if(viewModel.friend.value != null){
            val friend : Friend = viewModel.friend.value!!
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            binding.editTextName.text = Editable.Factory.getInstance().newEditable(friend.name)
            binding.birthDateInput.text = Editable.Factory.getInstance().newEditable(friend.dateBirth.format(formatter).toString())
            binding.birthTimeInput.text = Editable.Factory.getInstance().newEditable(friend.timeBirth.toString())
            binding.editTextPlaceBirth.text = Editable.Factory.getInstance().newEditable(friend.placeBirth)
            if(friend.imageUri != null){
                viewModel.setImageUri(friend.imageUri)
                binding.imageViewFriend.setImageURI(Uri.parse(friend.imageUri))
            }else{
                binding.imageViewFriend.setImageResource(friend.defaultImage)
            }

            binding.buttonAddFriend.text = getString(R.string.modify)
        }

        //DatePicker
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .build()

        binding.datePicker.setOnClickListener {
            datePicker.show(childFragmentManager, "tag");
        }

        datePicker.addOnPositiveButtonClickListener { selectedDate ->
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = selectedDate
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val selectedDateString = dateFormat.format(calendar.time)
            binding.birthDateInput.text = Editable.Factory.getInstance().newEditable(selectedDateString)
        }

        //TimePicker
        val TimePicker =
            MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(10)
                .setTitleText("Select Appointment time")
                .build()

        binding.timePicker.setOnClickListener {
            TimePicker.show(childFragmentManager, "")
        }

        TimePicker.addOnPositiveButtonClickListener {
            val hour = TimePicker.hour
            val minute = TimePicker.minute
            val formattedTime = String.format(Locale.getDefault(), "%02d:%02d", hour, minute)
            binding.birthTimeInput.text = Editable.Factory.getInstance().newEditable(formattedTime)
        }

        binding.buttonAddFriend.setOnClickListener {
            if(!isAnyFieldEmpty()){
                viewModel.addToFavourites(
                    binding.editTextName.text.toString(),
                    binding.birthDateInput.text.toString(),
                    binding.birthTimeInput.text.toString(),
                    binding.editTextPlaceBirth.text.toString(),
                    viewModel.image.value
                )
                viewModel.resetFriend()
                viewModel.resetImageUri()
                this.dismiss()
            }else{
                Toast.makeText(context, "Complete all fields", Toast.LENGTH_SHORT).show()
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

        if (disableNameEdit) {
            binding.editTextName.isEnabled = false
        }

    }

    private fun isAnyFieldEmpty() = (binding.editTextName.text.toString().isEmpty()
            || binding.birthDateInput.text.toString().isEmpty()
            || binding.birthTimeInput.text.toString().isEmpty()
            || binding.editTextPlaceBirth.text.toString().isEmpty())


    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        checkSelfPermission(
            requireContext(), it
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onStart() {
        super.onStart()
        // Cambiar el tamaño del diálogo
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,  // Ancho deseado, por ejemplo: ViewGroup.LayoutParams.MATCH_PARENT para que ocupe todo el ancho
            ViewGroup.LayoutParams.WRAP_CONTENT   // Altura deseada, por ejemplo: ViewGroup.LayoutParams.WRAP_CONTENT para ajustarse al contenido
        )
        val transparentDrawable = ColorDrawable(Color.TRANSPARENT)
        dialog?.window?.setBackgroundDrawable(transparentDrawable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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