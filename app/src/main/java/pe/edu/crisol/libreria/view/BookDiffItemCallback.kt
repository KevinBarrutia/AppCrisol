package pe.edu.crisol.libreria.view

import androidx.recyclerview.widget.DiffUtil
import pe.edu.crisol.libreria.model.Book

class BookDiffItemCallback : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean = (oldItem.id == newItem.id)

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean = (oldItem == newItem)
}