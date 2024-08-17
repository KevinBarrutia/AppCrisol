package pe.edu.crisol.libreria.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import pe.edu.crisol.libreria.view.adapters.BookAdapter
import pe.edu.crisol.libreria.databinding.FragmentSearchBinding

import pe.edu.crisol.libreria.viewModel.SearchViewModel

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        val searchBar = binding.searchBar
        val searchView = binding.searchView

        searchView.setupWithSearchBar(searchBar)

        val adapter = BookAdapter { bookId ->
            viewModel.onBookClicked(bookId)
        }

        binding.booksRecyclerView.adapter = adapter

        viewModel.navigateToDetails.observe(viewLifecycleOwner, Observer { bookId ->
            bookId?.let {
                val action = SearchFragmentDirections.actionSearchFragmentToDetailsFragment(bookId)
                view.findNavController().navigate(action)
                viewModel.onDetailsNavigated()
            }
        })

        viewModel.bookList.observe(viewLifecycleOwner, Observer { bookList ->
            bookList?.let {
                adapter.submitList(bookList)
            }
        })

        searchView.editText.setOnEditorActionListener { v, actionId, event ->
            viewModel.searchBooks(searchView.text.toString())
            searchBar.setText(searchView.text)
            searchView.hide()
            return@setOnEditorActionListener false
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}