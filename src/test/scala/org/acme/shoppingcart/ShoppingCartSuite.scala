package org.acme.shoppingcart

import org.scalatest.FunSuite

class ShoppingCartSuite extends FunSuite {

  test("An empty shopping cart should have total of 0") {
    val cart = new ShoppingCart(Map.empty[Long, ShoppingCartRow])
    assert(cart.netTotal == 0)
  }

  test("A cart with 5 Dove Soap with a unit price of 39.99 should total 199.95") {
    val emptyCart = new ShoppingCart(Map.empty[Long, ShoppingCartRow])
    val populatedCart = emptyCart.addItem(Item(1L, "Dove Soap", 3999), 5)
    assert(populatedCart.netTotal == 199.95)
  }

  test("A cart with 2 then 3 Dove Soap with a unit price of 39.99 should total 199.95") {
    val emptyCart = new ShoppingCart(Map.empty[Long, ShoppingCartRow])
    val populatedCart = emptyCart.addItem(Item(1L, "Dove Soap", 3999), 3)
    assert(populatedCart.netTotal == 119.97)
    val populatedCart2 = populatedCart.addItem(Item(1L, "Dove Soap", 3999), 2)
    assert(populatedCart2.netTotal == 199.95)
  }
}
