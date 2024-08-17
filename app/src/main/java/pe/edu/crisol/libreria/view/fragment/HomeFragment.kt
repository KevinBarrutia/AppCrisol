package pe.edu.crisol.libreria.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.HeroCarouselStrategy
import pe.edu.crisol.libreria.databinding.FragmentHomeBinding
import pe.edu.crisol.libreria.model.Book
import pe.edu.crisol.libreria.model.OtherBook
import pe.edu.crisol.libreria.model.OtherBookDetails
import pe.edu.crisol.libreria.view.adapters.BookCarouselAdapter
import pe.edu.crisol.libreria.viewModel.HomeViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        val toolbar = binding.toolbar
        (this.activity as AppCompatActivity).setSupportActionBar(toolbar)

        homeViewModel.navigateToDetails.observe(viewLifecycleOwner, Observer { bookId ->
            bookId?.let {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(bookId)
                view.findNavController().navigate(action)
                homeViewModel.onDetailsNavigated()
            }
        })

       setupObserver(homeViewModel.category1, binding.carousel1)
       setupObserver(homeViewModel.category2, binding.carousel2)
       setupObserver(homeViewModel.category3, binding.carousel3)
       setupObserver(homeViewModel.category4, binding.carousel4)

        return view
    }
    private fun setupObserver(categoryLiveData: LiveData<List<OtherBook>>, carousel: RecyclerView) {
        categoryLiveData.observe(viewLifecycleOwner, Observer { books ->
            carousel.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


            val adapter = BookCarouselAdapter { bookId ->
                bookId?.let {
                    homeViewModel.onBookClicked(bookId)
                }
            }

            carousel.adapter = adapter

            adapter.submitList(books)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}