package pe.edu.crisol.libreria.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import pe.edu.crisol.libreria.data.source.googlebooks.GoogleBooksPagingSource
import pe.edu.crisol.libreria.domain.model.Book
import pe.edu.crisol.libreria.domain.usecase.LoadNextPageUseCase
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val loadNextPageUseCase: LoadNextPageUseCase
) : ViewModel() {

    private val _searchResults = MutableStateFlow<PagingData<Book>>(PagingData.empty())
    val searchResults: StateFlow<PagingData<Book>> = _searchResults

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun searchBooks(query: String) {
        viewModelScope.launch {
            Pager(PagingConfig(pageSize = 10)) {
                GoogleBooksPagingSource(loadNextPageUseCase, "intitle:$query")
            }.flow.cachedIn(viewModelScope).catch { e ->
                    _errorMessage.value = "Error: ${e.message}"
                }.collectLatest { pagingData ->
                    _searchResults.value = pagingData

                }
        }
    }

    fun errorMessageHandled() {
        _errorMessage.value = null
    }
}