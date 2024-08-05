package com.surajpurohit.pagingdemoproject.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.surajpurohit.pagingdemoproject.data.adapter.ProductAdapter
import com.surajpurohit.pagingdemoproject.data.adapter.ProductLoadStateAdapter
import com.surajpurohit.pagingdemoproject.data.model.ProductViewModel
import com.surajpurohit.pagingdemoproject.data.model.ProductViewModelFactory
import com.surajpurohit.pagingdemoproject.data.repository.ProductRepository
import com.surajpurohit.pagingdemoproject.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var viewModel: ProductViewModel
    private lateinit var viewModelFactory: ProductViewModelFactory
    private lateinit var productAdapter: ProductAdapter
    private lateinit var repository: ProductRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        val repository = ProductRepository()
        viewModelFactory = ProductViewModelFactory(repository)

        viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory).get(ProductViewModel::class.java)
        binding.productRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

        productAdapter = ProductAdapter(requireContext())

        binding.productRecyclerView.adapter = productAdapter.withLoadStateHeaderAndFooter(
            header = ProductLoadStateAdapter(),
            footer = ProductLoadStateAdapter()
        )

        viewModel.products.observe(viewLifecycleOwner, Observer {
            productAdapter.submitData(lifecycle, it)
        })

        return binding.root
    }
}