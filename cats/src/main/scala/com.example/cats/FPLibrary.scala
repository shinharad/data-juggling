package com.example.cats
package fplibrary

trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]

  // def map[A, B](f: A => B): F[A] => F[B] =
  //   fa => map(fa)(f)

  // def map[A, B](fa: F[A])(f: A => B): F[B] =
  //   map(f)(fa)
}

trait Applicative[F[_]] extends Functor[F] {
  def pure[A](a: A): F[A]
}

// IO(println("hello world"))
// IO.delay(println("hello world"))
// IO.pure(println("hello world"))

trait ApplicativeError[F[_], E] extends Applicative[F] {
  def raiseError[A](e: E): F[A]

  // e.raiseError[F, E]
}