package com.arEgTA.futsureacademy.ui.home.ui.monthlyEvaluation.addMonthFragment

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arEgTA.futsureacademy.model.Admin
import com.arEgTA.futsureacademy.model.Month
import com.arEgTA.futsureacademy.model.repositories.UserRepo
import com.arEgTA.futsureacademy.ui.home.ui.admin.AdminViewModel

class AddMonthViewModel (application: Application, var userRepo: UserRepo) : AndroidViewModel(application) {


    fun setMonth(month: Month){
        userRepo.setMonth(month)
    }



    class Factory(private val application: Application, var userRepo: UserRepo) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AddMonthViewModel(application,userRepo) as T
        }
    }

    companion object{
        fun create(context: Fragment): AddMonthViewModel = ViewModelProvider(
            context,
            Factory(
                context.context?.applicationContext as Application,
                UserRepo(context.context?.applicationContext as Application)
            )
        )[AddMonthViewModel::class.java]
    }
}