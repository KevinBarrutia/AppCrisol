package pe.edu.crisol.libreria.view.fragment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import pe.edu.crisol.libreria.R
import pe.edu.crisol.libreria.databinding.FragmentProfileBinding
import pe.edu.crisol.libreria.model.User
import java.io.File

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth : FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var uid: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser!!.uid

        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        if ( uid != null) {
            getUsersData()
        }
        return view
    }
    private fun getUsersData() {
        databaseReference.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                if (user != null) {
                    binding.tvNombrePerfil.setText(user.nombre)
                    binding.tvNombre.setText(user.nombre)
                    binding.tvEmail.setText(user.correo)
                    binding.tvEmailPerfil.setText(user.correo)
                    binding.tvContrasena.setText(user.contrasena)
                } else {
                    Toast.makeText(context, "No se encontraron datos", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Error al obtener datos", Toast.LENGTH_SHORT).show()
            }
        })
    }


}