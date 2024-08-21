package pe.edu.crisol.libreria.data.source.googlebooks

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GoogleBooksApiService {
    @GET("volumes")
    suspend fun getBooks(
        @Query("q") query: String,
        @Query("startIndex") startIndex: Int = 0,
        @Query("maxResults") maxResults: Int = 10
    ): BooksResponse

    @GET("volumes/{volumeId}")
    suspend fun getBook(
        @Path("volumeId") volumeId: String
    ): BookResponse

}