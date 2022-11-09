package com.example.mytask.Fragment

import android.app.Application
import android.content.Intent

import android.os.Build
import android.os.Bundle
import android.provider.Settings
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytask.Adapter.PostAdapter
import com.example.mytask.Model.Response.PostResponse
import com.example.mytask.R
import com.example.mytask.Service.MyAccessibilityService
import com.example.mytask.Utils.NetworkResult
import com.example.mytask.Utils.Utils
import com.example.mytask.ViewModel.PostViewModel
import com.example.mytask.databinding.FragmentOneBinding
import com.example.mytask.roomdata.PostDataViewModelFactory
import com.example.mytask.roomdata.PostDataViewModelOne
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.startService


@AndroidEntryPoint
class OneFragment : Fragment(), PostAdapter.OnClickListener {
    private lateinit var binding: FragmentOneBinding
    private val postViewModel: PostViewModel by viewModels()
    private lateinit var model: PostDataViewModelOne

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOneBinding.inflate(inflater, container, false)


        val modelfactory =
            PostDataViewModelFactory(requireContext().applicationContext as Application)

        model = ViewModelProvider(this, modelfactory).get(PostDataViewModelOne::class.java)

        if (!Utils.hasInternetConnection(requireContext())) {

            model.allpostData.observe(viewLifecycleOwner, Observer {
                if (it.size > 0) {
                    setData(it)
                }

            })
        } else {

            postViewModel.fetchPostData()
            GetPostDataObservel();
        }





        binding.floating.setOnClickListener {
            val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            startActivity(intent)
        }

        return binding.root
    }

    private fun GetPostDataObservel() {

        postViewModel.GetPostData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {

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

                    Toast.makeText(
                        requireContext(),
                        response.message,
                        Toast.LENGTH_SHORT
                    ).show()

                    Log.e("Error", response.message.toString())
                }

                is NetworkResult.Loading -> {

                }
            }
        }
    }

    override fun OnItemClickListener(Postdata: List<PostResponse>, position: Int) {

        var data = bundleOf()
        data.putSerializable("postData", Postdata[position])
        findNavController().navigate(R.id.action_oneFragment_to_detailsFragment, data)
    }


    fun setData(data: List<PostResponse>) {
        val mLayoutManager1: RecyclerView.LayoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerView.setLayoutManager(mLayoutManager1)
        binding.recyclerView.setItemAnimator(DefaultItemAnimator())
        var adapter =
            PostAdapter(data, requireContext(), this);
        binding.recyclerView.setAdapter(adapter)
    }



}