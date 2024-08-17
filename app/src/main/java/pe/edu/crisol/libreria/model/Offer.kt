package pe.edu.crisol.libreria.model

data class Offer (
    var finskyOfferType: Int,
    var listPrice: ListPrice,
    var retailPrice: RetailPrice,
    var giftable: Boolean
)
