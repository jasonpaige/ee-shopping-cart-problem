package org.acme.shoppingcart

import org.scalatest.FunSuite

class ShoppingCartRowSuite extends FunSuite {

  val item = Item(1L, "Dove Soap", 3999)

  test("A row price with qty 1 should equal the item price") {
    val row = new ShoppingCartRow(item, 1)
    assert(row.netTotalPence == item.price)
  }

  test("A row price with qty 10 should equal 10 times the item price") {
    val row = new ShoppingCartRow(item, 10)
    assert(row.netTotalPence == item.price * 10)
  }
}
