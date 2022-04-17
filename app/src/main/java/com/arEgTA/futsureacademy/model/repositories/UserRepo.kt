package com.arEgTA.futsureacademy.model.repositories

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.arEgTA.futsureacademy.model.*
import com.arEgTA.futsureacademy.utils.Constants.MONTH
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*

class UserRepo {

    private var application: Application
    var firebaseProfileMutableLiveData: MutableLiveData<Profile>
    var firebaseUsersMutableLiveData: MutableLiveData<List<Admin>>
    var firebaseSearchMutableLiveData: MutableLiveData<List<Admin>>
    var firebaseMonthsMutableLiveData: MutableLiveData<List<Month>>
    var firebaseScoreMonthMutableLiveData: MutableLiveData<List<Score>>
    var firebaseSeasonMutableLiveData: MutableLiveData<List<Season>>
    var firebaseScoreSeasonMutableLiveData: MutableLiveData<List<Score>>


    var database: FirebaseDatabase? = null
    var databaseReferenceProfile: DatabaseReference? = null
    var databaseReferenceUser: DatabaseReference? = null
    var databaseReferenceMonth: DatabaseReference? = null
    var databaseReferenceSeason: DatabaseReference? = null
    val sharedPreferences: SharedPreferences
    var idUser: String? = null
    private var firebaseAuth: FirebaseAuth;

    constructor(application: Application) {
        // initialization firebase
        this.application = application
        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReferenceProfile = database?.reference!!.child("profile")
        databaseReferenceProfile!!.keepSynced(true)
        databaseReferenceUser = database!!.getReference("users")
        databaseReferenceUser!!.keepSynced(true)
        databaseReferenceMonth = database!!.getReference("month")
        databaseReferenceMonth!!.keepSynced(true)
        databaseReferenceSeason = database!!.getReference("season")
        databaseReferenceSeason!!.keepSynced(true)

        sharedPreferences =
            application!!.getSharedPreferences("sharedPrefFile", Context.MODE_PRIVATE)


        // initialization MutableLiveData
        firebaseProfileMutableLiveData = MutableLiveData<Profile>()
        firebaseUsersMutableLiveData = MutableLiveData<List<Admin>>()
        firebaseSearchMutableLiveData = MutableLiveData<List<Admin>>()
        firebaseMonthsMutableLiveData = MutableLiveData<List<Month>>()
        firebaseScoreMonthMutableLiveData = MutableLiveData<List<Score>>()
        firebaseSeasonMutableLiveData = MutableLiveData<List<Season>>()
        firebaseScoreSeasonMutableLiveData = MutableLiveData<List<Score>>()


    }

    /**
     * get all user from firebase
     */
    fun getUserData() {
        databaseReferenceUser?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listUser: MutableList<Admin> = mutableListOf<Admin>()

                for (dataSnap in snapshot.children) {
                    val userResult = Admin(
                        dataSnap.child("email").value.toString(),
                        dataSnap.child("name").value.toString(),
                        dataSnap.child("id").value.toString(),
                        dataSnap.child("groupName").value.toString()
                    )
                    listUser.add(userResult)
                }
                firebaseUsersMutableLiveData.value = listUser
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(application, error.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    /**
     * set month details in firebase
     */
    fun setMonth(month: Month) {
        idUser = sharedPreferences.getString(MONTH, "0")
        var currentMonth: DatabaseReference? = null
        if (idUser.isNullOrEmpty()) {
            currentMonth =
                firebaseAuth.currentUser?.uid?.let { it1 -> databaseReferenceMonth?.child(it1) }
                    ?.child(month.date.replace("/", "-"))
        } else {
            currentMonth = databaseReferenceMonth?.child(idUser!!)
                ?.child(month.date.replace("/", "-"))

        }
        currentMonth?.child("attendance")?.setValue(month.attendance)
        currentMonth?.child("date")?.setValue(month.date)
        currentMonth?.child("interaction")?.setValue(month.interaction)
        currentMonth?.child("interactionWithLeader")?.setValue(month.interactionWithLeader)
        currentMonth?.child("leader")?.setValue(month.leader)
        currentMonth?.child("note")?.setValue(month.note)
        currentMonth?.child("quiz")?.setValue(month.quiz)
        currentMonth?.child("quran")?.setValue(month.quran)
        currentMonth?.child("season")?.setValue(month.season)
        currentMonth?.child("totalEvaluation")?.setValue(month.totalEvaluation)

    }

    /**
     * set season details in firebase
     */
    fun setSeason(season: Season) {
        idUser = sharedPreferences.getString(MONTH, "0")
        var currentSeason: DatabaseReference? = null
        if (idUser.isNullOrEmpty()) {
            currentSeason =
                firebaseAuth.currentUser?.uid?.let { it1 -> databaseReferenceSeason?.child(it1) }
                    ?.child(season.date.replace("/", "-"))
        } else {
            currentSeason = databaseReferenceSeason?.child(idUser!!)
                ?.child(season.date.replace("/", "-"))

        }
        currentSeason?.child("attendance")?.setValue(season.attendance)
        currentSeason?.child("date")?.setValue(season.date)
        currentSeason?.child("interaction")?.setValue(season.interaction)
        currentSeason?.child("testQuran")?.setValue(season.testQuran)
        currentSeason?.child("leader")?.setValue(season.leader)
        currentSeason?.child("note")?.setValue(season.note)
        currentSeason?.child("quiz")?.setValue(season.quiz)
        currentSeason?.child("quran")?.setValue(season.quran)
        currentSeason?.child("season")?.setValue(season.season)
        currentSeason?.child("totalEvaluation")?.setValue(season.totalEvaluation)
        currentSeason?.child("discussion")?.setValue(season.discussion)
        currentSeason?.child("project")?.setValue(season.project)
        currentSeason?.child("finalQuiz")?.setValue(season.finalQuiz)

    }

    /**
     * get all months details from firebase
     */
    fun getSeason() {
        idUser = sharedPreferences.getString(MONTH, "0")
        var id: String? = null
        if (idUser.isNullOrEmpty()) {
            firebaseAuth.currentUser?.uid?.let { id = it }
        } else {
            id = idUser
        }
        id?.let {
            databaseReferenceSeason?.child(it)?.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val listSeason: MutableList<Season> = mutableListOf<Season>()
                    val listScore: MutableList<Score> = mutableListOf<Score>()

                    for (dataSnap in snapshot.children) {
                        if (dataSnap != null) {
                            val seasonResult = Season(
                                dataSnap.child("leader").value.toString(),
                                dataSnap.child("totalEvaluation").value.toString(),
                                dataSnap.child("season").value.toString(),
                                dataSnap.child("date").value.toString(),
                                dataSnap.child("interaction").value.toString(),
                                dataSnap.child("discussion").value.toString(),
                                dataSnap.child("attendance").value.toString(),
                                dataSnap.child("quran").value.toString(),
                                dataSnap.child("quiz").value.toString(),
                                dataSnap.child("note").value.toString(),
                                dataSnap.child("testQuran").value.toString(),
                                dataSnap.child("finalQuiz").value.toString(),
                                dataSnap.child("project").value.toString()
                            )


                            val score = Score(
                                dataSnap.child("season").value.toString(),
                                dataSnap.child("totalEvaluation").value.toString()
                            )
                            listSeason.add(seasonResult)
                            listScore.add(score)


                        }
                    }
                    firebaseSeasonMutableLiveData.value = listSeason
                    firebaseScoreSeasonMutableLiveData.value = listScore
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(application, error.message, Toast.LENGTH_LONG).show()
                }

            })
        }
    }

    /**
     * get all months details from firebase
     */
    fun getMonth() {
        idUser = sharedPreferences.getString(MONTH, "0")
        var id: String? = null
        if (idUser.isNullOrEmpty()) {
            firebaseAuth.currentUser?.uid?.let { id = it }
        } else {
            id = idUser
        }
        id?.let {
            databaseReferenceMonth?.child(it)?.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val listMonths: MutableList<Month> = mutableListOf<Month>()
                    val listScore: MutableList<Score> = mutableListOf<Score>()

                    for (dataSnap in snapshot.children) {
                        if (dataSnap != null) {
                            val monthResult = Month(
                                dataSnap.child("date").value.toString(),
                                dataSnap.child("totalEvaluation").value.toString(),
                                dataSnap.child("season").value.toString(),
                                dataSnap.child("leader").value.toString(),
                                dataSnap.child("interaction").value.toString(),
                                dataSnap.child("interactionWithLeader").value.toString(),
                                dataSnap.child("attendance").value.toString(),
                                dataSnap.child("quran").value.toString(),
                                dataSnap.child("quiz").value.toString(),
                                dataSnap.child("note").value.toString()
                            )


                            val score = Score(
                                dataSnap.child("season").value.toString(),
                                dataSnap.child("totalEvaluation").value.toString()
                            )
                            listMonths.add(monthResult)
                            listScore.add(score)


                        }
                    }
                    firebaseMonthsMutableLiveData.value = listMonths
                    firebaseScoreMonthMutableLiveData.value = listScore
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(application, error.message, Toast.LENGTH_LONG).show()
                }

            })
        }
    }


    fun loadProfile() {
        idUser = sharedPreferences.getString(MONTH, "0")
        var id: String? = null
        if (idUser.isNullOrEmpty()) {
            firebaseAuth.currentUser?.uid?.let { id = it }
        } else {
            id = idUser
        }
        val userReference = id?.let { databaseReferenceProfile?.child(it) }
        userReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var profile = Profile(
                    snapshot.child("name").value.toString(),
                    snapshot.child("email").value.toString(),
                    snapshot.child("leaderName").value.toString(),
                    snapshot.child("age").value.toString(),
                    snapshot.child("birthday").value.toString(),
                    snapshot.child("groupName").value.toString(),
                    snapshot.child("dateOfJoin").value.toString()
                )
                firebaseProfileMutableLiveData.postValue(profile)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(application, error.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    /**
     * Search for any user by his name
     */
    fun searchUser(user: String) {
        databaseReferenceUser?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listUser: MutableList<Admin> = mutableListOf<Admin>()

                for (dataSnap in snapshot.children) {
                    val userResult = Admin(
                        dataSnap.child("email").value.toString(),
                        dataSnap.child("name").value.toString(),
                        dataSnap.child("id").value.toString(),
                        dataSnap.child("groupName").value.toString()
                    )
                    if (user.isNullOrEmpty()) {
                        listUser.clear()
                    } else if (userResult!!.name.contains(user)) {

                        listUser.add(userResult)
                    }

                }
                firebaseSearchMutableLiveData.value = listUser
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(application, error.message, Toast.LENGTH_LONG).show()
            }

        })

    }

    /**
     * update profile
     */
    fun updateProfile(profile: Profile) {
        idUser = sharedPreferences.getString(MONTH, "0")

        val profileMap = mapOf<String, String>(
            "name" to profile.name,
            "email" to profile.email,
            "leaderName" to profile.leaderName,
            "age" to profile.age,
            "birthday" to profile.birthday,
            "groupName" to profile.groupName,
            "dateOfJoin" to profile.dateOfJoin,
        )
        idUser?.let {
            databaseReferenceProfile?.child(it)?.updateChildren(profileMap)?.addOnSuccessListener {
                Toast.makeText(application, "تم التعديل", Toast.LENGTH_LONG).show()

            }
        }?.addOnFailureListener {
            Toast.makeText(application,"خطأ في التعديل",Toast.LENGTH_LONG).show()

        }

    }
    /**
     * update month when click icon update
     */
    fun updateMonth(month: Month) {
        idUser = sharedPreferences.getString(MONTH, "0")

        val monthMap = mapOf<String, String>(
            "date" to month.date,
            "totalEvaluation" to month.totalEvaluation  ,
            "season" to month.season ,
            "leader" to month.leader ,
            "interaction" to month.interaction ,
            "interactionWithLeader" to month.interactionWithLeader ,
            "quran" to month.quran ,
            "quiz" to month.quiz ,
            "note" to month.note ,
            "attendance" to month.attendance ,
        )
        idUser?.let {
            databaseReferenceMonth?.child(it)?.child(month.date.replace("/", "-"))?.updateChildren(monthMap)?.addOnSuccessListener {

            }
        }?.addOnFailureListener {
            Toast.makeText(application,"خطأ في التعديل",Toast.LENGTH_SHORT).show()

        }

    }

    /**
     * delete month when click icon delete
     */
    fun deleteMonth(date:String){
        idUser = sharedPreferences.getString(MONTH, "0")
        idUser?.let {
            databaseReferenceMonth?.child(it)?.child(date.replace("/", "-"))?.removeValue()?.addOnSuccessListener {
                Toast.makeText(application, "تم الحذف", Toast.LENGTH_SHORT).show()

            }
        }?.addOnFailureListener {
            Toast.makeText(application,"خطأ في الحذف",Toast.LENGTH_SHORT).show()

        }
    }


    /**
     * delete Season when click icon delete
     */
    fun deleteSeason(date:String){
        idUser = sharedPreferences.getString(MONTH, "0")
        idUser?.let {
            databaseReferenceSeason?.child(it)?.child(date.replace("/", "-"))?.removeValue()?.addOnSuccessListener {
                Toast.makeText(application, "تم الحذف", Toast.LENGTH_SHORT).show()

            }
        }?.addOnFailureListener {
            Toast.makeText(application,"خطأ في الحذف",Toast.LENGTH_SHORT).show()

        }
    }

    /**
     * Delete all user data
     */
    fun deleteUser(idUser:String){
        // delete Season
        databaseReferenceSeason?.child(idUser)?.removeValue()?.addOnSuccessListener {
     }?.addOnFailureListener {
        Toast.makeText(application,"خطأ في الحذف",Toast.LENGTH_SHORT).show()

    }
        //delete month
        databaseReferenceMonth?.child(idUser)?.removeValue()?.addOnSuccessListener {
         }?.addOnFailureListener {
            Toast.makeText(application,"خطأ في الحذف",Toast.LENGTH_SHORT).show()

        }
        //delete profile
        databaseReferenceProfile?.child(idUser)?.removeValue()?.addOnSuccessListener {
         }?.addOnFailureListener {
            Toast.makeText(application,"خطأ في الحذف",Toast.LENGTH_SHORT).show()

        }
        // delete profile form list
        databaseReferenceUser?.child(idUser)?.removeValue()?.addOnSuccessListener {
            Toast.makeText(application, "تم الحذف", Toast.LENGTH_SHORT).show()
        }?.addOnFailureListener {
            Toast.makeText(application,"خطأ في الحذف",Toast.LENGTH_SHORT).show()

        }


    }


}