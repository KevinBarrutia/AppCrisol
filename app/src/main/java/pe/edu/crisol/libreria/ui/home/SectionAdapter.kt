package pe.edu.crisol.libreria.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.checkerframework.checker.units.qual.C
import pe.edu.crisol.libreria.databinding.ItemBookSectionBinding
import pe.edu.crisol.libreria.domain.model.Book
import pe.edu.crisol.libreria.domain.model.Section

class SectionAdapter(
    private val sections: List<Section>,
    private val lifecycleScope: CoroutineScope,
    private val onBookClick: (Book) -> Unit
) : RecyclerView.Adapter<SectionAdapter.SectionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val binding = ItemBookSectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        val section = sections[position]
        holder.bind(section)
    }

    override fun getItemCount(): Int = sections.size

    inner class SectionViewHolder(private val binding: ItemBookSectionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val adapter = BookAdapter(onBookClick)

        init {
            binding.sectionRecyclerView.adapter = adapter
            binding.sectionRecyclerView.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        }
        fun bind(section: Section) {
            binding.sectionTitle.text = section.title
            lifecycleScope.launch {
                adapter.submitData(section.pagingData)
            }
        }
    }
}

