package pe.edu.crisol.libreria.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pe.edu.crisol.libreria.model.Book
import pe.edu.crisol.libreria.retrofit.BookClient
import pe.edu.crisol.libreria.retrofit.request.DetailsRequest
import pe.edu.crisol.libreria.retrofit.response.DetailsResponse
import pe.edu.crisol.libreria.retrofit.response.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchRepository {
    fun searchBooksByCategory(category: String): LiveData<List<Book>> {
        val bookList = MutableLiveData<List<Book>>()

        val call = BookClient.bookService.searchBooks("subject:$category")
        call.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                if (response.isSuccessful) {
                    val bookListResponse = response.body()
                    bookList.value = bookListResponse?.items ?: emptyList()
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
            }
        })

        return bookList
    }

    fun searchBooksByName(name: String): LiveData<List<Book>> {
        val bookList = MutableLiveData<List<Book>>()

        val call = BookClient.bookService.searchBooks(name)
        call.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                if (response.isSuccessful) {
                    val bookListResponse = response.body()
                    bookList.value = bookListResponse?.items ?: emptyList()
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
            }
        })

        return bookList
    }

    fun searchBookById(bookId: String): LiveData<Book> {
        val book = MutableLiveData<Book>()
        val call = BookClient.bookService.searchBookById(bookId)
        call.enqueue(object : Callback<DetailsResponse> {
            override fun onResponse(call: Call<DetailsResponse>, response: Response<DetailsResponse>) {
                if (response.isSuccessful) {
                    val bookResponse = response.body()
                    bookResponse?.let {
                        book.value = bookResponse.toBook()
                    }
                }
            }

            override fun onFailure(call: Call<DetailsResponse>, t: Throwable) {

            }
        })

        return book
    }


}