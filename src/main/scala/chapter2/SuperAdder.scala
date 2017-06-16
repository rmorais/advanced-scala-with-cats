package chapter2

import cats.syntax.semigroup._

object SuperAdder {

  def add[A](items: List[A])(implicit ev: cats.Monoid[A]): A = items.foldLeft(cats.Monoid[A].empty)( _ |+| _)
}
