package pe.edu.crisol.libreria.data.source.googlebooks

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import pe.edu.crisol.libreria.domain.model.Book
import pe.edu.crisol.libreria.domain.usecase.LoadNextPageUseCase

class GoogleBooksPagingSource(
    private val loadNextPageUseCase: LoadNextPageUseCase,
    private val query: String
) : PagingSource<Int, Book>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {
        val page = params.key ?: 1
        Log.d("GoogleBooksPagingSource", "Calling LoadNextPageUseCase with query: $query, page: $page")
        return try {
            val books = loadNextPageUseCase(query, page)
            LoadResult.Page(
                data = books,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (books.isNotEmpty()) page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Book>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}