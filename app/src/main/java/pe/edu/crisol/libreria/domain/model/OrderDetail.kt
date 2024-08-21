package pe.edu.crisol.libreria.domain.model

data class OrderDetail(
    val id: String,
    val orderId: String,
    val bookId: String,
    val quantity: Int,
    val price: Double
)
