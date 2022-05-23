package com.arEgTA.futsureacademy.ui.home.ui.monthlyEvaluation

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
import com.arEgTA.futsureacademy.utils.Constants

class AdapterMonthEvaluation(
    var listMonth: List<Month>,
    var setNavigation: ((Month) -> Unit)? = null,
    var deleteMonth: ((Month) -> Unit)? = null,
    var updateMonth: ((Month) -> Unit)? = null
) :

    RecyclerView.Adapter<AdapterMonthEvaluation.ViewHolder>() {


    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val tvDateItem: TextView
            get() = view.findViewById(R.id.tvDateItem)
        val tvSeasonItem: TextView
            get() = view.findViewById(R.id.tvSeasonItem)
        val tvTotalEvaluationItem: TextView
            get() = view.findViewById(R.id.tvTotalEvaluationItem)
        val contentMonth: ConstraintLayout
            get() = view.findViewById(R.id.contentMonth)
        val ivDelete: ImageView
            get() = view.findViewById(R.id.ivDelete)
        val ivUpdate: ImageView
            get() = view.findViewById(R.id.ivUpdate)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viwe: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_month_evaluation, parent, false)
        return ViewHolder(viwe)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvDateItem.text = listMonth.get(position).date
        holder.tvSeasonItem.text = listMonth.get(position).season
        holder.tvTotalEvaluationItem.text = "%${listMonth.get(position).totalEvaluation}"
        holder.contentMonth.setOnClickListener {
            setNavigation?.invoke(listMonth[position])
        }
        // get user id from sharedPreferences
        val sharedPreferences =
            holder.contentMonth.context.getSharedPreferences("sharedPrefFile", Context.MODE_PRIVATE)
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
            deleteMonth?.invoke(listMonth[position])

        }
        holder.ivUpdate.setOnClickListener {
            updateMonth?.invoke(listMonth[position])

        }


    }

    fun setMonth(month: List<Month>) {
        this.listMonth = month
        notifyDataSetChanged()

    }


    override fun getItemCount(): Int = listMonth.size
}