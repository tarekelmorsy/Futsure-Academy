package com.arEgTA.futsureacademy.model.repositories

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.arEgTA.futsureacademy.R
import com.arEgTA.futsureacademy.model.Admin
import com.arEgTA.futsureacademy.model.Profile
import com.arEgTA.futsureacademy.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AuthenticationRepo {

    private var application: Application
    var firebaseUserMutableLiveData: MutableLiveData<FirebaseUser>
    var userLoggedMutableLiveData: MutableLiveData<Boolean>
    var database: FirebaseDatabase? = null
    var databaseReferenceProfile: DatabaseReference? = null
    var databaseReferenceUsers: DatabaseReference? = null
    private var firebaseAuth: FirebaseAuth

    constructor(application: Application) {
        this.application = application
        // initialization firebase
        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReferenceProfile = database?.reference!!.child("profile")
        databaseReferenceUsers = database?.reference!!.child("users")
        databaseReferenceProfile!!.keepSynced(true)
        databaseReferenceUsers!!.keepSynced(true)
        firebaseUserMutableLiveData = MutableLiveData<FirebaseUser>()
        userLoggedMutableLiveData = MutableLiveData<Boolean>()
        if (firebaseAuth.currentUser != null) {
            firebaseUserMutableLiveData.postValue(firebaseAuth.currentUser)
        }


    }

    /**
     * user can register by email and profile information
     */
    fun register(email: String, password: String, profile: Profile) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                //register in firebase
                firebaseUserMutableLiveData.postValue(firebaseAuth.currentUser)
                var currentUserDb =
                    firebaseAuth.currentUser?.uid?.let { it1 -> databaseReferenceProfile?.child(it1) }
                currentUserDb?.child("name")?.setValue(profile.name)
                currentUserDb?.child("age")?.setValue(profile.age)
                currentUserDb?.child("birthday")?.setValue(profile.birthday)
                currentUserDb?.child("dateOfJoin")?.setValue(profile.dateOfJoin)
                currentUserDb?.child("email")?.setValue(profile.email)
                currentUserDb?.child("groupName")?.setValue(profile.groupName)
                currentUserDb?.child("leaderName")?.setValue(profile.leaderName)
                val sharedPreferences: SharedPreferences =
                    application.getSharedPreferences("sharedPrefFile", Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString(Constants.LOGINID, firebaseAuth.currentUser?.uid)
                editor.putString(Constants.MONTH, firebaseAuth.currentUser?.uid)
                editor.apply()
                editor.commit()

            } else
                Toast.makeText(application, it.exception!!.message, Toast.LENGTH_LONG).show()
        }

    }

    fun setUser(admin: Admin) {
        // set data in admin list
        var currentUserAdmin =
            firebaseAuth.currentUser?.uid?.let { it1 -> databaseReferenceUsers?.child(it1) }
        currentUserAdmin?.child("email")?.setValue(admin.email)
        currentUserAdmin?.child("name")?.setValue(admin.name)
        currentUserAdmin?.child("id")?.setValue(admin.id)
        currentUserAdmin?.child("groupName")?.setValue(admin.groupName)

    }

    /**
     * user can login by email and password
     */

    fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                firebaseUserMutableLiveData.postValue(firebaseAuth.currentUser)
                val sharedPreferences: SharedPreferences =
                    application.getSharedPreferences("sharedPrefFile", Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString(Constants.LOGINID, firebaseAuth.currentUser?.uid)
                editor.putString(Constants.MONTH, firebaseAuth.currentUser?.uid)
                editor.apply()
                editor.commit()

            } else
                Toast.makeText(application, it.exception!!.message, Toast.LENGTH_LONG).show()
        }

    }

    /**
     * send message to email to change password
     */
    fun forgotPassword(userEmail: String) {


        firebaseAuth.sendPasswordResetEmail(userEmail)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        application,
                        application.getString(R.string.email_sent),
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(application, task.exception?.message, Toast.LENGTH_LONG).show()


                }
            }
    }

    /**
     * signOut from app open register screen
     */

    fun signOut() {
        firebaseAuth.signOut()
        userLoggedMutableLiveData.postValue(true)
    }
}