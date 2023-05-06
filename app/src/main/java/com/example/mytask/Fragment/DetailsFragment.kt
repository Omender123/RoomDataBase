package com.example.mytask.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.mytask.Model.Response.ProductResponse
import com.example.mytask.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    var ProductData: ProductResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        ProductData = arguments?.getSerializable("ProductData") as? ProductResponse
        Log.e("dashboradResponse", "shshsh" + ProductData)

        Glide.with(requireActivity())
            .asBitmap()
            .load(ProductData?.image)
            .thumbnail(0.05f)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.image)

        binding.name.text = ProductData?.title
        binding.txtDes.text = ProductData?.description
        binding.price.text = "Rs. " + ProductData?.price


        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
        }

        return binding.root
    }


}