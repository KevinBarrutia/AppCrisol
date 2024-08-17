package pe.edu.crisol.libreria.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailsViewModelFactory(private val newBookId: String): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java))
            return DetailsViewModel(newBookId) as T
        throw IllegalArgumentException("Unknown ViewModel")
    }
}