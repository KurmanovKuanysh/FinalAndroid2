package kz.sd.shop.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Модуль Dagger для настройки сетевого взаимодействия с Amazon API
@InstallIn(SingletonComponent::class)
@Module
object NetworkAmazonModule {

    // Базовый URL для API Amazon
    private const val baseUrl = "https://real-time-amazon-data.p.rapidapi.com/"

    // Инициализация перехватчика логирования для OkHttp
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Создание экземпляра OkHttpClient с добавлением перехватчиков и настройками
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader("X-RapidAPI-Key", "d371bee275msh661c019daf4c844p1e5065jsn28f406267cfc")
                .addHeader("X-RapidAPI-Host", "real-time-amazon-data.p.rapidapi.com")
                .build()
            chain.proceed(request)
        }.build()

    // Создание экземпляра Retrofit для взаимодействия с API
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    // Предоставление экземпляра AmazonApi в виде зависимости Dagger
    @Provides
    @Singleton
    fun getApi(): AmazonApi {
        return getRetrofit()
            .create(AmazonApi::class.java)
    }
}
