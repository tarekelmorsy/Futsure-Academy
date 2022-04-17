package com.arEgTA.futsureacademy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.arEgTA.futsureacademy.ui.login.LoginActivity

abstract class BaseFragment<T : ViewBinding>(val viewBindingInflater:(LayoutInflater)->T): Fragment(){

    val mainActivity by lazy{
        this.activity as LoginActivity
    }

    val binding by lazy {
        viewBindingInflater(layoutInflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity.activityPermissionResultData.observe(viewLifecycleOwner){
            if(it!=null)
                onRequestPermissionsResult(it)
        }
        mainActivity.activityResultLiveData.observe(viewLifecycleOwner)
        {
            if(it!=null)
                onActivityResult(it)
        }
    }

    open fun onActivityResult(activityResultData: LoginActivity.ActivityResultData?) {}

    open fun onRequestPermissionsResult(permissionResultData: LoginActivity.ActivityPermissionResultData?) {}
}