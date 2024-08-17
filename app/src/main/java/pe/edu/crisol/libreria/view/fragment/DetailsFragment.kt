package pe.edu.crisol.libreria.view.fragment

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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import pe.edu.crisol.libreria.R
import pe.edu.crisol.libreria.databinding.FragmentDetailsBinding
import pe.edu.crisol.libreria.model.ShoppingCart
import pe.edu.crisol.libreria.model.WishList
import pe.edu.crisol.libreria.viewModel.DetailsViewModel
import pe.edu.crisol.libreria.viewModel.DetailsViewModelFactory
import pe.edu.crisol.libreria.viewModel.ShoppingCartViewModel

class DetailsFragment : Fragment(), MenuProvider {

    private var _binding: FragmentDetailsBinding? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var uid: String
    private val binding get() = _binding!!

    private lateinit var detailsViewModel: DetailsViewModel

    private lateinit var shoppingCartViewModel: ShoppingCartViewModel

    private var bookId = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        val toolbar = binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        shoppingCartViewModel =
            ViewModelProvider(requireActivity()).get(ShoppingCartViewModel::class.java)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(toolbar, navController)

        bookId = DetailsFragmentArgs.fromBundle(requireArguments()).bookId


        Log.i("bookId", bookId)

        val viewModelFactory = DetailsViewModelFactory(bookId)
        detailsViewModel =
            ViewModelProvider(this, viewModelFactory).get(DetailsViewModel::class.java)

        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser!!.uid

        /*viewModel.searchBookById(bookId).observe(viewLifecycleOwner, Observer {detailsResponse  ->
            if (detailsResponse != null) {
                val book = detailsResponse.toBook()
                Glide.with(view.context)
                    .load(book.volumeInfo.imageLinks.thumbnail)
                    .into(binding.bookCoverDetails)
                binding.bookTitleDetails.text = book.volumeInfo.title
                binding.bookAuthorsDetails.text = book.volumeInfo.authors.joinToString()
                binding.bookPriceDetails.text = "${book.saleInfo?.listPrice?.currencyCode} ${book.saleInfo?.listPrice?.amount}"
                binding.titleDescription.text = "About this book"
                binding.contentDescription.text = Html.fromHtml(book.volumeInfo.description, Html.FROM_HTML_MODE_COMPACT)

                binding.addShoppingCart.setOnClickListener {
                    shoppingCartViewModel.addToCart(book)
                    Snackbar.make(view, "Book added to cart", Snackbar.LENGTH_SHORT).show()
                }
            }
        })*/

        detailsViewModel.loadBook()

        detailsViewModel.book.observe(viewLifecycleOwner, Observer { book ->
            book?.let {
                Glide.with(view.context)
                    .load(book.volumeInfo.imageLinks.thumbnail)
                    .into(binding.bookCoverDetails)
                binding.bookTitleDetails.text = book.volumeInfo.title
                binding.bookAuthorsDetails.text = book.volumeInfo.authors.joinToString()
                binding.bookPriceDetails.text =
                    "${book.saleInfo?.listPrice?.currencyCode} ${book.saleInfo?.listPrice?.amount}"
                binding.titleDescription.text = "About this book"
                binding.contentDescription.text =
                    Html.fromHtml(book.volumeInfo.description, Html.FROM_HTML_MODE_COMPACT)

                binding.addShoppingCart.setOnClickListener {
                    addToShoppingCart()
                    shoppingCartViewModel.addToCart(book)
                    Snackbar.make(view, "Book added to cart", Snackbar.LENGTH_SHORT).show()
                }
            }
        })

        return view
    }


    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_toolbar_details, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.add_to_wishlist -> addToWishList()
        }
        return false
    }

    private fun addToWishList() {
        database = FirebaseDatabase.getInstance().getReference("WishList")
        val wishList = WishList(uid, bookId)
        database.child(uid).child(bookId).setValue(wishList).addOnSuccessListener {
            Snackbar.make(binding.root, "In Wishlist", Snackbar.LENGTH_SHORT).show()
        }

    }

    private fun addToShoppingCart() {
        database = FirebaseDatabase.getInstance().getReference("ShoppingCart")
        val shoppingCart = ShoppingCart(uid, bookId)
        database.child(uid).child(bookId).setValue(shoppingCart).addOnSuccessListener {
            Snackbar.make(binding.root, "In Shopping Cart", Snackbar.LENGTH_SHORT).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}