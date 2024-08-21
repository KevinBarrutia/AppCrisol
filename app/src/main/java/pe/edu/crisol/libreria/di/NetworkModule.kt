package pe.edu.crisol.libreria.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.edu.crisol.libreria.data.source.googlebooks.GoogleBooksApiService
import pe.edu.crisol.libreria.data.source.googlebooks.GoogleBooksDataSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/books/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideGoogleBooksApiService(retrofit: Retrofit): GoogleBooksApiService {
        return retrofit.create(GoogleBooksApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideGoogleBooksDataSource(apiService: GoogleBooksApiService): GoogleBooksDataSource {
        return GoogleBooksDataSource(apiService)
    }
}