package chapter4

import cats.Eval

object Eval2 {

  def foldRight[A, B](as: List[A], acc: B)(f: (A, B) => B): B =
    as match {
      case head :: tail => f(head, foldRight(tail, acc)(f))
      case Nil          => acc
    }

  def foldRightStackSafeNaive[A, B](as: List[A], acc: B)(
      f: (A, B) => B): Eval[B] = {
    as match {
      case Nil => Eval.now(acc)
      case head :: tail =>
        Eval.defer(foldRightStackSafeNaive(tail, acc)(f).map(b => f(head, b)))
    }
  }

  private def foldRightEval[A, B](as: List[A], acc: Eval[B])(
      f: (A, Eval[B]) => Eval[B]): Eval[B] =
    as match {
      case head :: tail => f(head, Eval.defer(foldRightEval(tail, acc)(f)))
      case Nil          => acc
    }

  def foldRightStackSafe[A, B](as: List[A], acc: B)(f: (A, B) => B): B =
    foldRightEval(as, Eval.now(acc)) { (a, b) =>
      b.map(f(a, _))
    }.value

}
