package pe.edu.crisol.libreria.data.source.firebase

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import pe.edu.crisol.libreria.domain.model.OrderDetail
import javax.inject.Inject

class OrderDetailDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    private val orderDetailsCollection = firestore.collection("orderDetails")

    suspend fun addOrderDetail(orderDetail: OrderDetail) {
        orderDetailsCollection.add(orderDetail).await()
    }

    suspend fun getOrderDetailsByOrderId(orderId: String): List<OrderDetail> {
        return orderDetailsCollection.whereEqualTo("orderId", orderId).get().await().toObjects(OrderDetail::class.java)
    }

    suspend fun updateOrderDetail(orderDetail: OrderDetail) {
        orderDetailsCollection.document(orderDetail.id).set(orderDetail).await()
    }

    suspend fun deleteOrderDetail(id: String) {
        orderDetailsCollection.document(id).delete().await()
    }
}