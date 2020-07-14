package com.example.cats

object MonadForOption extends Monad[Option] {
  def pure[A](a: A): Option[A] =
    Some(a)

  def map[A, B](fa: Option[A])(f: A => B): Option[B] =
    fa match {
      case Some(a) => Some(f(a))
      case _       => None
    }

  override def ap[A, B](fa: Option[A])(f: Option[A => B]): Option[B] =
    fa match {
      case Some(a) => f.map(ab => ab(a))
      case _       => None
    }

  override def flatMap[A, B](fa: Option[A])(f: A => Option[B]): Option[B] =
    fa match {
      case Some(a) => f(a)
      case _       => None
    }

  def ap2[A, B](fa: Option[A])(f: Option[A => B]): Option[B] =
    flatMap(f)(map(fa))

  def flatMap2[A, B](fa: Option[A])(f: A => Option[B]): Option[B] =
    flatten(ap2[A, Option[B]](fa)(Some(f)))

  private def flatten[A](nested: Option[Option[A]]): Option[A] =
    nested match {
      case Some(oa) => oa
      case _        => None
    }

}
