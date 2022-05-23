package com.arEgTA.futsureacademy.ui.login.forgotPassword

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.arEgTA.futsureacademy.BaseFragment
import com.arEgTA.futsureacademy.R
import com.arEgTA.futsureacademy.databinding.FragmentForgotPasswordBinding

class ForgotPasswordFragment :
    BaseFragment<FragmentForgotPasswordBinding>(FragmentForgotPasswordBinding::inflate) {


    private val forgotPasswordViewModel by lazy {
        ForgotPasswordViewModel.create(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btOkForget.setOnClickListener {
            if (binding.etLoginEmailForget.text.isNullOrEmpty()) {
                binding.tfEmail.error = getString(R.string.please_enter_email)
            } else {
                binding.tfEmail.error = null

                forgotPasswordViewModel.forgotPassword(binding.etLoginEmailForget.text.toString())
            }
            binding.etLoginEmailForget.doOnTextChanged { text, start, before, count ->
                if (text!!.isNullOrEmpty()) {
                    binding.tfEmail.error = getString(R.string.please_enter_email)
                } else {
                    binding.tfEmail.error = null
                }

            }

        }

    }


}