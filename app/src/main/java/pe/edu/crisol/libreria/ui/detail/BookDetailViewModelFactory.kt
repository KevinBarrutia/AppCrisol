package pe.edu.crisol.libreria.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pe.edu.crisol.libreria.data.repository.BookRepository

class BookDetailViewModelFactory(private val bookRepository: BookRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookDetailViewModel::class.java))
            return BookDetailViewModel(bookRepository) as T
        throw IllegalArgumentException("Unknown ViewModel")
    }
}