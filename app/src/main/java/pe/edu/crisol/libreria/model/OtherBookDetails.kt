package pe.edu.crisol.libreria.model

import com.google.gson.annotations.SerializedName

data class OtherBookDetails (
    val title: String,
    val description: String,
    val contributor: String,
    val author: String,
    @SerializedName("contributor_note")
    val contributorNote: String,
    val price: String,
    @SerializedName("age_group")
    val ageGroup: String,
    val publisher: String,
    @SerializedName("primary_isbn_13")
    val primaryIsbn13: String,
    @SerializedName("primary_isbn_10")
    val primaryIsbn10: String
)