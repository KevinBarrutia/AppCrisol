package pe.edu.crisol.libreria.data.source.googlebooks

import android.util.Log
import pe.edu.crisol.libreria.domain.model.Book
import javax.inject.Inject
import kotlin.math.roundToInt
import kotlin.random.Random

class GoogleBooksDataSource @Inject constructor(
    private val apiService: GoogleBooksApiService
) {
    suspend fun getBooks(query: String, page: Int): List<Book> {
        val startIndex = (page - 1) * 10
        val response = apiService.getBooks(query, startIndex)
        Log.d("GoogleBooksDataSource", "API Response: $response")
        return response.items?.map { it.toBook() } ?: emptyList()
    }

    suspend fun getBook(id: String): Book {
        val response = apiService.getBook(id)
        return response.toBook()
    }

    private fun BookResponse.toBook(): Book {
        return Book(
            id = this.id,
            isbn = this.volumeInfo.industryIdentifiers?.get(0)?.identifier ?: "Unknown",
            title = this.volumeInfo.title,
            subtitle = this.volumeInfo.subtitle ?: "",
            authors = this.volumeInfo.authors ?: emptyList(),
            publisher = this.volumeInfo.publisher ?: "Unknown",
            publishedDate = this.volumeInfo.publishedDate ?: "Unknown",
            description = this.volumeInfo.description ?: "No Description",
            categories = this.volumeInfo.categories.orEmpty(),
            price = this.saleInfo.listPrice?.amount ?: 98.9,
            cover = this.volumeInfo.imageLinks?.thumbnail.orEmpty(),
            rating = 4.8
        )
    }
}