package pe.edu.crisol.libreria.data.repository

import pe.edu.crisol.libreria.data.source.googlebooks.GoogleBooksDataSource
import pe.edu.crisol.libreria.domain.model.Book
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val bookDataSource: GoogleBooksDataSource
) {
    suspend fun getBooks(query: String, page: Int): List<Book> {
        return bookDataSource.getBooks(query, page)
    }
}