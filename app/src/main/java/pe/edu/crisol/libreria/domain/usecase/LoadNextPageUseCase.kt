package pe.edu.crisol.libreria.domain.usecase

import android.util.Log
import pe.edu.crisol.libreria.data.source.googlebooks.GoogleBooksDataSource
import pe.edu.crisol.libreria.domain.model.Book
import javax.inject.Inject

class LoadNextPageUseCase @Inject constructor(private val googleBooksDataSource: GoogleBooksDataSource
) {
    suspend operator fun invoke(query: String, page: Int): List<Book> {
        Log.d("LoadNextPageUseCase", "Loading books for query: $query, page: $page")
        return googleBooksDataSource.getBooks(query, page)
    }
}