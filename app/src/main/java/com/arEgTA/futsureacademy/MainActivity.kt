package com.arEgTA.futsureacademy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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