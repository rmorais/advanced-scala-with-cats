package chapter3

import cats.Functor
import cats.syntax.functor._

trait Tree[+A]

final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

final case class Leaf[A](value: A) extends Tree[A]

object Tree {

  implicit val treeFunctor: Functor[Tree] = new Functor[Tree] {
    override def map[A, B](fa: Tree[A])(f: (A) => B): Tree[B] = fa match {
      case Branch(l, r) => Branch(map(l)(f), map(r)(f))
      case Leaf(v) => Leaf(f(v))
    }
  }

  def branch[A](left: Tree[A], right: Tree[A]): Tree[A] = {
    Branch(left, right)
  }

  def leaf[A](value: A): Tree[A] = Leaf(value)
}

object TreeRunner extends App {

  import Tree._
  val leafInstance = leaf(1)

  val branchInstance = branch(Leaf(2), Leaf(3))

  val f = (x: Int) => x + 1

  assert(leafInstance.map(_ * 4 ) == leaf(4))

  assert(branchInstance.map(_ * 4) == branch(leaf(8), leaf(12)))
}