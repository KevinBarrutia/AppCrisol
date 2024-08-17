package pe.edu.crisol.libreria.viewModel

import androidx.lifecycle.ViewModel
import pe.edu.crisol.libreria.model.Book

class ShoppingCartViewModel : ViewModel() {

    private val cartBooks = mutableListOf<Book>()


    fun addToCart(book: Book) {
        cartBooks.add(book)
    }

    fun removeBookFromCart(position: Int) {
        if (position in cartBooks.indices) {
            cartBooks.removeAt(position)
        }
    }

    fun getCartBooks(): List<Book> {
        return cartBooks.toList()
    }

    fun getTotalProductCount(): Int {
        return cartBooks.size
    }

    fun getTotalPrice(): Double {
        var totalPrice = 0.0
        for (book in cartBooks) {
            val amount = book.saleInfo?.listPrice?.amount
            if (amount is Number) {
                totalPrice += amount.toDouble()
            } else {

            }
        }
        return totalPrice
    }
}