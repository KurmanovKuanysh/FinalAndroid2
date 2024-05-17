package kz.sd.shop.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Модуль Dagger для предоставления репозитория в виде зависимости Dagger
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    // Предоставление экземпляра репозитория AmazonRepository в виде зависимости Dagger
    @Singleton
    @Provides
    fun provideRepositoryModule(api: AmazonApi): AmazonRepository {
        return AmazonRepositoryImpl(api)
    }
}
