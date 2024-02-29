package com.robby.news.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.robby.news.R
import com.robby.news.databinding.AdapterCategoryBinding
import com.robby.news.util.Constant.ZERO

class CategoryAdapter(
    private var categories: List<CategoryModel>,
    private var listener: OnAdapterListener,
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private val items = arrayListOf<TextView>()

    class ViewHolder(val binding: AdapterCategoryBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        AdapterCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.binding.category.text = category.name
        items.add( holder.binding.category )
        holder.itemView.setOnClickListener {
            listener.onClick( category )
            setColor( holder.binding.category )
        }
        setColor(items[ZERO]) /// first load
    }

    interface OnAdapterListener {
        fun onClick(category: CategoryModel)
    }

    private fun setColor(textView: TextView){
        items.forEach {
            it.setBackgroundResource(R.color.white)
        }
        textView.setBackgroundResource(android.R.color.darker_gray)
    }
}