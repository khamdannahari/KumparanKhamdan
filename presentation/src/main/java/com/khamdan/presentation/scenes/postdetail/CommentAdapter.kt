package com.khamdan.presentation.scenes.postdetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khamdan.domain.model.Comment
import com.khamdan.presentation.R.layout
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.android.synthetic.main.comment_item.view.tv_author_name
import kotlinx.android.synthetic.main.comment_item.view.tv_body

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    val commentClickIntent: PublishSubject<Comment> = PublishSubject.create()

    var data: MutableList<Comment> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun setData(position: Int, comment: Comment) {
        data[position] = comment
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(layout.comment_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(data[position], commentClickIntent)

    override fun getItemCount() = data.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(
            comment: Comment,
            postClickIntent: PublishSubject<Comment>
        ) = with(itemView) {
            tv_body.text = comment.body
            tv_author_name.text = comment.name
            setOnClickListener { postClickIntent.onNext(comment) }
        }
    }
}