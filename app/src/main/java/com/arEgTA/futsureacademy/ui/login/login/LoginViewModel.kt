package com.arEgTA.futsureacademy.ui.login.login

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arEgTA.futsureacademy.model.repositories.AuthenticationRepo
import com.google.firebase.auth.FirebaseUser

class LoginViewModel(application: Application, var authenticationRepo: AuthenticationRepo) :
    AndroidViewModel(application) {

    var userData = MutableLiveData<FirebaseUser>()
    var loggedStatus = MutableLiveData<Boolean>()

    init {
        userData = authenticationRepo.firebaseUserMutableLiveData
        loggedStatus = authenticationRepo.userLoggedMutableLiveData

    }


    fun login(email: String, password: String) {
        authenticationRepo.login(email, password)

    }

    fun signOut() {
        authenticationRepo.signOut()

    }


    class Factory(
        private val application: Application,
        val authenticationRepo: AuthenticationRepo
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LoginViewModel(application, authenticationRepo) as T
        }
    }

    companion object {
        fun create(context: Fragment): LoginViewModel = ViewModelProvider(
            context,
            Factory(
                context.context?.applicationContext as Application,
                AuthenticationRepo(context.context?.applicationContext as Application)
            )
        )[LoginViewModel::class.java]
    }
}