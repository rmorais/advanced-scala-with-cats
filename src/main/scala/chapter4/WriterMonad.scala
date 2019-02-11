package chapter4

import cats.data.Writer
import cats.syntax.applicative._
import cats.syntax.writer._
import cats.instances.vector._

import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object WriterMonad {

  def slowly[A](body: => A) =
    try body
    finally Thread.sleep(500)

  def factorial(n: Int): Int = {
    val ans = slowly(if (n == 0) 1 else n * factorial(n - 1))
    println(s"fact $n $ans")
    ans
  }

  Await.result(
    awaitable = Future.sequence(
      Vector(
        Future(factorial(3)),
        Future(factorial(3))
      )
    ),
    atMost = 5.seconds
  )

  type Logged[A] = Writer[Vector[String], A]

  def factorial2(n: Int): Logged[Int] = {

    val ans = slowly(if (n == 0) 1.pure[Logged] else factorial2(n -1).map(_ * n))
    Vector(s"fact $n $ans").tell
    ans
  }

  def factorial3(n: Int): Logged[Int] = {
    for {
      ans <- slowly(if (n == 0) 1.pure[Logged] else factorial3(n -1).map(_ * n))
      _ <- Vector(s"fact $n $ans").tell
    } yield ans
  }

  def desugaredFactorial3(n: Int): Logged[Int]=
    slowly((if (n == 0) 1.pure[Logged] else desugaredFactorial3(n -1).map(_ * n)).flatMap{ans => Vector(s"fact $n $ans").tell.map(_ => ans)})
}
