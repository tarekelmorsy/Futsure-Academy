package com.arEgTA.futsureacademy.ui.login.register

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.arEgTA.futsureacademy.model.Admin
import com.arEgTA.futsureacademy.model.Profile
import com.arEgTA.futsureacademy.model.repositories.AuthenticationRepo
import com.google.firebase.auth.FirebaseUser

class RegisterViewModel(application: Application, var authenticationRepo : AuthenticationRepo) : AndroidViewModel(application) {

    var userData = MutableLiveData<FirebaseUser>()
    var loggedStatus = MutableLiveData<Boolean>()
      init {
        userData=authenticationRepo.firebaseUserMutableLiveData
        loggedStatus=authenticationRepo.userLoggedMutableLiveData

    }

    fun register(email:String,password:String,profile: Profile){
        authenticationRepo.register(email,password,profile)

    }
    fun setUser(admin: Admin){
        authenticationRepo.setUser(admin)
    }



    class Factory(private val application: Application, val authenticationRepo: AuthenticationRepo) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RegisterViewModel(application,authenticationRepo) as T
        }
    }

    companion object{
        fun create(context: Fragment): RegisterViewModel = ViewModelProvider(
            context,
            Factory(
                context.context?.applicationContext as Application,
                AuthenticationRepo(context.context?.applicationContext as Application))
        )[RegisterViewModel::class.java]
    }


}