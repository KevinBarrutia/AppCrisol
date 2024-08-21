package pe.edu.crisol.libreria.ui.detail

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import pe.edu.crisol.libreria.R
import pe.edu.crisol.libreria.databinding.FragmentBookDetailBinding
import pe.edu.crisol.libreria.domain.model.Book
import pe.edu.crisol.libreria.util.IMAGE_URL
import pe.edu.crisol.libreria.util.SUFFIX_URL

@AndroidEntryPoint
class BookDetailFragment : Fragment(), MenuProvider {

    private var _binding: FragmentBookDetailBinding? = null
    private val binding get() = _binding!!

    private val args: BookDetailFragmentArgs by navArgs()
    private val viewModel: BookDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        val toolbar = binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(toolbar, navController)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadBookDetails(args.bookId)

        viewModel.book.observe(viewLifecycleOwner) { book ->
            bindBookDetails(book)
        }
    }

    private fun bindBookDetails(book: Book) {
        binding.apply {
            bookTitle.text = book.title
            bookAuthor.text = book.authors.joinToString()
            bookSubtitle.text = book.subtitle
            bookRating.text = book.rating.toString()
            buyButton.text = getString(R.string.tv_total_price, book.price)
            bookDescription.text = Html.fromHtml(book.description, Html.FROM_HTML_MODE_COMPACT)
            Picasso.get()
                .load(book.cover.ifEmpty { IMAGE_URL + book.isbn + SUFFIX_URL })
                .into(bookCover)
        }
    }


    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_book_detail, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.add_to_wishlist -> Log.d("DetailsFragment", "Add to wishlist")
        }
        return false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}