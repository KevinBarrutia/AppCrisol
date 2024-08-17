package pe.edu.crisol.libreria.viewModel

/*import android.util.Log
import androidx.lifecycle.LiveData*/
import android.util.Log
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.edu.crisol.libreria.model.Book
import pe.edu.crisol.libreria.repository.SearchRepository
import pe.edu.crisol.libreria.retrofit.request.SearchRequest
import pe.edu.crisol.libreria.retrofit.response.SearchResponse

class SearchViewModel : ViewModel() {
    private val repository = SearchRepository()

    private val _bookList = MutableLiveData<List<Book>>()
    val bookList: LiveData<List<Book>> = _bookList

    private val _navigateToDetails = MutableLiveData<String?>()
    val navigateToDetails: LiveData<String?> = _navigateToDetails

    fun searchBooks(q: String) {
        repository.searchBooksByName(q).observeForever {
            _bookList.value = it
        }
    }

    fun onBookClicked(bookId: String) {
        _navigateToDetails.value = bookId
    }

    fun onDetailsNavigated() {
        _navigateToDetails.value = null
    }

}
