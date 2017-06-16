package chapter2

trait SemiGroup[A] {
  def combine(x: A, y: A): A
}

trait Monoid[A] extends SemiGroup[A] {
  def empty: A
}

object Monoid {
  def apply[A](implicit monoid: Monoid[A]) = monoid
}

object BooleanMonoids {

  implicit val booleanOrMonoid: Monoid[Boolean] = new Monoid[Boolean] {

    override def empty: Boolean = false

    override def combine(x: Boolean, y: Boolean): Boolean = x || y
  }

  implicit val booleanAndMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def empty: Boolean = true

    override def combine(x: Boolean, y: Boolean): Boolean = x && y
  }

  implicit val booleanEitherMonoid: Monoid[Boolean] = new Monoid[Boolean] {

    override def empty: Boolean = false

    override def combine(x: Boolean, y: Boolean): Boolean =
      (x && !y) || (!x && y)
  }

  implicit val booleanXnorMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def empty: Boolean = true

    override def combine(x: Boolean, y: Boolean): Boolean =
      (x || !y) && (!x || y)
  }
}

object SetMonoids {
  implicit def setUnionMonoid[A]: Monoid[Set[A]] = new Monoid[Set[A]] {
    override def empty: Set[A] = Set.empty[A]

    override def combine(x: Set[A], y: Set[A]): Set[A] = x union y
  }
}

object SetSemiGroup {
  implicit def setIntersectionSemigroup[A]: SemiGroup[Set[A]] = new SemiGroup[Set[A]] {
    override def combine(x: Set[A], y: Set[A]): Set[A] = x intersect y
  }
}

object MonoidsRunner extends App {
  import BooleanMonoids._

  def identityLaw[A](x: A)(implicit m: Monoid[A]): Boolean = {
    (m.combine(x, m.empty) == x) &&
    (m.combine(m.empty, x) == x)
  }

  assert(identityLaw(false)(booleanOrMonoid),
         "Boolean Or Monoid empty is not neutral")
  assert(identityLaw(true)(booleanAndMonoid),
         "Boolean And Monoid empty is not neutral")
  assert(identityLaw(false)(booleanEitherMonoid),
    "Boolean Either Monoid empty is not neutral")
  assert(identityLaw(true)(booleanXnorMonoid),
    "Boolean Xnor Monoid empty is not neutral")

}
