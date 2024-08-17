package pe.edu.crisol.libreria.model

data class VolumeInfo (
    val title: String,
    val authors: List<String>,
    val publisher: String,
    val publishedDate: String,
    val description: String,
    val industryIdentifiers: List<IndustryIdentifiers>,
    val readingModes: ReadingModes,
    val pageCount: Int,
    val printedPageCount: Int,
    val dimensions: Dimensions,
    val printType: String,
    val categories: List<String>,
    val averageRating: Int,
    val ratingsCount: Int,
    val maturityRating: String,
    val allowAnonLogging: Boolean,
    val contentVersion: String,
    val panelizationSummary: PanelizationSummary,
    val language: String,
    val imageLinks: ImageLink,
    val previewLink: String,
    val infoLink: String,
    val canonicalVolumeLink: String
)
