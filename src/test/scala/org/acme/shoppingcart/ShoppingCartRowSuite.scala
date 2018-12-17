package org.acme.shoppingcart

import org.scalatest.FunSuite

class ShoppingCartRowSuite extends FunSuite {

  val item = Item(1L, "Dove Soap", 3999, 0)
  val taxedItem = Item(2L, "Dove Soap", 3999, 0.125)

  test("A row price with qty 1 should equal the item price") {
    val row = new ShoppingCartRow(item, 1)
    assert(row.netTotalPence == item.price)
  }

  test("A row price with qty 10 should equal 10 times the item price") {
    val row = new ShoppingCartRow(item, 10)
    assert(row.netTotalPence == item.price * 10)
  }

  test("A row with a tax free item should always return 0 tax total") {
    val row = new ShoppingCartRow(item, 10)
    assert(row.taxTotalPence == 0)
  }

  test("A row with a tax 12.5% should return 12.5% of the row netTotalPence") {
    val row = new ShoppingCartRow(taxedItem, 10)
    assert(row.taxTotalPence == (row.netTotalPence / 100d) * 12.5)
  }
}