package org.acme.shoppingcart

case class ShoppingCartRow(item: Item, qty: Int) {

  lazy val netTotalPence: Int = item.price * qty
}
