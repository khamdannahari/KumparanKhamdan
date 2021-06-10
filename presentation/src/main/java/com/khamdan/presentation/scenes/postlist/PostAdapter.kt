package com.khamdan.presentation.scenes.postlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khamdan.domain.model.Post
import com.khamdan.presentation.R.layout
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.android.synthetic.main.post_item.view.tv_body
import kotlinx.android.synthetic.main.post_item.view.tv_company
import kotlinx.android.synthetic.main.post_item.view.tv_name
import kotlinx.android.synthetic.main.post_item.view.tv_title

class PostAdapter : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    val postClickIntent: PublishSubject<Post> = PublishSubject.create()

    var data: MutableList<Post> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun setData(position: Int, post: Post) {
        data[position] = post
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(layout.post_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(data[position], postClickIntent)

    override fun getItemCount() = data.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(
            post: Post,
            postClickIntent: PublishSubject<Post>
        ) = with(itemView) {
            tv_title.text = post.title
            tv_body.text = post.body
            tv_name.text = post.user?.name ?: ""
            tv_company.text = post.user?.company?.name ?: ""
            setOnClickListener { postClickIntent.onNext(post) }
        }
    }
}