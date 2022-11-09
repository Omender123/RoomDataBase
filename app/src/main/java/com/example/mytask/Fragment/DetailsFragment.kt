package com.example.mytask.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mytask.Model.Response.PostResponse
import com.example.mytask.R
import com.example.mytask.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
     var postData: PostResponse ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentDetailsBinding.inflate(inflater, container, false)

        postData = arguments?.getSerializable("postData") as? PostResponse
        Log.e("dashboradResponse", "shshsh" + postData)

        binding.txtId.text = "Id : "+postData?.id
        binding.txtUserid.text = "UserId : "+postData?.userId
        binding.txtTitle.text = "Title : "+postData?.title
        binding.txtBody.text = "Body : "+postData?.body


        return binding.root
    }


}