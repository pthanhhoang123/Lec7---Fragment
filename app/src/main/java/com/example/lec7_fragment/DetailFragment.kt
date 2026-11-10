package com.example.lec7_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class DetailFragment : Fragment() {

    private var item: Item? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            item = it.getSerializable("item") as? Item
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        val tvTitle = view.findViewById<TextView>(R.id.tvDetailTitle)
        val tvCategory = view.findViewById<TextView>(R.id.tvDetailCategory)
        val tvDescription = view.findViewById<TextView>(R.id.tvDetailDescription)
        val btnBack = view.findViewById<Button>(R.id.btnBack)

        item?.let {
            tvTitle.text = it.title
            tvCategory.text = "Danh mục: ${it.category}"
            tvDescription.text = it.description
        }

        btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        return view
    }
}
