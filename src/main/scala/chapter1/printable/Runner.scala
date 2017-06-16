package chapter1.printable

import chapter1.Cat
import PrintableInstances._
import PrintableSyntax._

object Runner extends App {

  val cat = Cat(name = "Tioma", age = 8, color = "gray")

  cat.print
}
