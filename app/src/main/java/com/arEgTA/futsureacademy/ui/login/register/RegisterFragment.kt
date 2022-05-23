package com.arEgTA.futsureacademy.ui.login.register

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.arEgTA.futsureacademy.BaseFragment
import com.arEgTA.futsureacademy.R
import com.arEgTA.futsureacademy.databinding.FragmentRegisterBinding
import com.arEgTA.futsureacademy.model.Admin
import com.arEgTA.futsureacademy.model.Profile
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val registerViewModel by lazy {
        RegisterViewModel.create(this)
    }
    var cal = Calendar.getInstance()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerViewModel.userData.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
        }

        val imageSlider = binding.imageSlider2
        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.photo10))
        imageList.add(SlideModel(R.drawable.photo11))
        imageList.add(SlideModel(R.drawable.photo12))
        imageList.add(SlideModel(R.drawable.photo13))
        imageList.add(SlideModel(R.drawable.photo14))
        imageList.add(SlideModel(R.drawable.photo15))
        imageList.add(SlideModel(R.drawable.photo16))
        imageList.add(SlideModel(R.drawable.photo17))
        imageList.add(SlideModel(R.drawable.photo18))
        imageList.add(SlideModel(R.drawable.photo19))
        imageList.add(SlideModel(R.drawable.photo20))


        imageSlider.setImageList(imageList, ScaleTypes.FIT)

        register()

    }

    fun register() {
        binding.btRegister.setOnClickListener {
            if (binding.edEmailRegister.text.isNullOrEmpty()) {
                binding.tfEmailRegister.error = getString(R.string.please_enter_email)

            } else if (binding.edNameRegister.text.isNullOrEmpty()) {
                binding.tfNameRegister.error = getString(R.string.please_enter_name)

            } else if (binding.edAgeRegister.text.isNullOrEmpty()) {
                binding.tfAgeRegister.error = getString(R.string.please_enter_age)

            } else if (binding.edBirthdayRegister.text.isNullOrEmpty()) {
                binding.tfBirthdayRegister.error = getString(R.string.please_enter_birthday)

            } else if (binding.edLeaderNameRegister.text.isNullOrEmpty()) {
                binding.tfLeaderNameRegister.error = getString(R.string.please_enter_leader)

            } else if (binding.edGroupNameRegister.text.isNullOrEmpty()) {
                binding.tfGroupNameRegister.error = getString(R.string.please_enter_group)

            } else if (binding.edDateOfJoinRegister.text.isNullOrEmpty()) {
                binding.tfDateOfJoinRegister.error = getString(R.string.please_enter_join)

            } else if (binding.edPasswordRegister.text.isNullOrEmpty()) {
                binding.tfPasswordRegister.error = getString(R.string.please_enter_password)

            } else if (binding.edConfirmPassword.text.isNullOrEmpty()) {
                binding.tfConfirmPasswordRegister.error =
                    getString(R.string.please_enter_confirm_password)

            } else if (!binding.edConfirmPassword.text.toString()
                    .equals(binding.edPasswordRegister.text.toString())
            ) {
                binding.tfConfirmPasswordRegister.error =
                    getString(R.string.please_enter_confirm_password2)
            } else {


                var email: String = binding.edEmailRegister.text.toString()
                var name: String = binding.edNameRegister.text.toString()
                var age: String = binding.edAgeRegister.text.toString()
                var dateOfJoin: String = binding.edDateOfJoinRegister.text.toString()
                var birthday: String = binding.edBirthdayRegister.text.toString()
                var leaderName: String = binding.edLeaderNameRegister.text.toString()
                var group: String = binding.edGroupNameRegister.text.toString()
                var password: String = binding.edPasswordRegister.text.toString()


                registerViewModel.register(
                    email, password,
                    Profile(name, email, leaderName, age, birthday, group, dateOfJoin)
                )

                registerViewModel.userData.observe(viewLifecycleOwner) {

                    registerViewModel.setUser(Admin(email, name, it.uid, group))
                }
            }

            binding.edEmailRegister.doOnTextChanged { text, start, before, count ->
                if (text!!.isNullOrEmpty())
                    binding.tfEmailRegister.error = getString(R.string.please_enter_email)
                else
                    binding.tfEmailRegister.error = null
            }
            binding.edPasswordRegister.doOnTextChanged { text, start, before, count ->
                if (text!!.isNullOrEmpty())
                    binding.tfPasswordRegister.error = getString(R.string.please_enter_password)
                else
                    binding.tfPasswordRegister.error = null
            }
            binding.edConfirmPassword.doOnTextChanged { text, start, before, count ->
                if (text!!.isNullOrEmpty())
                    binding.tfConfirmPasswordRegister.error =
                        getString(R.string.please_enter_confirm_password)
                else
                    binding.tfConfirmPasswordRegister.error = null
            }
            binding.edNameRegister.doOnTextChanged { text, start, before, count ->
                if (text!!.isNullOrEmpty())
                    binding.tfNameRegister.error = getString(R.string.please_enter_name)
                else
                    binding.tfNameRegister.error = null
            }
            binding.edAgeRegister.doOnTextChanged { text, start, before, count ->
                if (text!!.isNullOrEmpty())
                    binding.tfAgeRegister.error = getString(R.string.please_enter_age)
                else
                    binding.tfAgeRegister.error = null
            }
            binding.edDateOfJoinRegister.doOnTextChanged { text, start, before, count ->
                if (text!!.isNullOrEmpty())
                    binding.tfDateOfJoinRegister.error = getString(R.string.please_enter_join)
                else
                    binding.tfDateOfJoinRegister.error = null
            }
            binding.edBirthdayRegister.doOnTextChanged { text, start, before, count ->
                if (text!!.isNullOrEmpty())
                    binding.tfBirthdayRegister.error = getString(R.string.please_enter_birthday)
                else
                    binding.tfBirthdayRegister.error = null
            }
            binding.edGroupNameRegister.doOnTextChanged { text, start, before, count ->
                if (text!!.isNullOrEmpty())
                    binding.tfGroupNameRegister.error = getString(R.string.please_enter_group)
                else
                    binding.tfGroupNameRegister.error = null
            }
            binding.edLeaderNameRegister.doOnTextChanged { text, start, before, count ->
                if (text!!.isNullOrEmpty())
                    binding.tfLeaderNameRegister.error = getString(R.string.please_enter_leader)
                else
                    binding.tfLeaderNameRegister.error = null
            }

        }
        // create an OnDateSetListener
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateBirthdayInView()
            }
        }
        // create an OnDateSetListener
        val dateSetListener2 = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateOfJoinInView()
            }
        }
        binding.edBirthdayRegister!!.setFocusable(false)
        binding.edDateOfJoinRegister!!.setFocusable(false)


        // when you click on the edit text , show DatePickerDialog that is set with OnDateSetListener
        binding.edBirthdayRegister!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                activity?.let {
                    DatePickerDialog(
                        it,
                        dateSetListener,
                        // set DatePickerDialog to point to today's date when it loads up
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)
                    ).show()
                }
            }

        })

        // when you click on the edit text , show DatePickerDialog that is set with OnDateSetListener
        binding.edDateOfJoinRegister!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                activity?.let {
                    DatePickerDialog(
                        it,
                        dateSetListener2,
                        // set DatePickerDialog to point to today's date when it loads up
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)
                    ).show()
                }
            }

        })
    }

    private fun updateBirthdayInView() {
        val myFormat = "dd/MM/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.edBirthdayRegister!!.setText(sdf.format(cal.getTime()))
    }

    private fun updateDateOfJoinInView() {
        val myFormat = "dd/MM/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.edDateOfJoinRegister!!.setText(sdf.format(cal.getTime()))
    }
}