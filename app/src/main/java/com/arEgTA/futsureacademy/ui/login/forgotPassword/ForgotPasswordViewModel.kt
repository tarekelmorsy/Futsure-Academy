package com.arEgTA.futsureacademy.ui.login.forgotPassword

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arEgTA.futsureacademy.model.repositories.AuthenticationRepo

class ForgotPasswordViewModel (application: Application, var authenticationRepo : AuthenticationRepo) : AndroidViewModel(application) {

    fun forgotPassword(email:String){
        authenticationRepo.forgotPassword(email)

    }


    class Factory(private val application: Application, val authenticationRepo: AuthenticationRepo) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ForgotPasswordViewModel(application,authenticationRepo) as T
        }
    }

    companion object{
        fun create(context: Fragment): ForgotPasswordViewModel = ViewModelProvider(
            context,
            Factory(
                context.context?.applicationContext as Application,
                AuthenticationRepo(context.context?.applicationContext as Application)
            )
        )[ForgotPasswordViewModel::class.java]
    }
}