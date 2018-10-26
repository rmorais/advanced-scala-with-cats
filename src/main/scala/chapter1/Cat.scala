package chapter1

import PrintableInstances._

final case class Cat(name: String, age: Int, color: String)

object Cat {
  implicit val printableCat: Printable[Cat] = new Printable[Cat] {
    override def format(cat: Cat): String = {
      val name = Printable.format(cat.name)
      val age = Printable.format(cat.age)
      val color = Printable.format(cat.color)
      s" $name is a $age year-old $color cat."
    }
  }
}

object CatRunner extends App {
  val cat = Cat(name = "Teco", age = 4, color = "White")

  Printable.print(cat)

  //using syntax
  import PrintableSyntax._
  cat.print
}