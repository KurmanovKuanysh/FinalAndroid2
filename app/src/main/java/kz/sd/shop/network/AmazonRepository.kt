package kz.sd.shop.network

import kz.sd.shop.models.ApiResponse

// Репозиторий для взаимодействия с данными Amazon
interface AmazonRepository {
    // Метод для поиска продуктов
    suspend fun searchProducts(query: String): ApiResponse?
}
