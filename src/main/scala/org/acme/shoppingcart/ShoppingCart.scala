package org.acme.shoppingcart

class ShoppingCart(val contents: Map[Long, ShoppingCartRow]) {

  lazy val netTotalPence: Int = contents.foldLeft(0)((a, b) => a + b._2.netTotalPence)
  lazy val netTotal: Double = netTotalPence.toDouble / 100

  lazy val taxTotalPence: Int = Math.round(contents.foldLeft(0d)((a, b) => a + b._2.taxTotalPence)).toInt
  lazy val taxTotal: Double = taxTotalPence / 100

  lazy val grossTotal: Double = netTotal + taxTotal

  def addItem(item:Item, qty:Int = 1): ShoppingCart = contents.get(item.sku) match {
    case Some(row) => new ShoppingCart(contents - item.sku + (item.sku -> ShoppingCartRow(item, qty + row.qty)))
    case _ =>  new ShoppingCart(contents + (item.sku -> ShoppingCartRow(item, qty)))
  }

}
