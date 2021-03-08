package com.example.pruebamercadolibre.view.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.pruebamercadolibre.R
import com.example.pruebamercadolibre.db.model.Picture

class ViewPagerAdapter(
    val context: Context,
    private val listPictures: ArrayList<Picture>
) : PagerAdapter() {

    override fun getCount(): Int {
        return listPictures.size
    }

    override fun isViewFromObject(@NonNull view: View, @NonNull `object`: Any): Boolean {
        return view === `object` as ImageView
    }

    @NonNull
    override fun instantiateItem(@NonNull container: ViewGroup, position: Int): Any {
        val itemView: ImageView =
            (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                R.layout.view_image_detail,
                container,
                false
            ) as ImageView
        Glide.with(itemView).load(listPictures[position].secure_url).into(itemView)
        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ImageView)
    }
}