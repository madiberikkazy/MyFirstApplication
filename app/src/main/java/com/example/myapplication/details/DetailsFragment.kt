package com.example.myapplication.details

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.viewmodel.CatalogViewModel

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel: CatalogViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item = viewModel.getItemById(args.itemId)
        item?.let {
            view.findViewById<TextView>(R.id.titleText).text = it.title
            view.findViewById<TextView>(R.id.descText).text = it.desc
            view.findViewById<TextView>(R.id.priceText).text = it.price.toString()

            val favButton = view.findViewById<Button>(R.id.favButton)
            favButton.setOnClickListener {
                viewModel.toggleFav(it.id)
            }
        }
    }
}