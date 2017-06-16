package chapter2

import cats.instances.int._
import cats.syntax.semigroup._

object SuperAdder {

  def add(items: List[Int]): Int = items.sum
  def addUsingMonoids(items: List[Int]): Int = items.foldLeft(cats.Monoid[Int].empty)(_ |+| _)
}
