package pe.edu.crisol.libreria.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.edu.crisol.libreria.model.Book
import pe.edu.crisol.libreria.repository.SearchRepository
import pe.edu.crisol.libreria.retrofit.BookClient
import pe.edu.crisol.libreria.retrofit.response.DetailsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WishListViewModel: ViewModel() {
    private val repository = SearchRepository()
    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> = _books

    fun fetchBooksByIds(bookIds: List<String>) {
        val bookList = mutableListOf<Book>()

        bookIds.forEach { bookId ->
            repository.searchBookById(bookId).observeForever {book ->
                bookList?.let {
                    bookList.add(book)
                }
                _books.value = bookList
            }
        }
    }

}