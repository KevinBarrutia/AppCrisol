package pe.edu.crisol.libreria.domain.usecase

import android.util.Log
import pe.edu.crisol.libreria.data.source.googlebooks.GoogleBooksDataSource
import pe.edu.crisol.libreria.domain.model.Book
import javax.inject.Inject

class GetBookDetailsUseCase @Inject constructor(private val googleBooksDataSource: GoogleBooksDataSource
) {
    suspend operator fun invoke(id: String): Book {
        return googleBooksDataSource.getBook(id)
    }
}
