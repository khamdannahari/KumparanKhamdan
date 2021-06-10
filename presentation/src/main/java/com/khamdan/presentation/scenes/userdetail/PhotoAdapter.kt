package com.khamdan.presentation.scenes.userdetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khamdan.domain.model.Photo
import com.khamdan.presentation.R.layout
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.android.synthetic.main.photo_item.view.iv_photo

class PhotoAdapter(
    private val photoClickIntent: PublishSubject<Photo>
) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    var data: MutableList<Photo> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun setData(position: Int, Photo: Photo) {
        data[position] = Photo
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(layout.photo_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(data[position], photoClickIntent)

    override fun getItemCount() = data.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(
            photo: Photo,
            postClickIntent: PublishSubject<Photo>
        ) = with(itemView) {
            Picasso.get().load(photo.thumbnailUrl).into(iv_photo)
            setOnClickListener { postClickIntent.onNext(photo) }
        }
    }
}