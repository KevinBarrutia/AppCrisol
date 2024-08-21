package pe.edu.crisol.libreria.data.source.googlebooks

data class BooksResponse (
    val items: List<BookResponse>?
)

data class BookResponse(
    val id: String,
    val volumeInfo: VolumeInfo,
    val saleInfo: SaleInfo,
)

data class VolumeInfo(
    val title: String,
    val subtitle: String,
    val authors: List<String>?,
    val industryIdentifiers: List<IndustryIdentifier>?,
    val publisher: String?,
    val publishedDate: String?,
    val imageLinks: ImageLinks?,
    val description: String?,
    val categories: List<String>?
)

data class ImageLinks (
    val thumbnail: String
)

data class IndustryIdentifier (
    val type: String,
    val identifier: String
)

data class SaleInfo(
    val listPrice: Price?
)

data class Price(
    val amount: Double?
)