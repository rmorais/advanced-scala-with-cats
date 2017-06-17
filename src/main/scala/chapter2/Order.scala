package chapter2

import cats.syntax.semigroup._
import cats.instances.double._

case class Order(totalCost: Double, quantity: Double)

object Order {
  implicit val orderMonoid: cats.Monoid[Order] = new cats.Monoid[Order] {
    override def empty: Order = Order(totalCost = 0,quantity = 0)

    override def combine(x: Order, y: Order): Order = {
      Order(
        totalCost = x.totalCost |+| y.totalCost,
        quantity = x.quantity |+| y.quantity
      )
    }
  }
}
