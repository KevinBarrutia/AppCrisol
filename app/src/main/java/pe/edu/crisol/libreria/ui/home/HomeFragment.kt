package pe.edu.crisol.libreria.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import pe.edu.crisol.libreria.databinding.FragmentHomeBinding
import pe.edu.crisol.libreria.domain.model.Section

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var sectionAdapter: SectionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val toolbar = binding.toolbar
        (this.activity as AppCompatActivity).setSupportActionBar(toolbar)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Inicializa el SectionAdapter con una lista vacía
        sectionAdapter = SectionAdapter(emptyList(), lifecycleScope) { book ->
            // Acción al hacer clic en un libro (navegación o detalle)
/*            val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(bookId = book.id)
            findNavController().navigate(action)
            Log.d("HomeFragment", "Clicked on book: ${book.title}")*/
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = sectionAdapter
        // Observa los flujos de datos para cada sección
        observeSections()
    }

    private fun observeSections() {
        val sections = mutableListOf<Section>()


        homeViewModel.bookSections.forEach { (sectionTitle, pagingDataFlow) ->
            lifecycleScope.launch {
                pagingDataFlow.collectLatest { pagingData ->
                    Log.d("HomeFragment", "Received new PagingData for section: $sectionTitle")
                    // Si ya existe la sección, actualiza los datos
                    val existingSection = sections.find { it.title == sectionTitle }
                    if (existingSection != null) {
                        val updatedSection = existingSection.copy(pagingData = pagingData)
                        sections[sections.indexOf(existingSection)] = updatedSection
                    } else {
                        // Si no existe, agrega la nueva sección
                        sections.add(Section(sectionTitle, pagingData))
                    }

                    // Actualiza el adaptador con las nuevas secciones
                    sectionAdapter = SectionAdapter(sections, lifecycleScope) { book ->
                        // Acción al hacer clic en un libro
                        Log.d("HomeFragment", "Clicked on book: ${book.title}")
                        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(bookId = book.id)
                        findNavController().navigate(action)

                    }

                    binding.recyclerView.adapter = sectionAdapter
                }

            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}