package com.robby.news.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robby.news.databinding.AdapterHeadlineBinding
import com.robby.news.databinding.AdapterNewsBinding
import com.robby.news.source.news.ArticleModel
import com.robby.news.util.FormatUtil

private const val HEADLINES = 1
private const val NEWS = 2

class NewsAdapter(
    var articles: ArrayList<ArticleModel>,
    var listener: OnAdapterListener,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        var VIEW_TYPE = 1
    }

    class ViewHolderHeadlines(val binding: AdapterHeadlineBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticleModel){
            binding.article = article
            binding.format = FormatUtil()
        }
    }

    class ViewHolderNews(val binding: AdapterNewsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticleModel){
            binding.article = article
            binding.format = FormatUtil()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HEADLINES) {
            ViewHolderHeadlines (
                AdapterHeadlineBinding.inflate( LayoutInflater.from(parent.context), parent, false )
            )
        } else ViewHolderNews (
            AdapterNewsBinding.inflate( LayoutInflater.from(parent.context), parent, false )
        )
    }

    override fun getItemViewType(position: Int) = VIEW_TYPE

    override fun getItemCount() = articles.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val article = articles[position]
        if (VIEW_TYPE == HEADLINES) (holder as ViewHolderHeadlines).bind( article )
        else (holder as ViewHolderNews).bind( article )
        holder.itemView.setOnClickListener {
            listener.onClick( article )
        }
    }

    fun add(data: List<ArticleModel>) {
        articles.addAll(data)
        notifyItemRangeInserted((articles.size - data.size), data.size)
    }

    fun clear() {
        articles.clear()
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(article: ArticleModel)
    }
}