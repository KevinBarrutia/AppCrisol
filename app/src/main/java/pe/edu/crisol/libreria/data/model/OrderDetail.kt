package pe.edu.crisol.libreria.data.model

data class OrderDetail(
    val id: String,
    val orderId: String,
    val bookId: String,
    val quantity: Int,
    val price: Double
)
