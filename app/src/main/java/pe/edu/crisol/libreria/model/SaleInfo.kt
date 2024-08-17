package pe.edu.crisol.libreria.model

data class SaleInfo (
    var country: String,
    var listPrice: ListPriceSale,
    var retailPrice: RetailPriceSale,
    var buyLink: String,
    var offers: List<Offer>,
)
