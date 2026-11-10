package com.example.lec7_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFragment : Fragment() {

    private val itemList = listOf(
        Item(1, "Android Jetpack", "Learn about Architecture Components, Lifecycle, and Fragments.", "Development"),
        Item(2, "Kotlin Coroutines", "Asynchronous programming and non-blocking threads in Kotlin.", "Programming"),
        Item(3, "Material Design 3", "Modern UI components and theming for Android applications.", "Design"),
        Item(4, "Room Database", "SQLite database abstraction library for Android persistence.", "Database")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = ItemAdapter(itemList) { item ->
            val detailFragment = DetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("item", item)
                }
            }
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, detailFragment)
                .addToBackStack(null)
                .commit()
        }
        return view
    }
}

class ItemAdapter(
    private val items: List<Item>,
    private val onClick: (Item) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvCategory: TextView = view.findViewById(R.id.tvCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvTitle.text = item.title
        holder.tvCategory.text = item.category
        holder.itemView.setOnClickListener { onClick(item) }
    }

    override fun getItemCount() = items.size
}
