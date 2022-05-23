package com.arEgTA.futsureacademy.ui.home.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.arEgTA.futsureacademy.R
import com.arEgTA.futsureacademy.ui.home.BaseFragmentHome
import com.arEgTA.futsureacademy.ui.login.login.LoginViewModel
import com.arEgTA.futsureacademy.ui.login.LoginActivity
import com.arEgTA.futsureacademy.utils.Constants
import com.arEgTA.futsureacademy.databinding.FragmentProfileBinding
import com.arEgTA.futsureacademy.model.PushNotification
import com.arEgTA.futsureacademy.notification.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ProfileFragment : BaseFragmentHome<FragmentProfileBinding>(FragmentProfileBinding::inflate) {


    private val authenticationViewModel by lazy {
        LoginViewModel.create(this)
    }

    private val profileViewModel by lazy {
        ProfileViewModel.create(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        profileViewModel.loadProfile()
        loadProfile()

        // LogOut
        binding.btLogOut.setOnClickListener {
            authenticationViewModel.signOut()
            Toast.makeText(activity, "تم تسجيل الخروج بنجاح", Toast.LENGTH_LONG).show()
            val intent = Intent(getActivity(), LoginActivity::class.java)
            getActivity()?.startActivity(intent)
            activity?.finish()
        }
        // get user id from sharedPreferences
        val sharedPreferences =
            requireActivity().getSharedPreferences("sharedPrefFile", Context.MODE_PRIVATE)
        var idUser = sharedPreferences.getString(Constants.LOGINID, "0")

        if (idUser.equals("7VrTy19p9OOWG8qVXoasfUz9Esh2") || idUser.equals("TsEOWcKTFyZ5glTnYRlRgyhulN62")) {
            binding.btAdmin.visibility = View.VISIBLE
            binding.btUpdateProfile.visibility = View.VISIBLE

        } else {
            binding.btAdmin.visibility = View.GONE
            binding.btUpdateProfile.visibility = View.GONE
        }
        binding.btAdmin.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_navigation_admin)

        }

    }


    /**
     * send Notification by token
     */
    private fun sendNotification(notification: PushNotification) =
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstance.api.postNotification(notification)
                if (response.isSuccessful) {
                    //   Log.d(TAG, "Response: ${Gson().toJson(response)}")
                } else {
                    // Log.e(TAG, response.errorBody().toString())
                }
            } catch (e: Exception) {
                // Log.e(TAG, e.toString())
            }
        }


    /**
     *  get user details from firebase
     */

    fun loadProfile() {
        profileViewModel.userProfileMutableLiveData.observe(viewLifecycleOwner) {
            binding.tvEmailProfile.text = it.email
            binding.tvAgeProfile.text = it.age
            binding.tvDatep.text = it.dateOfJoin
            binding.tvGroup.text = it.groupName
            binding.tvLeaderNameProfile.text = it.leaderName
            binding.tvNameProfile.text = it.name
            binding.tvBirthdayProfile.text = it.birthday
            binding.btUpdateProfile.setOnClickListener { it2 ->
                findNavController().navigate(
                    ProfileFragmentDirections.actionNavigationProfileToItemProfileDialogFragment(it)
                )
            }

        }
    }
}