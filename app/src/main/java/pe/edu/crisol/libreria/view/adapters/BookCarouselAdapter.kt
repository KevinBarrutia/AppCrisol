package pe.edu.crisol.libreria.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.edu.crisol.libreria.databinding.ItemBookCarouselBinding
import pe.edu.crisol.libreria.model.OtherBook
import pe.edu.crisol.libreria.model.OtherBookDetails
import pe.edu.crisol.libreria.view.OtherBookDiffItemCallback

class BookCarouselAdapter(private val clickListener: (bookId: String) -> Unit) :
    ListAdapter<OtherBook, BookCarouselAdapter.ViewHolder>(OtherBookDiffItemCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder = ViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)

    }

    class ViewHolder(val binding: ItemBookCarouselBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: OtherBook, clickListener: (bookId: String) -> Unit) {
            item.title?.let {
                binding.bookTitle.text = it
            }
            item.primaryIsbn10?.let {
                Glide.with(itemView.context)
                    .load(item.bookImage)
                    .into(binding.carouselImageView)
            }

            binding.root.setOnClickListener {
                Log.i("isbn", item.primaryIsbn10)
                clickListener(item.primaryIsbn10)
            }
        }

        companion object {
            fun inflateFrom(parent: ViewGroup): BookCarouselAdapter.ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemBookCarouselBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}