package slavic_api.github.io.android

import slavic_api.github.io.android.data.api.SlavicApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideDeityRepository(apiService: SlavicApiService): DeityRepository {
        return DeityRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideSlavicApiService(retrofit: Retrofit): SlavicApiService {
        return retrofit.create(SlavicApiService::class.java)
    }
}