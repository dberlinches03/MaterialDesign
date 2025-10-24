package com.example.shrine

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shrine.network.ProductEntry
import com.example.shrine.staggeredgridlayout.StaggeredProductCardRecyclerViewAdapter

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
        val productGrid = view.findViewById<NestedScrollView>(R.id.product_grid)

        (activity as AppCompatActivity).setSupportActionBar(appBar)
        appBar.setNavigationOnClickListener(NavigationIconClickListener(
            activity!!,
            productGrid,
            AccelerateDecelerateInterpolator(),
            ContextCompat.getDrawable(context!!, R.drawable.shr_branded_menu),
            ContextCompat.getDrawable(context!!, R.drawable.shr_close_menu)
        ))

        recyclerView.setHasFixedSize(true)

        val gridLayoutManager = GridLayoutManager(context, 2, RecyclerView.HORIZONTAL, false)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position % 3 == 2) 2 else 1
            }
        }
        recyclerView.layoutManager = gridLayoutManager
        val adapter = StaggeredProductCardRecyclerViewAdapter (
            ProductEntry.initProductEntryList(resources))
        recyclerView.adapter = adapter
        val largePadding = resources.getDimensionPixelSize(R.dimen.shr_product_grid_spacing)
        val smallPadding = resources.getDimensionPixelSize(R.dimen.shr_product_grid_spacing_small)
        recyclerView.addItemDecoration(ProductGridItemDecoration(largePadding, smallPadding))


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            productGrid.background = context?.getDrawable(R.drawable.shr_product_grid_background_shape)
        }
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.shr_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}
