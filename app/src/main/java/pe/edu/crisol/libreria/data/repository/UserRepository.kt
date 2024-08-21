package pe.edu.crisol.libreria.data.repository

import pe.edu.crisol.libreria.data.source.firebase.OrderDataSource
import pe.edu.crisol.libreria.data.source.firebase.OrderDetailDataSource
import pe.edu.crisol.libreria.data.source.firebase.UserDataSource
import pe.edu.crisol.libreria.domain.model.Order
import pe.edu.crisol.libreria.domain.model.OrderDetail
import pe.edu.crisol.libreria.domain.model.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDataSource: UserDataSource,
    private val orderDetailDataSource: OrderDetailDataSource,
    private val orderDataSource: OrderDataSource
) {
    suspend fun addUser(user: User) = userDataSource.addUser(user)
    suspend fun getUserById(id: String) = userDataSource.getUserById(id)
    suspend fun updateUser(user: User) = userDataSource.updateUser(user)
    suspend fun deleteUser(id: String) = userDataSource.deleteUser(id)

    suspend fun addOrderDetail(orderDetail: OrderDetail) = orderDetailDataSource.addOrderDetail(orderDetail)
    suspend fun getOrderDetailsByOrderId(orderId: String) = orderDetailDataSource.getOrderDetailsByOrderId(orderId)
    suspend fun updateOrderDetail(orderDetail: OrderDetail) = orderDetailDataSource.updateOrderDetail(orderDetail)
    suspend fun deleteOrderDetail(id: String) = orderDetailDataSource.deleteOrderDetail(id)

    suspend fun addOrder(order: Order) = orderDataSource.addOrder(order)
    suspend fun getOrdersByUserId(userId: String) = orderDataSource.getOrdersByUserId(userId)
    suspend fun updateOrder(id: String, status: String) = orderDataSource.updateOrderStatus(id, status)
    suspend fun deleteOrder(id: String) = orderDataSource.deleteOrder(id)

}