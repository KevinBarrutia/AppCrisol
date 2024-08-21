package pe.edu.crisol.libreria.ui.detail


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.edu.crisol.libreria.domain.model.Book
import pe.edu.crisol.libreria.domain.usecase.GetBookDetailsUseCase
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel@Inject constructor(
    private val getBookDetailsUseCase: GetBookDetailsUseCase
)  : ViewModel() {
    private val _book = MutableLiveData<Book>()
    val book: LiveData<Book> get() = _book

    fun loadBookDetails(bookId: String) {
        viewModelScope.launch {
            val bookDetails = getBookDetailsUseCase(bookId)
            _book.value = bookDetails
        }
    }
}