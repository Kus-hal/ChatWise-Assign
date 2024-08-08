package com.example.chatwiseassign.ui.fragments

import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.chatwiseassign.data.model.Product
import com.example.chatwiseassign.databinding.FragmentProductDetialBinding


class ProductDetialFragment : Fragment() {
    private lateinit var binding: FragmentProductDetialBinding
    private val product: Product? by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("PRODUCT", Product::class.java)
        } else {
            arguments?.getParcelable("PRODUCT")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.explode)
        sharedElementReturnTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.explode)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetialBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        product?.let { model ->
            binding.tvProductName.text =  model.title
            binding.rbProductRating.rating = model.rating.toFloat()
            binding.tvProductDescription.text = model.description
            Glide.with(binding.ivProductImage.context)
                .load(model.images[0])
                .into(binding.ivProductImage)
            binding.tvProductPrice.text = model.price.toString()
        }?: kotlin.run{
            Toast.makeText(requireContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show()
        }

        binding.backBtn.setOnClickListener{
            findNavController().popBackStack()
        }
    }


}