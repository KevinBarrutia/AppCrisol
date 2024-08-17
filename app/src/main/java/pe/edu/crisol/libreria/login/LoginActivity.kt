package pe.edu.crisol.libreria.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import pe.edu.crisol.libreria.R
import pe.edu.crisol.libreria.databinding.ActivityLoginBinding
import pe.edu.crisol.libreria.view.MainActivity


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding
    // Firebase api service
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.btRegister.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        binding.btLogin.setOnClickListener(this)

    }
    override fun onClick(v: View?) {
        // Lógica para manejar el clic en algún elemento de la interfaz de usuario
        when (v?.id) {
            R.id.btLogin -> loginUser()
        }
    }
    // Funcion para iniciar sesion con Firebase
    private fun loginUser() {
        val email = binding.htEmail.text.toString()
        val password = binding.htPassword.text.toString()
        if (validarCampos()) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Inicio de sesión exitoso
                        Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                        // Iniciar MenuActivity
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                        finish()
                    } else {
                        // Inicio de sesión fallido
                        Toast.makeText(this, "Error, credenciales incorrectas", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    // Validación de campos
    private fun validarCampos(): Boolean {
        val email = binding.htEmail.text.toString()
        val password = binding.htPassword.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
    
}