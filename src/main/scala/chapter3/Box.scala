package chapter3

import chapter1.Printable

final case class Box[A](value: A)

object Box {

  def traditionalPrintableBox[A](implicit printable: Printable[A]): Printable[Box[A]] = new Printable[Box[A]] {
    override def format(box: Box[A]): String = printable.format(box.value)
  }

  implicit def printableBox[A](implicit printable: Printable[A]): Printable[Box[A]] = printable.contramap(_.value)
}
