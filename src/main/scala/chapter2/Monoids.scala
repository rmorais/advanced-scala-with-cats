package chapter2

import chapter2.BooleanMonoidLaws.property
import org.scalacheck.Arbitrary


trait Semigroup[A] {
  def combine(x: A, y: A): A
}

trait Monoid[A] extends Semigroup[A] {
  def empty: A
}

object Monoid {
  def apply[A](implicit monoid: Monoid[A]) =
    monoid
}

trait MonoidLaws {

  private def identityLaw[A](x: A)(implicit monoid: Monoid[A]): Boolean =
    (monoid.combine(x, monoid.empty) == x) &&
      (monoid.combine(monoid.empty, x) == x)

  private def associativeLaw[A](x: A, y: A, z: A)(implicit m: Monoid[A]): Boolean = {
    m.combine(x, m.combine(y, z)) ==
      m.combine(m.combine(x, y), z)
  }

  import org.scalacheck.Prop.forAll

  def identity[A: Arbitrary](monoid: Monoid[A]) = {
    property(monoid.getClass.getName) = forAll { a: A =>
      identityLaw(a)(monoid)
    }
  }

  def associativity[A: Arbitrary](monoid: Monoid[A]) = {
    property(monoid.getClass.getName) = forAll { (a: A, b: A, c: A) =>
      associativeLaw(a, b, c)(monoid)
    }
  }
}
