package chapter1

trait Printable[A] {
  self =>

  def format(a: A): String

  def contramap[B](f: B => A):Printable[B] = new Printable[B] {
    override def format(b: B): String = self.format(f(b))
  }
}

object PrintableInstances {

  implicit val intPrintable: Printable[Int] = new Printable[Int] {
    override def format(a: Int): String = a.toString
  }

  implicit val stringPrintable: Printable[String] = new Printable[String] {
    override def format(a: String): String = "\"" + a + "\""
  }

  implicit val booleanPrintable: Printable[Boolean] = new Printable[Boolean] {
    override def format(a: Boolean): String = if(a) "yes" else "no"
  }
 }

//Generic Interface methods
object Printable {

  def format[A](a: A)(implicit printable: Printable[A]): String = printable.format(a)

  def print[A](a: A)(implicit printable: Printable[A]): Unit = println(format(a))

}

//Extension Methods
object PrintableSyntax {

  implicit class PrintableOps[A](a: A) {
    def format(implicit p: Printable[A]): String = Printable.format(a)
    def print(implicit p: Printable[A]): Unit = Printable.print(a)
  }
}