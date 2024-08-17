package pe.edu.crisol.libreria.model

data class AccessInfo (
    var country: String,
    var epub: Epub,
    var pdf: Pdf,
    var accessViewStatus: String
)
