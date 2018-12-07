package chapter4

import cats.Id

trait Monad[F[_]] {
  def pure[A](a: A): F[A]

  def flatMap[A,B](value: F[A])(f: A => F[B]): F[B]

  def map[A,B](value: F[A])(f: A => B): F[B] =
    flatMap(value)(f andThen pure)
}


object instances {

  implicit val idMonad: Monad[Id] = new Monad[Id] {
    override def pure[A](a: A): Id[A] = a

    override def flatMap[A, B](value: Id[A])(f: A => Id[B]): Id[B] = f(value)

    override def map[A, B](value: Id[A])(f: A => B): Id[B] = f(value)
  }
}