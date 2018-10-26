package chapter2

import org.scalacheck.Properties

object booleanMonoidInstances {
  val andMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def empty: Boolean = true

    override def combine(x: Boolean, y: Boolean): Boolean = x && y
  }

  val orMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def empty: Boolean = false

    override def combine(x: Boolean, y: Boolean): Boolean = x || y
  }

  val eitherMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def empty: Boolean = false

    override def combine(x: Boolean, y: Boolean): Boolean =
      (x && !y) || (!x && y)
  }

  val xnorMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def empty: Boolean = true

    override def combine(x: Boolean, y: Boolean): Boolean =
      (!x || y) && (x || !y)
  }
}

object BooleanMonoidLaws extends Properties("BooleanMonoids") with MonoidLaws {

  identity(booleanMonoidInstances.andMonoid)
  associativity(booleanMonoidInstances.andMonoid)

  identity(booleanMonoidInstances.orMonoid)
  associativity(booleanMonoidInstances.orMonoid)

  identity(booleanMonoidInstances.eitherMonoid)
  associativity(booleanMonoidInstances.eitherMonoid)

  identity(booleanMonoidInstances.xnorMonoid)
  associativity(booleanMonoidInstances.xnorMonoid)
}

