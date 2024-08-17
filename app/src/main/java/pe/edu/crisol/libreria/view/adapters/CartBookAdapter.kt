package pe.edu.crisol.libreria.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.edu.crisol.libreria.databinding.ItemBookCartBinding
import pe.edu.crisol.libreria.model.Book

class CartBookAdapter(private val books: List<Book>) : RecyclerView.Adapter<CartBookAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemBookCartBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBookCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = books.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]
        with(holder.binding) {
            book.volumeInfo.title?.let { bookTitle.text = it }
            book.volumeInfo.authors?.let { bookAuthor.text = it.joinToString() }
            book.saleInfo?.listPrice?.let {
                bookPrice.text = "${it.currencyCode} ${it.amount}"
            }
            book.volumeInfo.imageLinks?.let {
                Glide.with(holder.itemView.context)
                    .load(it.smallThumbnail)
                    .fitCenter()
                    .into(bookCover)
            }
        }
    }
}