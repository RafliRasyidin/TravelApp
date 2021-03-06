package com.rasyidin.travelapp.core.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.rasyidin.travelapp.R
import com.rasyidin.travelapp.core.domain.model.Tourism
import com.rasyidin.travelapp.databinding.ItemListTourismBinding

class TourismAdapter : RecyclerView.Adapter<TourismAdapter.TourismViewHolder>() {

    companion object {

        fun imageLoadingListener(pendingImage: LottieAnimationView): RequestListener<Drawable> {
            return object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    pendingImage.pauseAnimation()
                    pendingImage.visibility = View.GONE
                    return false
                }
            }
        }
    }

    private var listTourism = ArrayList<Tourism>()

    fun setTourism(tourism: List<Tourism>) {
        if (tourism.isNullOrEmpty()) return
        listTourism.clear()
        listTourism.addAll(tourism)
        notifyDataSetChanged()
    }

    var onItemClick: ((Tourism) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourismViewHolder {
        return TourismViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_tourism, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TourismViewHolder, position: Int) {
        val data = listTourism[position]
        val binding = ItemListTourismBinding.bind(holder.itemView)
        with(binding) {
            Glide.with(holder.itemView.context)
                .load(data.image)
                .apply(RequestOptions().override(280, 360))
                .addListener(imageLoadingListener(imgPlaceholder))
                .error(R.drawable.ic_refresh)
                .into(imgTourism)

            tvItemTitle.text = data.name
            tvItemSubtitle.text = data.address
            tvCountLike.text = data.like.toString()

            root.setOnClickListener {
                onItemClick?.invoke(listTourism[position])
            }
        }

    }

    override fun getItemCount(): Int {
        return listTourism.size
    }

    inner class TourismViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}