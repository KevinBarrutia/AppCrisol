package pe.edu.crisol.libreria.data.source.firebase

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import pe.edu.crisol.libreria.domain.model.User
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    private val usersCollection = firestore.collection("users")

    suspend fun addUser(user: User) {
        usersCollection.add(user).await()
    }

    suspend fun getUserById(id: String): User? {
        return usersCollection.document(id).get().await().toObject(User::class.java)
    }

    suspend fun updateUser(user: User) {
        usersCollection.document(user.id).set(user).await()
    }

    suspend fun deleteUser(id: String) {
        usersCollection.document(id).delete().await()
    }
}