package com.example.chatwiseassign.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatwiseassign.R
import com.example.chatwiseassign.data.adapter.ProductAdapter
import com.example.chatwiseassign.data.repository.ProductRepository
import com.example.chatwiseassign.databinding.FragmentProductListFragmentBinding
import com.example.chatwiseassign.ui.ProductViewModel
import com.example.chatwiseassign.ui.ProductViewModelProviderFactory
import kotlinx.coroutines.launch


class ProductListFragment : Fragment() {
    private lateinit var viewModel: ProductViewModel
    private lateinit var adapter: ProductAdapter
    private lateinit var binding: FragmentProductListFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductListFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = ProductRepository()
        val viewModelProviderFactory = ProductViewModelProviderFactory(repository)

        // Initialize the ViewModel using the custom factory
        viewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(ProductViewModel::class.java)


        // Initialize RecyclerView

        adapter = ProductAdapter(arrayListOf()) { view,product ->
            val extras = FragmentNavigatorExtras(view to "transition_1")
            findNavController().navigate(
                R.id.action_productListFragment_to_productDetialFragment,
                Bundle().apply {
                    putParcelable("PRODUCT", product)
                })
        }

        binding.rvProduct.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@ProductListFragment.adapter
        }

        // Observe LiveData from ViewModel
        viewModel.products.observe(viewLifecycleOwner) { products ->
            viewLifecycleOwner.lifecycleScope.launch {
                adapter.update(products)
            }
        }

//         Load products
        viewModel.loadProducts()
    }
}