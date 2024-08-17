package pe.edu.crisol.libreria.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import pe.edu.crisol.libreria.databinding.ActivitySignUpBinding
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import pe.edu.crisol.libreria.R
import pe.edu.crisol.libreria.model.User


class SignUpActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var binding: ActivitySignUpBinding
    // Firebase api service
    private lateinit var auth:FirebaseAuth
    private lateinit var database:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        binding.btnRegistrar.setOnClickListener {
            registerAndSaveUser()
        }

    }
    override fun onClick(v: View?) {
        // Lógica para manejar el clic en algún elemento de la interfaz de usuario
        when (v?.id) {
            R.id.btnRegistrar -> registerAndSaveUser()
        }
    }

    // Método para guardar datos de usuario en Firebase
    private fun saveUserData(nombre: String, apellido: String, correo: String, contrasena: String) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        val userid = auth.currentUser?.uid

        val user = User(nombre, apellido, correo, contrasena)
        if (userid != null) {
            database.child(userid).setValue(user).addOnSuccessListener {
                clearFields()
                Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show()
        }
    }
    // Método para registrar usuarios con Firebase
    private fun registerAndSaveUser() {
        val nombre = binding.tilNombre.editText?.text.toString()
        val apellido = binding.tilApellido.editText?.text.toString()
        val correo = binding.tilCorreo.editText?.text.toString()
        val contrasena = binding.tilContrasena.editText?.text.toString()

        if (validarCampos()) {
            auth.createUserWithEmailAndPassword(correo, contrasena)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Registro exitoso
                        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                        // Guardar datos de usuario en Firebase
                        saveUserData(nombre, apellido, correo, contrasena)
                        // Iniciar MenuActivity
                        startActivity(Intent(applicationContext, LoginActivity::class.java))
                    } else {
                        // Registro fallido
                        Toast.makeText(this, "Error en el registro", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener(this) { e ->
                    // Manejar de error especifico
                    Toast.makeText(this, "Error en el registro: ${e.message}", Toast.LENGTH_SHORT).show()
                }

        }
    }



    // Validación de correo
    private fun validarCorreo(correo: String): Boolean {
        val patron = "^[a-zA-Z0-9_]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$".toRegex()
        return patron.matches(correo)
    }
    // Validación de campos
    private fun validarCampos(): Boolean {
        val nombre = binding.tilNombre.editText?.text.toString()
        val apellido = binding.tilApellido.editText?.text.toString()
        val email = binding.tilCorreo.editText?.text.toString()
        val password = binding.tilContrasena.editText?.text.toString()

        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
            return false
        }
        // Validar formato de correo electronico
        if (!validarCorreo(email)) {
            Toast.makeText(this, "Por favor ingrese un correo válido", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.length < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
    // Método para limpiar campos
    private fun clearFields() {
        binding.tilNombre.editText?.setText("")
        binding.tilApellido.editText?.setText("")
        binding.tilCorreo.editText?.setText("")
        binding.tilContrasena.editText?.setText("")
    }
}