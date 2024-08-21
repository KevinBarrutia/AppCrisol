package pe.edu.crisol.libreria.domain.model

import androidx.paging.PagingData

data class Section(
    val title: String,
    val pagingData: PagingData<Book>
)