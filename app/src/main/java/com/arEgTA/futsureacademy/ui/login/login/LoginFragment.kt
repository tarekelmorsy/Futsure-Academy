package com.arEgTA.futsureacademy.ui.login.login

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.arEgTA.futsureacademy.BaseFragment
import com.arEgTA.futsureacademy.R
import com.arEgTA.futsureacademy.databinding.FragmentLoginBinding
import com.arEgTA.futsureacademy.ui.home.HomeActivity
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val loginViewModel by lazy {
        LoginViewModel.create(this)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginViewModel.userData.observe(viewLifecycleOwner) {
            if (it != null) {
                //findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
                val intent = Intent(getActivity(), HomeActivity::class.java)
                getActivity()?.startActivity(intent)
                activity?.finish()
            }
        }

        val imageSlider = binding.imageSlider
        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.photo1))
        imageList.add(SlideModel(R.drawable.photo2))
        imageList.add(SlideModel(R.drawable.photo3))
        imageList.add(SlideModel(R.drawable.photo4))
        imageList.add(SlideModel(R.drawable.one))
        imageList.add(SlideModel(R.drawable.photo5))
        imageList.add(SlideModel(R.drawable.photo6))
        imageList.add(SlideModel(R.drawable.photo7))
        imageList.add(SlideModel(R.drawable.photo8))
        imageList.add(SlideModel(R.drawable.two))

        imageSlider.setImageList(imageList, ScaleTypes.FIT)

        binding.btRegister.setOnClickListener {

            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)

        }
        //check input
        binding.btLogin.setOnClickListener {
            if (binding.etLoginEmail.text.isNullOrEmpty()) {
                binding.tfEmail.error = getString(R.string.please_enter_email)
            } else if (binding.etLoginPassword.text.isNullOrEmpty()) {
                binding.tfPassword.error = getString(R.string.please_enter_password)
            } else {
                binding.tfPassword.error = null
                binding.tfEmail.error = null

                var email: String = binding.etLoginEmail.text.toString()
                var password: String = binding.etLoginPassword.text.toString()
                loginViewModel.login(email, password)
            }
            binding.etLoginEmail.doOnTextChanged { text, start, before, count ->
                if (text!!.isNullOrEmpty())
                    binding.tfEmail.error = getString(R.string.please_enter_email)
                else
                    binding.tfEmail.error = null
            }
            binding.etLoginPassword.doOnTextChanged { text, start, before, count ->
                if (text!!.isNullOrEmpty())
                    binding.tfEmail.error = getString(R.string.please_enter_password)
                else
                    binding.tfPassword.error = null
            }


        }
        binding.tvForgetPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)

        }

    }
}