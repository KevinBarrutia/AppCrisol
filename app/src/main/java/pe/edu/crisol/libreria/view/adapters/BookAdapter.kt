package pe.edu.crisol.libreria.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.edu.crisol.libreria.databinding.ItemBookBinding
import pe.edu.crisol.libreria.model.Book
import pe.edu.crisol.libreria.view.BookDiffItemCallback

class BookAdapter(val clickListener: (bookId: String) -> Unit/*private val books: List<Book>*//*, private val searchViewModel: SearchViewModel*/) :
    ListAdapter<Book, BookAdapter.ViewHolder>(BookDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class ViewHolder(val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Book, clickListener: (bookId: String) -> Unit) {
            item.volumeInfo.title?.let { binding.bookTitle.text = it }
            item.volumeInfo.authors?.let { binding.bookAuthor.text = it.joinToString() }
            item.saleInfo?.let {saleInfo ->
                saleInfo.listPrice?.let { listPrice ->
                    binding.bookPrice.text = listPrice.currencyCode + " " + listPrice.amount }
                }

            Glide.with(itemView.context)
                .load(item!!.volumeInfo.imageLinks.smallThumbnail)
                .fitCenter()
                .into(binding.bookCover)
            binding.root.setOnClickListener {
                clickListener(item.id)
            }
        }

        companion object {
            fun inflateFrom(parent: ViewGroup): BookAdapter.ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemBookBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

}