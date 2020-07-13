package com.example.cats.part6

import com.example.cats._

object `09_MonadForOption` extends Monad[Option] {
  def pure[A](a: A): Option[A] =
    Some(a)

  def map[A, B](fa: Option[A])(f: A => B): Option[B] =
    fa match {
      case Some(a) => Some(f(a))
      case _       => None
    }

  def ap[A, B](fa: Option[A])(f: Option[A => B]): Option[B] =
    fa match {
      case Some(a) => f.map(ab => ab(a))
      case _       => None
    }

  // def ap2[A, B](fa: Option[A])(f: Option[A => B]): Option[B] =
  //   f match {
  //     case Some(ab) => fa.map(ab)
  //     case _       => None
  //   }

  def flatMap[A, B](fa: Option[A])(f: A => Option[B]): Option[B] =
    fa match {
      case Some(a) => f(a)
      case _       => None
    }
    
}
