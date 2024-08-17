package pe.edu.crisol.libreria.repository

import android.util.Log
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

class DetailsRepository {
    fun searchBookById(detailsRequest: DetailsRequest): LiveData<Book> {
        val book = MutableLiveData<Book>()
        val call = BookClient.bookService.searchBookById(detailsRequest.id)
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

    fun searchBookByIsbn(isbn: String): LiveData<Book> {
        val book = MutableLiveData<Book>()
        val call = BookClient.bookService.searchBookByIsbn("isbn:$isbn")
        call.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                if (response.isSuccessful) {
                    val bookResponse = response.body()
                    Log.i("bookResponse", bookResponse.toString())
                    bookResponse?.let {
                        bookResponse.items?.let {items ->
                            book.value = items[0]
                        }
                    }
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {

            }
        })

        return book
    }
}