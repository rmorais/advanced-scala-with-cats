package chapter2

object SetMonoids {

  def setUnionMonoid[A]: Monoid[Set[A]] = new Monoid[Set[A]] {
    override def empty: Set[A] = Set.empty

    override def combine(x: Set[A], y: Set[A]): Set[A] = x.union(y)
  }

  //Intersection can't be a monoid as there is no identity element
  def setIntersectionSemigroup[A]: Semigroup[Set[A]] = new Semigroup[Set[A]] {
    override def combine(x: Set[A], y: Set[A]): Set[A] = x.intersect(y)
  }

}
