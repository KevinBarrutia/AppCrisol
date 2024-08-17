package pe.edu.crisol.libreria.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import pe.edu.crisol.libreria.R
import pe.edu.crisol.libreria.databinding.FragmentShoppingCartBinding
import pe.edu.crisol.libreria.model.Book
import pe.edu.crisol.libreria.view.adapters.CartBookAdapter
import pe.edu.crisol.libreria.view.adapters.WishListAdapter
import pe.edu.crisol.libreria.viewModel.ShoppingCartViewModel

class ShoppingCartFragment : Fragment() {

    private lateinit var binding: FragmentShoppingCartBinding
    private lateinit var adapter: CartBookAdapter
    private lateinit var shoppingCartViewModel: ShoppingCartViewModel
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var uid: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        val view = binding.root

        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser!!.uid
        shoppingCartViewModel = ViewModelProvider(requireActivity()).get(ShoppingCartViewModel::class.java)

        adapter = CartBookAdapter(shoppingCartViewModel.getCartBooks())
        binding.cartRecyclerView.adapter = adapter
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        database = FirebaseDatabase.getInstance().getReference("ShoppingCart")


        binding.tvProductCount.text = getString(R.string.product_count, shoppingCartViewModel.getTotalProductCount())
        binding.tvTotalPrice.text = getString(R.string.tv_total_price, shoppingCartViewModel.getTotalPrice())

        return view
    }

    private fun updateProductCountAndTotalPrice() {

    }
}