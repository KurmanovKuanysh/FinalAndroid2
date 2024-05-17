package kz.sd.shop.network

import kz.sd.shop.models.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// Интерфейс для взаимодействия с API Amazon
interface AmazonApi {
    // Метод для поиска продуктов
    @GET("search")
    suspend fun searchProducts(
        @Query("query") query: String,  // Параметр запроса: поисковый запрос
        @Query("page") page: String,    // Параметр запроса: номер страницы
        @Query("country") country: String  // Параметр запроса: страна
    ): Response<ApiResponse>
}
