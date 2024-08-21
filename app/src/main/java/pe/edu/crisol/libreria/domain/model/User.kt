package pe.edu.crisol.libreria.domain.model

import java.util.Date

data class User(
    val id: String,
    val givenName: String,
    val surname: String,
    val email: String,
    val password: String,
    val role: String = "user",
    val status: String = "active",
    val verified: Boolean = false,
    val verificationCode: String = "",
    val verificationExpiry: String = "",
    val createdAt: String = Date().toString(),
    val updatedAt: String
)
