package com.arEgTA.futsureacademy.ui.home.ui.admin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.arEgTA.futsureacademy.R
import com.arEgTA.futsureacademy.model.Admin
import com.arEgTA.futsureacademy.model.Month

class AdapterAdmin(
var listUser: List<Admin>,
var setNavigation:((Admin)->Unit)?=null,
var deleteUser:((String)->Unit)?=null) :
RecyclerView.Adapter<AdapterAdmin.ViewHolder>() {


    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val tvNameAdmin: TextView
            get() = view.findViewById(R.id.tvNameAdmin)
        val tvEmailAdmin: TextView
            get() = view.findViewById(R.id.tvEmailAdmin)

        val tvGroupAdmin: TextView
            get() = view.findViewById(R.id.tvGroupAdmin)

        val contentAdmin: LinearLayout
            get() = view.findViewById(R.id.contentAdmin)
        val btDelete: Button
            get() = view.findViewById(R.id.btDelete)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viwe: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_admin, parent, false)
        return ViewHolder(viwe)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        listUser.get(position).apply {
            holder.tvEmailAdmin.text="${holder.tvEmailAdmin.context.getString(R.string.email)}$email"
            holder.tvNameAdmin.text="${holder.tvEmailAdmin.context.getString(R.string.name2)}$name"
            holder.tvGroupAdmin.text="${holder.tvNameAdmin.context.getString(R.string.group)}$groupName"
        }

        holder.contentAdmin.setOnClickListener {
            setNavigation?.invoke(listUser[position])
        }
        holder.btDelete.setOnClickListener {
            deleteUser?.invoke(listUser[position].id)
        }


    }

    fun setAdmin(admin: List<Admin>) {
        if (!admin.isEmpty()) {
            this.listUser = admin
            notifyDataSetChanged()
        } else
            notifyDataSetChanged()

    }


    override fun getItemCount(): Int = listUser.size
}