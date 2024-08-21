package pe.edu.crisol.libreria.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import pe.edu.crisol.libreria.R
import pe.edu.crisol.libreria.databinding.ItemBookPrimaryBinding
import pe.edu.crisol.libreria.domain.model.Book
import pe.edu.crisol.libreria.util.IMAGE_URL
import pe.edu.crisol.libreria.util.SUFFIX_URL

class BookAdapter(private val onBookClick: (Book) -> Unit) : PagingDataAdapter<Book, BookAdapter.ViewHolder>(BookDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBookPrimaryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = getItem(position)
        if (book != null) {
            holder.bind(book)
        }
    }

    inner class ViewHolder(private val binding: ItemBookPrimaryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.apply {
                if (book.cover.isNotBlank()) {
                    Picasso.get()
                        .load(book.cover)
                        .placeholder(R.color.md_theme_dark_onBackground)
                        .into(bookCover)
                } else {
                    Picasso.get()
                        .load(IMAGE_URL + book.isbn + SUFFIX_URL)
                        .placeholder(R.color.md_theme_dark_onBackground)
                        .into(bookCover)
                }

                bookTitle.text = book.title
                bookSubtitle.text = book.subtitle
                bookRating.text = book.rating.toString()
                bookPrice.text = itemView.context.getString(R.string.tv_total_price, book.price)
                root.setOnClickListener{
                    onBookClick(book)
                }
            }
        }
    }

    class BookDiffCallback : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }
    }
}