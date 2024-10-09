package com.example.kotlin_5pract

import androidx.lifecycle.*
import com.example.kotlin_5pract.api.ProductApi
import com.example.kotlin_5pract.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {
    val allProducts: LiveData<List<Product>> = repository.allProducts

    private val _productById = MutableLiveData<Product>()
    val productById: LiveData<Product>
        get() = _productById


    fun getProductById(id: Int) {
        val productApi = Retrofit.Builder()
            .baseUrl("https://dummyjson.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val product = productApi.getProductById(id)
            _productById.postValue(product)
        }
    }

    fun insertProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(product)
        }
    }


    fun updateProduct(product: Product) {
        viewModelScope.launch {
            repository.update(product)
        }
    }

    fun deleteProduct(product: Product) {
        viewModelScope.launch {
            repository.delete(product)
        }
    }
}
