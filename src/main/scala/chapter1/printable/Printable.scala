package chapter1.printable

trait Printable[A] {

  def format(value: A): String

  def contramap[B](func: B => A): Printable[B] = {
    val self = this

    new Printable[B] {
      override def format(value: B): String = self.format(func(value))
    }
  }

}


object Printable {

  def format[A](value: A)(implicit printable: Printable[A]): String = printable.format(value)

  def print[A](value: A)(implicit printable: Printable[A]): Unit = println(format(value))

}