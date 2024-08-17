package pe.edu.crisol.libreria.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import pe.edu.crisol.libreria.databinding.FragmentMoreBinding

class MoreFragment : Fragment() {
    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvListaDeseos.setOnClickListener{
            val action = MoreFragmentDirections.actionMoreFragmentToWishListFragment()
            view.findNavController().navigate(action)

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}