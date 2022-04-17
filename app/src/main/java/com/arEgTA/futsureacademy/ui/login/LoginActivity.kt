package com.arEgTA.futsureacademy.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.arEgTA.futsureacademy.R
import com.arEgTA.futsureacademy.databinding.ActivityHomeBinding
import com.arEgTA.futsureacademy.databinding.ActivityLoginBinding
import com.arEgTA.futsureacademy.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var navController :NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


          navController = findNavController(R.id.fragmentContainerView)

        val appBarConfiguration = AppBarConfiguration(navController.graph)

        setupActionBarWithNavController(navController, appBarConfiguration)


    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()|| super.onSupportNavigateUp()
    }

    var activityResultLiveData = MutableLiveData<ActivityResultData?>()
    var activityPermissionResultData = MutableLiveData<ActivityPermissionResultData?>()


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        activityResultLiveData.postValue(ActivityResultData(requestCode, resultCode, data))
        activityResultLiveData.postValue(null)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        activityPermissionResultData.postValue(ActivityPermissionResultData(requestCode, permissions, grantResults))
        activityPermissionResultData.postValue(null)
    }

    data class ActivityResultData(val requestCode: Int, val resultCode: Int, val data: Intent?)
    data class ActivityPermissionResultData(val requestCode: Int, val permissions: Array<out String>,val grantResults: IntArray) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as ActivityPermissionResultData

            if (requestCode != other.requestCode) return false
            if (!permissions.contentEquals(other.permissions)) return false
            if (!grantResults.contentEquals(other.grantResults)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = requestCode
            result = 31 * result + permissions.contentHashCode()
            result = 31 * result + grantResults.contentHashCode()
            return result
        }
    }


}