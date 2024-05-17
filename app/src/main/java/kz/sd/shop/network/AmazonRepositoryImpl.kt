package kz.sd.shop.network

import kz.sd.shop.models.ApiResponse
import javax.inject.Inject
import javax.inject.Singleton

// Реализация репозитория для взаимодействия с данными Amazon
@Singleton
class AmazonRepositoryImpl @Inject constructor(
    val api: AmazonApi
) : AmazonRepository {

    // Метод для поиска продуктов
    override suspend fun searchProducts(query: String): ApiResponse? {
        // Выполнение запроса к API для поиска продуктов
        val response = api.searchProducts(query, "1", "US")

        // Проверка успешности выполнения запроса
        if (response.isSuccessful) {
            // Возвращение тела ответа в случае успешного выполнения
            return response.body()
        } else {
            // В случае неуспешного выполнения запроса выбрасывается исключение с сообщением об ошибке
            throw Exception(response.message())
        }
    }
}
