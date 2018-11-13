package chapter3

trait Codec[A] {
  def encode(value: A): String
  def decode(value: String): A
  def imap[B](decoder: A => B, encoder: B => A): Codec[B] = new Codec[B] {
    val self = this
    override def encode(value: B): String = self.encode(encoder(value))

    override def decode(value: String): B = decoder(this.decode(value))
  }
}

object Codec {
  def encode[A](value: A)(implicit codec: Codec[A]): String =codec.encode(value)
  def decode[A](value: String)(implicit codec: Codec[A]): A = codec.decode(value)

  implicit val stringCodec: Codec[String] = new Codec[String] {
    override def encode(value: String): String = value

    override def decode(value: String): String = value

  }

  implicit val intCodec: Codec[Int] = stringCodec.imap(_.toInt, _.toString)

  implicit val booleanCodec: Codec[Boolean] = stringCodec.imap(_.toBoolean, _.toString)

}