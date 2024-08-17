package pe.edu.crisol.libreria.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pe.edu.crisol.libreria.model.OtherBook
import pe.edu.crisol.libreria.retrofit.OtherBookApiClient
import pe.edu.crisol.libreria.retrofit.response.OtherBookApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OtherBookApiRepository {
    fun searchBooksByCategory(category: String): LiveData<List<OtherBook>> {
        val bookList = MutableLiveData<List<OtherBook>>()

        val call = OtherBookApiClient.otherBookApiService.searchBooksByCategory(category)
        call.enqueue(object : Callback<OtherBookApiResponse> {
            override fun onResponse(
                call: Call<OtherBookApiResponse>,
                response: Response<OtherBookApiResponse>
            ) {
                Log.i("bookListResponse", response.body().toString())
                if (response.isSuccessful) {
                    val bookListResponse = response.body()
                    Log.i("bookListResponse", bookListResponse.toString())
                    bookList.value = bookListResponse?.results?.books ?: emptyList()
                }
            }

            override fun onFailure(call: Call<OtherBookApiResponse>, t: Throwable) {
                Log.i("bookListResponse", t.toString())
            }
        })

        return bookList
    }
}