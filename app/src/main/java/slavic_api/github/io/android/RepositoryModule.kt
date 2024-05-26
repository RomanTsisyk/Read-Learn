package slavic_api.github.io.android

import android.content.Context
import androidx.room.Room
import slavic_api.github.io.android.data.api.SlavicApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import slavic_api.github.io.android.data.database.AppDatabase
import slavic_api.github.io.android.data.database.DeityDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "deities.db"
        ).build()
    }

    @Provides
    fun provideDeityDao(db: AppDatabase): DeityDao {
        return db.deityDao()
    }

    @Provides
    @Singleton
    fun provideDeityRepository(apiService: SlavicApiService, deityDao: DeityDao): DeityRepository {
        return DeityRepository(apiService, deityDao)
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