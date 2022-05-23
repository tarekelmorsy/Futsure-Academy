package com.arEgTA.futsureacademy.ui.home.ui.season

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.arEgTA.futsureacademy.R
import com.arEgTA.futsureacademy.databinding.FragmentHomeBinding
import com.arEgTA.futsureacademy.model.Season
import com.arEgTA.futsureacademy.ui.home.BaseFragmentHome
import com.arEgTA.futsureacademy.ui.home.ui.monthlyEvaluation.MonthlyEvaluationFragmentDirections
import com.arEgTA.futsureacademy.ui.home.ui.monthlyEvaluation.MonthlyEvaluationViewModel
import com.arEgTA.futsureacademy.utils.Constants

class SeasonFragment : BaseFragmentHome<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private lateinit var adapterSeasonEvaluation: AdapterSeasonEvaluation

    private val seasonViewModel by lazy {
        SeasonViewModel.create(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get all season from firebase
        seasonViewModel.getSeason()

        // get user id from sharedPreferences
        val sharedPreferences =
            requireActivity().getSharedPreferences("sharedPrefFile", Context.MODE_PRIVATE)
        var idUser = sharedPreferences.getString(Constants.LOGINID, "0")

        if (idUser.equals("7VrTy19p9OOWG8qVXoasfUz9Esh2") || idUser.equals("TsEOWcKTFyZ5glTnYRlRgyhulN62")) {
            binding.btAddSeason.visibility = View.VISIBLE
        } else {
            binding.btAddSeason.visibility = View.GONE

        }
        adapterSeasonEvaluation = AdapterSeasonEvaluation(ArrayList(), {
            findNavController().navigate(
                SeasonFragmentDirections.actionNavigationSeasonToSeasoneDetailsFragment(
                    it
                )
            )

        },
            // delete item from list
            {

                val builder = AlertDialog.Builder(activity)
                builder.setTitle("حذف الموسم")
                builder.setMessage(" هل انت متاكد من حذف الموسم؟")
                builder.setPositiveButton("حذف") { dialog, which ->
                    seasonViewModel.deleteSeason(it)
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
            findNavController().navigate(
                SeasonFragmentDirections.actionNavigationSeasonToUpdateSeasonFragment(
                    it
                )
            )


        }
        binding.btAddSeason.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_season_to_addSeasonFragment)

        }
        binding.reSeason.layoutManager = LinearLayoutManager(context)
        binding?.reSeason?.adapter = adapterSeasonEvaluation
        // set all season in Recyclerview
        seasonViewModel.firebaseSeasonMutableLiveData.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) {
                binding.ivEmptySeason.visibility = View.VISIBLE
                binding.tvEmptySeason.visibility = View.VISIBLE
            } else {
                binding.ivEmptySeason.visibility = View.GONE
                binding.tvEmptySeason.visibility = View.GONE
            }
            adapterSeasonEvaluation.setSeason(it)


        }

    }


}