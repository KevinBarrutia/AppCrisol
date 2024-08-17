package pe.edu.crisol.libreria.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object OtherBookApiClient {
    private const val BASE_URL = "https://api.nytimes.com/svc/books/v3/"

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

    val otherBookApiService: OtherBookApiService by lazy {
        buildRetrofit().create(OtherBookApiService::class.java)
    }
}