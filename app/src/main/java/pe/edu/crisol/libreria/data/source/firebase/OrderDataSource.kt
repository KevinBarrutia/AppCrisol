package pe.edu.crisol.libreria.data.source.firebase

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import pe.edu.crisol.libreria.domain.model.Order
import javax.inject.Inject

class OrderDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    private val ordersCollection = firestore.collection("orders")

    suspend fun addOrder(order: Order) {
        ordersCollection.add(order).await()
    }

    suspend fun getOrdersByUserId(userId: String): List<Order> {
        return ordersCollection.whereEqualTo("userId", userId).get().await().toObjects(Order::class.java)
    }

    suspend fun updateOrderStatus(id: String, status: String) {
        ordersCollection.document(id).update("status", status).await()
    }

    suspend fun deleteOrder(id: String) {
        ordersCollection.document(id).delete().await()
    }
}