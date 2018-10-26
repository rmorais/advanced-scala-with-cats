package chapter2

import cats.syntax.semigroup._


object SuperAdder {

  def add(items: List[Int]): Int = items.foldLeft(0)(_ + _)

  def add2[A](items: List[A])(implicit m: cats.Monoid[A]): A = items.foldLeft(m.empty)(_ |+| _)

}
