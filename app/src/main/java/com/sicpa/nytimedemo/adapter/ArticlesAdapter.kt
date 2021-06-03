package com.sicpa.nytimedemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sicpa.nytimedemo.databinding.ArticleItemBinding
import com.sicpa.nytimedemo.domain.models.Article
import javax.inject.Inject

class ArticlesAdapter @Inject constructor() :
    RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {

    private var data: List<Article>? = null

    var list: List<Article>?
        get() = data
        set(value) {
            data = value
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ArticleItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list?.let {
            holder.bindView(it[position])
        }
    }

    override fun getItemCount(): Int = list?.size ?: 0

    inner class ViewHolder(private val binding: ArticleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(data: Article) {
            binding.title.text = data.title
            binding.publishedDate.text = data.publishedDate
        }
    }
}
