package com.example.kotlin_5pract

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kolin_5pract.ProductViewModelFactory
import com.example.kotlin_5pract.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private lateinit var productViewModel: ProductViewModel
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toListBtn.setOnClickListener{
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }

        val factory = ProductViewModelFactory(requireActivity().application)
        productViewModel = ViewModelProvider(this, factory).get(ProductViewModel::class.java)

        binding.getDataBtn.setOnClickListener {
            if (binding.editTextId.text.toString() == "") {
                Toast.makeText(requireContext(), "Please write id", Toast.LENGTH_SHORT).show()
            } else {
                val edit_id = binding.editTextId.text.toString().toInt()
                productViewModel.getProductById(edit_id)
            }
        }

        productViewModel.productById.observe(viewLifecycleOwner) { product ->
            if (product != null) {
                binding.textView.text = product.title
                productViewModel.insertProduct(product) //Вставляет в бд
            } else {
                binding.textView.text = "Error: Product not found"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}