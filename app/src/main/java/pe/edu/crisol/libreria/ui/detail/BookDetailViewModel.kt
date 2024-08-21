package pe.edu.crisol.libreria.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pe.edu.crisol.libreria.data.repository.BookRepository
import pe.edu.crisol.libreria.domain.model.Book

class BookDetailViewModel(private val bookRepository: BookRepository) : ViewModel() {
    private val _bookDetail = MutableLiveData<Book?>()
    val bookDetail: LiveData<Book?> get() = _bookDetail

    fun loadBookDetail(bookId: String) {

    }

}