package pe.edu.crisol.libreria.retrofit

import pe.edu.crisol.libreria.retrofit.response.OtherBookApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OtherBookApiService {
    @GET("lists/current/{list}.json?api-key=hmbCDnMrUvN0Oq0o7n5JvjW0l2YkIeQF")
    fun searchBooksByCategory(@Path("list") list: String): Call<OtherBookApiResponse>
}