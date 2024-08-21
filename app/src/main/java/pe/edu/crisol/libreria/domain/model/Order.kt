package pe.edu.crisol.libreria.domain.model

import java.util.Date

data class Order (
    val id: String,
    val userId: String,
    val orderDate: String = Date().toString(),
    val status: String,
    val total: Double
)