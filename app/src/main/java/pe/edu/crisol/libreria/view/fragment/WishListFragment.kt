package pe.edu.crisol.libreria.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import pe.edu.crisol.libreria.databinding.FragmentWishListBinding
import pe.edu.crisol.libreria.view.adapters.WishListAdapter
import pe.edu.crisol.libreria.viewModel.WishListViewModel


class WishListFragment : Fragment() {
    private var _binding: FragmentWishListBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var uid: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWishListBinding.inflate(inflater, container, false)

        val wishListViewModel = ViewModelProvider(this).get(WishListViewModel::class.java)

        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser!!.uid

        database = FirebaseDatabase.getInstance().getReference("WishList")

        database.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.getValue(Any::class.java).toString()
                val regex = Regex("""(?<=bookId=)[^,}]+""")
                val matches = regex.findAll(data)
                val bookIds = matches.map { it.value }.toList()
                wishListViewModel.fetchBooksByIds(bookIds)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        wishListViewModel.books.observe(viewLifecycleOwner, Observer {
            val adapter = WishListAdapter(it)
            binding.rvWishList.adapter = adapter
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}