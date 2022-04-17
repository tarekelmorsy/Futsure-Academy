package com.arEgTA.futsureacademy.ui.home.ui.admin

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.arEgTA.futsureacademy.model.Admin
import com.arEgTA.futsureacademy.model.repositories.UserRepo

class AdminViewModel(application: Application, var userRepo: UserRepo) :
    AndroidViewModel(application) {


    private var firebaseUsersMutableLiveData_ = MutableLiveData<List<Admin>>()
    private var firebaseSearchMutableLiveData_ = MutableLiveData<List<Admin>>()
    var firebaseUsersMutableLiveData : LiveData<List<Admin>>
        get() = firebaseUsersMutableLiveData_
        set(value) {}
    var firebaseSearchMutableLiveData : LiveData<List<Admin>>
        get() =firebaseSearchMutableLiveData_
        set(value) {}

    init {
        firebaseUsersMutableLiveData_ = userRepo.firebaseUsersMutableLiveData
        firebaseSearchMutableLiveData_ = userRepo.firebaseSearchMutableLiveData

    }

    fun getUserData() {
        userRepo.getUserData()
    }

    fun searchUser(user: String) {
        userRepo.searchUser(user)
    }
    fun deleteUser(idUser:String) {
        userRepo.deleteUser(idUser)
    }


        class Factory(private val application: Application, var userRepo: UserRepo) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AdminViewModel(application, userRepo) as T
        }
    }

    companion object {
        fun create(context: Fragment): AdminViewModel = ViewModelProvider(
            context,
            Factory(
                context.context?.applicationContext as Application,
                UserRepo(context.context?.applicationContext as Application)
            )
        )[AdminViewModel::class.java]
    }


}