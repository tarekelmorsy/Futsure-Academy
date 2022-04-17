package com.arEgTA.futsureacademy.ui.home.ui.monthlyEvaluation

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.arEgTA.futsureacademy.R
import com.arEgTA.futsureacademy.databinding.FragmentDashboardBinding
import com.arEgTA.futsureacademy.model.Month
import com.arEgTA.futsureacademy.ui.home.BaseFragmentHome
import com.arEgTA.futsureacademy.utils.Constants

class MonthlyEvaluationFragment : BaseFragmentHome<FragmentDashboardBinding>(FragmentDashboardBinding::inflate) {
    private lateinit var adapterMonthEvaluation: AdapterMonthEvaluation
    private val monthlyEvaluationViewModel by lazy {
        MonthlyEvaluationViewModel.create(this)
    }
    lateinit var month:Month
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get all month from firebase
        monthlyEvaluationViewModel.getMonth()

        // get user id from sharedPreferences
        val sharedPreferences = requireActivity().getSharedPreferences("sharedPrefFile", Context.MODE_PRIVATE)
        var idUser = sharedPreferences.getString(Constants.LOGINID, "0")

        if (idUser.equals("7VrTy19p9OOWG8qVXoasfUz9Esh2")||idUser.equals("TsEOWcKTFyZ5glTnYRlRgyhulN62")){
            binding.btAddMonth.visibility=View.VISIBLE
        }else{
            binding.btAddMonth.visibility=View.GONE

        }
        adapterMonthEvaluation = AdapterMonthEvaluation(ArrayList(),{
            findNavController().navigate(MonthlyEvaluationFragmentDirections.actionNavigationMonthToMonthDetails(it)
            )
        },
            // delete item from list
            {

                val builder = AlertDialog.Builder(activity)
                builder.setTitle("حذف الشهر")
                builder.setMessage(" هل انت متاكد من حذف الشهر؟")
                builder.setPositiveButton("حذف") { dialog, which ->
                    monthlyEvaluationViewModel.deleteMonth(it.date)
                    Toast.makeText(activity, "تم الحذف", Toast.LENGTH_SHORT)
                        .show()
                }

                builder.setNegativeButton("الفاء") { dialog, which ->
                    Toast.makeText(activity, "تم الالغاء", Toast.LENGTH_SHORT)
                        .show()
                }
                builder.show()
            })
        // update item from list

        {
            findNavController().navigate(MonthlyEvaluationFragmentDirections.actionNavigationMonthToUpdateMonthFragment(it)
            )

        }
        binding.btAddMonth.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_month_to_addMonthFragment)

        }
        binding.reMonth.layoutManager = LinearLayoutManager(context)
        binding?.reMonth?.adapter = adapterMonthEvaluation
        // set all month in Recyclerview

        monthlyEvaluationViewModel.firebaseMonthsMutableLiveData.observe(viewLifecycleOwner){
            if(it.isNullOrEmpty()){
                binding.ivEmptyMonth.visibility=View.VISIBLE
                binding.tvEmptyMonth.visibility=View.VISIBLE
            }else{
                binding.ivEmptyMonth.visibility=View.GONE
                binding.tvEmptyMonth.visibility=View.GONE
            }
            adapterMonthEvaluation.setMonth(it)


        }






}}