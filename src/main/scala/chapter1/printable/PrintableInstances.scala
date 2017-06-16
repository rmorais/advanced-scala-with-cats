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
    override def format(cat: Cat): String = {
      val name = Printable.format(cat.name)
      val age = Printable.format(cat.age)
      val color = Printable.format(cat.color)
      s"$name is a $age year-old $color cat."
    }
  }
}
