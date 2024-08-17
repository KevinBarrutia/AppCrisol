package pe.edu.crisol.libreria.retrofit.request

data class SearchRequest (
    val q: String,
    val filter: String = "",
    val langRestrict: String = "",
    val maxResults: Int = 0,
    val orderBy: String = "",
    val printType: String = "",
    val projection: String = ""
)