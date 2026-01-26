package com.example.myapplication.ui.catalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.CatalogItem

class CatalogAdapter(
    private val onItemClick: (CatalogItem) -> Unit
) : RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder>() {

    private var items: List<CatalogItem> = emptyList()

    fun submit(list: List<CatalogItem>) {
        items = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_catalog, parent, false)
        return CatalogViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    inner class CatalogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.textName)

        fun bind(item: CatalogItem) {
            nameTextView.text = item.name

            itemView.setOnClickListener {
                onItemClick(item)
            }
        }
    }
}