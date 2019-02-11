package chapter4

import cats.data.Reader
import cats.syntax.applicative._

object ReaderMonad {

  case class Db(
      usernames: Map[Int, String],
      passwords: Map[String, String]
  )

  type DbReader[A] = Reader[Db, A]

  def findUsername(userId: Int): DbReader[Option[String]] = Reader { Db =>
    Db.usernames.get(userId)
  }

  def checkPassword(username: String, password: String): DbReader[Boolean] =
    Reader { db =>
      db.passwords.exists {
        case (u, p) => u == username && p == password
      }
    }

  def checkLogin(userId: Int, password: String): DbReader[Boolean] =
    for {
      username <- findUsername(userId)
      ok <- username
        .map(u => checkPassword(u, password))
        .getOrElse(false.pure[DbReader])
    } yield ok

}
