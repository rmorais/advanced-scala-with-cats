package chapter1.printable

import chapter1.Cat

object PrintableInstances {

  implicit val PrintableInt = new Printable[Int] {
    override def format(value: Int): String = value.toString
  }

  implicit val PrintableString = new Printable[String] {
    override def format(value: String): String = value.toString
  }

  implicit val PrintableCat = new Printable[Cat] {
    override def format(value: Cat): String = s"${value.name} is a ${value.age} year-old ${value.color} cat."
  }
}
