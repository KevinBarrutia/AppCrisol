package pe.edu.crisol.libreria.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.edu.crisol.libreria.model.Book
import pe.edu.crisol.libreria.model.OtherBook
import pe.edu.crisol.libreria.model.OtherBookDetails
import pe.edu.crisol.libreria.repository.OtherBookApiRepository
import pe.edu.crisol.libreria.repository.SearchRepository

class HomeViewModel : ViewModel() {

    /*private val searchRepository = SearchRepository()*/
    private val otherBookApiRepository = OtherBookApiRepository()

    private val _category1 = MutableLiveData<List<OtherBook>>()
    val category1: LiveData<List<OtherBook>> = _category1

    private val _category2 = MutableLiveData<List<OtherBook>>()
    val category2: LiveData<List<OtherBook>> = _category2

    private val _category3 = MutableLiveData<List<OtherBook>>()
    val category3: LiveData<List<OtherBook>> = _category3

    private val _category4 = MutableLiveData<List<OtherBook>>()
    val category4: LiveData<List<OtherBook>> = _category4

    private val _navigateToDetails = MutableLiveData<String?>()
    val navigateToDetails: LiveData<String?> = _navigateToDetails

    init {
        loadCategories()
    }

    private fun loadCategories() {
        loadCategory("Combined Print and E-Book Fiction", _category1)
        loadCategory("Combined Print and E-Book Nonfiction", _category2)
        loadCategory("Hardcover Advice", _category3)
        loadCategory("Childrens Middle Grade E-Book", _category4)
    }

    private fun loadCategory(category: String, liveData: MutableLiveData<List<OtherBook>>) {
        otherBookApiRepository.searchBooksByCategory(category).observeForever {
            liveData.value = it
        }
    }

    fun onBookClicked(bookId: String) {
        _navigateToDetails.value = bookId
    }

    fun onDetailsNavigated() {
        _navigateToDetails.value = null
    }
}