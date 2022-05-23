package com.arEgTA.futsureacademy.ui.home.ui.admin

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.arEgTA.futsureacademy.R
import com.arEgTA.futsureacademy.databinding.FragmentAllStudentBinding
import com.arEgTA.futsureacademy.ui.home.BaseFragmentHome
import com.arEgTA.futsureacademy.utils.Constants.MONTH

class AdminFragment :
    BaseFragmentHome<FragmentAllStudentBinding>(FragmentAllStudentBinding::inflate) {

    private lateinit var adapterAdmin: AdapterAdmin
    private val adminViewModel by lazy {
        AdminViewModel.create(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.reAdmin.layoutManager = LinearLayoutManager(context)
        adapterAdmin = AdapterAdmin(ArrayList(), {
            //save id in sharedPreferences
            val sharedPreferences: SharedPreferences =
                requireActivity().getSharedPreferences("sharedPrefFile", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString(MONTH, it.id)
            editor.apply()
            editor.commit()
            Toast.makeText(
                activity,
                "${activity?.getString(R.string.opend)}${it.name}",
                Toast.LENGTH_LONG
            ).show()
        })
        // delete user
        {
            deleteUser(it)

        }
        binding?.reAdmin?.adapter = adapterAdmin
        adminViewModel.getUserData()


        if (binding.etSearch.text.isNullOrEmpty()) {
            adminViewModel.firebaseUsersMutableLiveData.observe(viewLifecycleOwner) {
                adapterAdmin.setAdmin(it)
            }
        }
        binding.etSearch.doOnTextChanged { text, start, before, count ->
            if (!text!!.isNullOrEmpty()) {
                adminViewModel.searchUser(text.toString())
                adminViewModel.firebaseSearchMutableLiveData.observe(viewLifecycleOwner) {
                    adapterAdmin.setAdmin(it)

                }
            } else {
                adminViewModel.firebaseUsersMutableLiveData.observe(viewLifecycleOwner) {
                    adapterAdmin.setAdmin(it)
                }

            }
        }
    }

    /**
     * delete user by
     */
    private fun deleteUser(id: String) {

        val builder = AlertDialog.Builder(activity)
        builder.setTitle("حذف الطالب")
        builder.setMessage(" هل انت متاكد من حذف الطالب نهائيا؟")
        builder.setPositiveButton("حذف") { dialog, which ->
            adminViewModel.deleteUser(id)
            Toast.makeText(activity, "تم الحذف", Toast.LENGTH_SHORT)
                .show()
        }

        builder.setNegativeButton("الفاء") { dialog, which ->
            Toast.makeText(activity, "تم الالغاء", Toast.LENGTH_SHORT)
                .show()
        }
        builder.show()

    }

}