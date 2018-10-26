package chapter1

import PrintableInstances._
import cats.Eq
import cats.syntax.eq._
import cats.instances.string._
import cats.instances.int._

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

  implicit val eqCat: Eq[Cat] = Eq.instance[Cat] { (x, y) =>
    (x.name === y.name) &&
    (x.age === y.age) &&
    (x.color === y.color)
  }
}

object CatRunner extends App {
  val cat = Cat(name = "Teco", age = 4, color = "White")

  Printable.print(cat)

  //using syntax
  import PrintableSyntax._
  cat.print

  val cat1 = Cat("Garfield", 38, "orange and black")
  val cat2 = Cat("Heathcliff", 33, "orange and black")

  val optionCat1 = Option(cat1)
  val optionCat2 = Option.empty[Cat]

  println(cat1 === cat2)
  println(cat1 =!= cat2)

  import cats.instances.option._
  println(optionCat1 === optionCat2)
  println(optionCat1 =!= optionCat2)
}
