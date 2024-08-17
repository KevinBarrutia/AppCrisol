package pe.edu.crisol.libreria.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object BookClient {
    private const val BASE_URL = "https://www.googleapis.com/books/v1/"

    private var retrofitClient = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.MINUTES)
        .readTimeout(10, TimeUnit.MINUTES)
        .writeTimeout(15, TimeUnit.MINUTES)
        .build()

    private fun buildRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(retrofitClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val bookService: BookService by lazy {
        buildRetrofit().create(BookService::class.java)
    }
}