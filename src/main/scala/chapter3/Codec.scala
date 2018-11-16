package chapter3

trait Codec[A] {
  self =>
  def encode(value: A): String
  def decode(value: String): A
  def imap[B](decoder: A => B, encoder: B => A): Codec[B] = new Codec[B] {
    override def encode(value: B): String = self.encode(encoder(value))

    override def decode(value: String): B = decoder(self.decode(value))
  }
}

object Codec {
  def encode[A](value: A)(implicit codec: Codec[A]): String =codec.encode(value)
  def decode[A](value: String)(implicit codec: Codec[A]): A = codec.decode(value)


}


object CodecInstances {
  implicit val stringCodec: Codec[String] = new Codec[String] {
    override def encode(value: String): String = value

    override def decode(value: String): String = value

  }

  implicit val intCodec: Codec[Int] = stringCodec.imap(_.toInt, _.toString)

  implicit val booleanCodec: Codec[Boolean] = stringCodec.imap(_.toBoolean, _.toString)

  implicit val doubleCodec: Codec[Double] = stringCodec.imap(_.toDouble, _.toString)

  implicit def boxCodec[A](implicit ca: Codec[A]): Codec[Box[A]] = new Codec[Box[A]] {
    override def encode(box: Box[A]): String = ca.encode(box.value)

    override def decode(box: String): Box[A] = Box(ca.decode(box))
  }

}