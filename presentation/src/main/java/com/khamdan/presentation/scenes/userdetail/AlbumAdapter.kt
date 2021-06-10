package com.khamdan.presentation.scenes.userdetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khamdan.domain.model.Album
import com.khamdan.domain.model.Photo
import com.khamdan.presentation.R.layout
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.android.synthetic.main.album_item.view.rv_photo
import kotlinx.android.synthetic.main.album_item.view.tv_album_name

class AlbumAdapter : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    val photoClickIntent: PublishSubject<Photo> = PublishSubject.create()

    var data: MutableList<Album> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun setData(position: Int, Album: Album) {
        data[position] = Album
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(layout.album_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(data[position], photoClickIntent)

    override fun getItemCount() = data.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(
            album: Album,
            photoClickIntent: PublishSubject<Photo>
        ) = with(itemView) {
            tv_album_name.text = album.title
            rv_photo.adapter = PhotoAdapter(photoClickIntent).apply {
                data = album.photos.toMutableList()
            }
        }
    }
}