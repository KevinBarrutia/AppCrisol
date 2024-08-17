package pe.edu.crisol.libreria.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.edu.crisol.libreria.model.Book
import pe.edu.crisol.libreria.repository.DetailsRepository
import pe.edu.crisol.libreria.retrofit.request.DetailsRequest

class DetailsViewModel(private val bookId: String) : ViewModel() {
    /*val bookId = newBookId*/
    private val repository = DetailsRepository()
    private val _book = MutableLiveData<Book>()
    val book: LiveData<Book> = _book

    fun loadBook() {
        if (bookId.all { it.isDigit() }) {
            repository.searchBookByIsbn(bookId).observeForever {
                _book.value = it
            }
        } else {
            repository.searchBookById(DetailsRequest(bookId)).observeForever {
                _book.value = it

            }
        }
    }
}