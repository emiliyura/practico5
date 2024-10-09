package com.example.kotlin_5pract

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kolin_5pract.ProductViewModelFactory
import com.example.kotlin_5pract.databinding.FragmentSecondBinding
import com.example.kotlin_5pract.model.Product

class SecondFragment : Fragment() {
    private var _binding : FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private lateinit var productViewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.to1fragmentBtn.setOnClickListener{
            findNavController().navigate(R.id.action_secondFragment_to_firstFragment)
        }

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val database = AppDatabase.getDatabase(requireContext())
        val viewModelFactory = ProductViewModelFactory(requireActivity().application)
        productViewModel = ViewModelProvider(this, viewModelFactory).get(ProductViewModel::class.java)

        productViewModel.allProducts.observe(viewLifecycleOwner, { products ->
            val adapter = ProductAdapter(products)
            recyclerView.adapter = adapter
        })

    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}