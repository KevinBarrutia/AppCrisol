package pe.edu.crisol.libreria.retrofit.response

import pe.edu.crisol.libreria.model.Book

data class SearchResponse (
    var kind: String,
    var totalItems: Int,
    var items: List<Book>
)