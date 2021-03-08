package com.example.pruebamercadolibre.view.result.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pruebamercadolibre.R
import com.example.pruebamercadolibre.db.model.Result
import com.example.pruebamercadolibre.util.IDS_INTENT
import com.example.pruebamercadolibre.util.formatter
import com.example.pruebamercadolibre.view.detail.DetailActivity


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
            formatter.format(listSearchBySite[position].price)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(IDS_INTENT, listSearchBySite[position].id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = listSearchBySite?.size!!
}