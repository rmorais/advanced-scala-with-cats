package chapter1

import cats.Show
import cats.syntax.show._
import cats.instances.string._
import cats.instances.int._

object CatShow {
  implicit val showCat: Show[Cat] = Show.show { cat =>
    s" ${cat.name.show} is a ${cat.age.show} year-old ${cat.color.show} cat."
  }
}

object CatShowRunner extends App {
  import CatShow._
  val cat = Cat(name = "Teco", age = 4, color = "White")

  println(cat.show)
}
