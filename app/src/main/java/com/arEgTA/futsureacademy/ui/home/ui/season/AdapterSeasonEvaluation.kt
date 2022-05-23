package com.arEgTA.futsureacademy.ui.home.ui.season

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.arEgTA.futsureacademy.R
import com.arEgTA.futsureacademy.model.Month
import com.arEgTA.futsureacademy.model.Season
import com.arEgTA.futsureacademy.utils.Constants
import com.bumptech.glide.util.Util
import com.google.android.material.bottomnavigation.BottomNavigationView

class AdapterSeasonEvaluation(
    var listSeason: List<Season>,
    var setNavigation: ((Season) -> Unit)? = null,
    var deleteSeason: ((String) -> Unit)? = null,
    var updateSeason: ((Season) -> Unit)? = null
) :
    RecyclerView.Adapter<AdapterSeasonEvaluation.ViewHolder>() {


    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val tvLeaderSeason: TextView
            get() = view.findViewById(R.id.tvLeaderSeason)
        val tvNameSeason: TextView
            get() = view.findViewById(R.id.tvNameSeason)
        val tvTotalEvaluationSeason: TextView
            get() = view.findViewById(R.id.tvTotalEvaluationSeason)
        val contentSeason: ConstraintLayout
            get() = view.findViewById(R.id.contentSeason)
        val ivDelete: ImageView
            get() = view.findViewById(R.id.ivDelete)
        val ivUpdate: ImageView
            get() = view.findViewById(R.id.ivUpdate)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viwe: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_season_evaluation, parent, false)
        return ViewHolder(viwe)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvNameSeason.text = listSeason.get(position).season.toString()
        holder.tvLeaderSeason.text = listSeason.get(position).leader.toString()
        holder.tvTotalEvaluationSeason.text = "%${listSeason.get(position).totalEvaluation}"
        holder.contentSeason.setOnClickListener {
            setNavigation?.invoke(listSeason.get(position))
        }
        // get user id from sharedPreferences
        val sharedPreferences = holder.contentSeason.context.getSharedPreferences(
            "sharedPrefFile",
            Context.MODE_PRIVATE
        )
        var idUser = sharedPreferences.getString(Constants.LOGINID, "0")

        //These ids belong to the admin
        if (idUser.equals("7VrTy19p9OOWG8qVXoasfUz9Esh2") || idUser.equals("TsEOWcKTFyZ5glTnYRlRgyhulN62")) {
            holder.ivDelete.visibility = View.VISIBLE
            holder.ivUpdate.visibility = View.VISIBLE

        } else {
            holder.ivDelete.visibility = View.GONE
            holder.ivUpdate.visibility = View.GONE
        }
        holder.ivDelete.setOnClickListener {
            deleteSeason?.invoke(listSeason[position].date)

        }
        holder.ivUpdate.setOnClickListener {
            updateSeason?.invoke(listSeason[position])

        }


    }

    fun setSeason(season: List<Season>) {

        this.listSeason = season
        notifyDataSetChanged()


    }


    override fun getItemCount(): Int = listSeason.size
}