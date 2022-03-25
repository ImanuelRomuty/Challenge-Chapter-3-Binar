package com.example.challenge3withnavigationcomponent

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import java.io.ByteArrayOutputStream


class FourthFragment : Fragment() {
    private lateinit var inputAge : EditText
    private lateinit var inputAddress : EditText
    private  lateinit var inputJob : EditText
    private lateinit var buttonNavigateBackToScreen3 : Button
    private lateinit var cameraButton               : Button
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CameraFragment.
         */
        // TODO: Rename and change types and number of parameters
        private const val CAMERA_PERMISSION_CODE = 1
        private const val CAMERA_REQUEST_CODE    = 2
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fourth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inputAge = view.findViewById(R.id.input_age_edit_text)
        inputAddress = view.findViewById(R.id.input_address_edit_text)
        inputJob       = view.findViewById(R.id.input_job_edit_text)
        buttonNavigateBackToScreen3 = view.findViewById(R.id.navigate_back_to_3_fragment)
        cameraButton = view.findViewById(R.id.camera_button)

        cameraButton.setOnClickListener {
            if (checkSelfPermission(
                    requireContext(),Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                //Note di fragment menggunakan requireContext() kalau di activity bisa pakai "this"
            ){
                val capt = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(capt, CAMERA_REQUEST_CODE)
            }else{
                requestPermissions(
                    requireActivity(), arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE
                )
            }
        }

        buttonNavigateBackToScreen3.setOnClickListener {
            val person = Person(
                name = arguments?.getString("name"),
                age = inputAge.text.toString(),
                address = inputAddress.text.toString(),
                job = inputJob.text.toString()
            )
            findNavController().navigate(FourthFragmentDirections.actionFourthFragmentToThirdFragment(person=person))
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode== CAMERA_PERMISSION_CODE){
            if (grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                val capt = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(capt, CAMERA_REQUEST_CODE)
            }
        }else{
            Toast.makeText(requireContext(),"TERTOLAK",Toast.LENGTH_LONG).show()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== Activity.RESULT_OK){
            if(requestCode== CAMERA_REQUEST_CODE){
                val bundle = Bundle()
                val thumb:Bitmap = data!!.extras!!.get("data") as Bitmap
                val stream = ByteArrayOutputStream()
                thumb.compress(Bitmap.CompressFormat.PNG, 90, stream)
                val image = stream.toByteArray()

                val person = Person(
                    byteArray = image,
                    name = arguments?.getString("name"),
                    age = inputAge.text.toString(),
                    address = inputAddress.text.toString(),
                    job = inputJob.text.toString()
                )
                bundle.putByteArray("hero",image)
                findNavController().navigate(FourthFragmentDirections.actionFourthFragmentToThirdFragment(person=person))
            }
        }
    }
}

