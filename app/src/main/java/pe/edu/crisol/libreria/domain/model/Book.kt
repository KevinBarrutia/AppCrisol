package pe.edu.crisol.libreria.domain.model

data class Book (
    val id: String = "",
    val isbn: String = "",
    val title: String = "",
    val subtitle: String = "",
    val authors: List<String> = emptyList(),
    val publisher: String = "",
    val publishedDate: String = "",
    val description: String = "",
    val categories: List<String> = emptyList(),
    val cover: String = "",
    val price: Double = 0.0,
    val rating: Double = 0.0
)
