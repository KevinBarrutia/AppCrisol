package pe.edu.crisol.libreria.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.crisol.libreria.data.source.googlebooks.GoogleBooksPagingSource
import pe.edu.crisol.libreria.domain.model.Book
import pe.edu.crisol.libreria.domain.usecase.LoadNextPageUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadNextPageUseCase: LoadNextPageUseCase
) : ViewModel() {

    private val sections = mapOf(
        "Los Mejores Libros" to "subject:best_sellers",
        "Magia y Fantasía" to "subject:magia",
        "Historia" to "subject:historia",
        "Guerra" to "subject:guerra",
        "Para todos los públicos" to "subject:familia",
        "Novelas" to "subject:novela",
        "Arte" to "subject:arte",
        "Manga" to "subject:manga"
    )

    val bookSections: Map<String, Flow<PagingData<Book>>> = sections.mapValues { (sectionTitle, query) ->
        Pager(PagingConfig(pageSize = 10)) {
            GoogleBooksPagingSource(loadNextPageUseCase, query)
        }.flow
            .cachedIn(viewModelScope)
    }
}