package com.arEgTA.futsureacademy.ui.home.ui.profile

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arEgTA.futsureacademy.model.Profile
import com.arEgTA.futsureacademy.model.repositories.AuthenticationRepo
import com.arEgTA.futsureacademy.model.repositories.UserRepo
import com.arEgTA.futsureacademy.ui.login.register.RegisterViewModel
import com.google.firebase.auth.FirebaseUser

class ProfileViewModel(application: Application, var userRepo: UserRepo) : AndroidViewModel(application) {

    var userProfileMutableLiveData = MutableLiveData<Profile>()

    init {
        userProfileMutableLiveData=userRepo.firebaseProfileMutableLiveData

    }

    fun loadProfile(){
     userRepo.loadProfile()
    }
   fun updateProfile(profile: Profile){
       userRepo.updateProfile(profile)
   }



    class Factory(private val application: Application, val userRepo: UserRepo) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProfileViewModel(application,userRepo) as T
        }
    }

    companion object{
        fun create(context: Fragment): ProfileViewModel = ViewModelProvider(
            context,
            Factory(
                context.context?.applicationContext as Application,
                  UserRepo(context.context?.applicationContext as Application)
            )
        )[ProfileViewModel::class.java]
    }


}