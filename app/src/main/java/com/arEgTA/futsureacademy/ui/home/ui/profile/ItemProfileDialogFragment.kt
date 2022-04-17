package com.arEgTA.futsureacademy.ui.home.ui.profile

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.arEgTA.futsureacademy.R
import com.arEgTA.futsureacademy.databinding.FragmentItemProfileDialogListDialogItemBinding
import com.arEgTA.futsureacademy.databinding.FragmentItemProfileDialogListDialogBinding
import com.arEgTA.futsureacademy.model.Month
import com.arEgTA.futsureacademy.model.Profile
import com.arEgTA.futsureacademy.ui.home.ui.monthlyEvaluation.MonthDetailsFragmentArgs

class ItemProfileDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentItemProfileDialogListDialogBinding? = null
    private val args: ItemProfileDialogFragmentArgs by navArgs()
    private val profileViewModel by lazy {
        ProfileViewModel.create(this)
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentItemProfileDialogListDialogBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var profile: Profile = args.profile
        binding.edGroupUpdateProfile.setText(profile.groupName)
        binding.edLeaderUpdateProfile.setText(profile.leaderName)
        binding.edDateOfJoinUpdateProfile.setText(profile.dateOfJoin)
        binding.edAgeUpdateProfile.setText(profile.age)
        binding.tvNameUpdateProfile.text=profile.name
        binding.btDoneUpdateProfile.setOnClickListener {
            profile.leaderName = binding.edLeaderUpdateProfile.text.toString()
            profile.groupName = binding.edGroupUpdateProfile.text.toString()
            profile.dateOfJoin =   binding.edDateOfJoinUpdateProfile.text.toString()
            profile.age =  binding.edAgeUpdateProfile.text.toString()
            profileViewModel.updateProfile(profile)
            this.dismiss()
        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}