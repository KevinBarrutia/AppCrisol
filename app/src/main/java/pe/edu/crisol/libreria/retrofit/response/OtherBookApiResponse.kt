package pe.edu.crisol.libreria.retrofit.response

import com.google.gson.annotations.SerializedName
import pe.edu.crisol.libreria.model.OtherBook
import pe.edu.crisol.libreria.model.Results

data class OtherBookApiResponse(
    val status: String,
    val copyright: String,
    @SerializedName("num_results") val numResults: Int,
    @SerializedName("last_modified") val lastModified: String,
    val results: Results,
    val corrections: List<Any>
)

