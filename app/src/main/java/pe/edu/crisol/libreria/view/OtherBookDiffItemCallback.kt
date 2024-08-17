package pe.edu.crisol.libreria.view

import androidx.recyclerview.widget.DiffUtil
import pe.edu.crisol.libreria.model.OtherBook

class OtherBookDiffItemCallback: DiffUtil.ItemCallback<OtherBook>()  {
    override fun areItemsTheSame(oldItem: OtherBook, newItem: OtherBook): Boolean = (oldItem.primaryIsbn10 == newItem.primaryIsbn10)

    override fun areContentsTheSame(oldItem: OtherBook, newItem: OtherBook): Boolean = (oldItem == newItem)
}