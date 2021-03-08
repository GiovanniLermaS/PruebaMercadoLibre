package com.example.pruebamercadolibre.view.result.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pruebamercadolibre.R
import com.example.pruebamercadolibre.db.model.Result


class ResultAdapter(
    private val context: Context,
    private val listSearchBySite: ArrayList<Result>?
) :
    RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    class ViewHolder(itemViewGroup: ViewGroup) : RecyclerView.ViewHolder(itemViewGroup)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_products_search, parent, false) as ConstraintLayout
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(listSearchBySite!![position].thumbnail)
            .placeholder(
                ResourcesCompat.getDrawable(
                    context.resources,
                    R.drawable.placeholder,
                    null
                )
            )
            .fitCenter()
            .into(holder.itemView.findViewById(R.id.ivPrincipalImage))
        holder.itemView.findViewById<TextView>(R.id.tvPrincipalTitle).text =
            listSearchBySite[position].title
        holder.itemView.findViewById<TextView>(R.id.tvPrincipalPrice).text =
            listSearchBySite[position].price.toString()
    }

    override fun getItemCount() = listSearchBySite?.size!!
}