package org.acme.shoppingcart

import org.scalatest.FunSuite

class ShoppingCartSuite extends FunSuite {

  val doveSoap = Item(1L, "Dove Soap", 3999, 0)

  test("An empty shopping cart should have total of 0") {
    val cart = new ShoppingCart(Map.empty[Long, ShoppingCartRow])
    assert(cart.netTotal == 0)
  }

  test("A cart with 5 Dove Soap with a unit price of 39.99 should total 199.95") {
    val emptyCart = new ShoppingCart(Map.empty[Long, ShoppingCartRow])
    val populatedCart = emptyCart.addItem(doveSoap, 5)
    assert(populatedCart.netTotal == 199.95)
  }

  test("A cart with 2 then 3 Dove Soap with a unit price of 39.99 should total 199.95") {
    val emptyCart = new ShoppingCart(Map.empty[Long, ShoppingCartRow])
    val populatedCart = emptyCart.addItem(doveSoap, 3)
    assert(populatedCart.netTotal == 119.97)
    val populatedCart2 = populatedCart.addItem(doveSoap, 2)
    assert(populatedCart2.netTotal == 199.95)
  }

  test("A cart with 5 then 3 Dove Soap with a unit price of 39.99 should total 319.92") {
    val emptyCart = new ShoppingCart(Map.empty[Long, ShoppingCartRow])
    val populatedCart = emptyCart.addItem(doveSoap, 5)
    assert(populatedCart.netTotal == 199.95)
    val populatedCart2 = populatedCart.addItem(doveSoap, 3)
    assert(populatedCart2.netTotal == 319.92)
  }

  val taxedDoveSoap = Item(1L, "Dove Soap", 3999, 0.125)
  val taxedAxeDeo = Item(2L, "Axe Deo", 9999, 0.125)

  test("A cart with 2 Dove Soaps and 2 Axe Deo should total 35.00 tax and 314.96 gross") {
    val emptyCart = new ShoppingCart(Map.empty[Long, ShoppingCartRow])
    val populatedCart = emptyCart
      .addItem(taxedDoveSoap, 2)
      .addItem(taxedAxeDeo, 2)
    assert(populatedCart.taxTotal == 35.00)
    assert(populatedCart.grossTotal == 314.96)
  }

  test("A cart with 2 tax free Dove Soaps and 2 Axe Deo should total 25.00 tax and 304.96 gross") {
    val emptyCart = new ShoppingCart(Map.empty[Long, ShoppingCartRow])
    val populatedCart = emptyCart
      .addItem(doveSoap, 2)
      .addItem(taxedAxeDeo, 2)
    assert(populatedCart.taxTotal == 25.00)
    assert(populatedCart.grossTotal == 304.96)
  }
}
