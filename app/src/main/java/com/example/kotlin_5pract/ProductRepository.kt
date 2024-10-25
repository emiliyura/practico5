package com.example.kotlin_5pract

import androidx.lifecycle.LiveData
import com.example.kotlin_5pract.api.ProductApi
import com.example.kotlin_5pract.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductRepository(private val productDao: ProductDao, private val productApi: ProductApi) {
    // LiveData, которая содержит список всех продуктов из локальной базы данных
    val allProducts: LiveData<List<Product>> = productDao.getAllProducts()

    // Метод для вставки нового продукта в локальную базу данных
    suspend fun insert(product: Product) {
        productDao.insertProduct(product)
    }

    suspend fun update(product: Product) {
        productDao.updateProduct(product)
    }

    suspend fun delete(product: Product) {
        productDao.deleteProduct(product)
    }


}
