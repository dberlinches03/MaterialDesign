package com.example.shrine

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shrine.network.ProductEntry

class ProductGridFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.shr_product_grid_fragment, container, false)
        val appBar = view.findViewById<Toolbar>(R.id.app_bar)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        (activity as AppCompatActivity).setSupportActionBar(appBar)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)

        val adapter = ProductCardRecyclerViewAdapter (
            ProductEntry.initProductEntryList(resources))
        recyclerView.adapter = adapter

        val largePadding = resources.getDimensionPixelSize(R.dimen.shr_product_grid_spacing)
        val smallPadding = resources.getDimensionPixelSize(R.dimen.shr_product_grid_spacing_small)
        recyclerView.addItemDecoration(ProductGridItemDecoration(largePadding, smallPadding))

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.shr_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}
