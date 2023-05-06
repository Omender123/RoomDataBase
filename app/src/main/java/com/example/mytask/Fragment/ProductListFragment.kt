package com.example.mytask.Fragment

import android.app.Application

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytask.Adapter.ProductAdapter
import com.example.mytask.Model.Response.ProductResponse
import com.example.mytask.R
import com.example.mytask.Utils.NetworkResult
import com.example.mytask.Utils.Utils
import com.example.mytask.ViewModel.ProductViewModel
import com.example.mytask.databinding.FragmentProductListBinding
import com.example.mytask.roomdata.ProductDataViewModelFactory
import com.example.mytask.roomdata.ProductDataViewModelOne
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.doAsync



@AndroidEntryPoint
class ProductListFragment : Fragment(), ProductAdapter.OnClickListener {
    private lateinit var binding: FragmentProductListBinding
    private val ProductViewModel: ProductViewModel by viewModels()
    private lateinit var model: ProductDataViewModelOne

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductListBinding.inflate(inflater, container, false)


        val modelfactory =
            ProductDataViewModelFactory(requireContext().applicationContext as Application)

        model = ViewModelProvider(this, modelfactory).get(ProductDataViewModelOne::class.java)

        if (!Utils.hasInternetConnection(requireContext())) {
            binding.progressCircular.visibility = View.VISIBLE

            model.allProductData.observe(viewLifecycleOwner, Observer {
                binding.progressCircular.visibility = View.GONE
                if (it.size > 0) {
                    setData(it)
                }

            })
        } else {

            ProductViewModel.fetchProductData()
            GetProductDataObservel();
        }





       

        return binding.root
    }

    private fun GetProductDataObservel() {
        binding.progressCircular.visibility = View.VISIBLE

        ProductViewModel.GetProductData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {

                    binding.progressCircular.visibility = View.GONE
                    response.data?.let {
                        setData(it)

                        for (i in 0 until it.size - 1) {

                            doAsync {
                                model.insert(it[i])
                            }


                        }

                        Log.e("dta", it.toString())
                    }

                }

                is NetworkResult.Error -> {
                    binding.progressCircular.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        response.message,
                        Toast.LENGTH_SHORT
                    ).show()

                    Log.e("Error", response.message.toString())
                }

                is NetworkResult.Loading -> {
                    binding.progressCircular.visibility = View.GONE
                }
            }
        }
    }

    override fun OnItemClickListener(Productdata: List<ProductResponse>, position: Int) {

        var data = bundleOf()
        data.putSerializable("ProductData", Productdata[position])
        findNavController().navigate(R.id.action_oneFragment_to_detailsFragment, data)
    }


    fun setData(data: List<ProductResponse>) {
        val mLayoutManager1: RecyclerView.LayoutManager =
            GridLayoutManager(context,2, RecyclerView.VERTICAL, false)
        binding.recyclerView.setLayoutManager(mLayoutManager1)
        binding.recyclerView.setItemAnimator(DefaultItemAnimator())
        var adapter =
            ProductAdapter(data, requireContext(), this);
        binding.recyclerView.setAdapter(adapter)
    }



}