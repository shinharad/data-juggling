package com.example.cats

trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]
}

trait Semigroupal[F[_]] {
  def product[A, B](fa: F[A], fb: F[B]): F[(A, B)]
}

trait Apply[F[_]] extends Functor[F] with Semigroupal[F] {
  def ap[A, B](fa: F[A])(f: F[A => B]): F[B]

  def product[A, B](fa: F[A], fb: F[B]): F[(A, B)] =
    ap(fa)(map(fb)(b => a => (a, b)))
  
  def map2[A, B, Z](fa: F[A], fb: F[B])(f: (A, B) => Z): F[Z] =
    map(product(fa, fb)) { case (a, b) => f(a, b) }

  def map3[A, B, C, Z](fa: F[A], fb: F[B], fc: F[C])(f: (A, B, C) => Z): F[Z] =
    map(product(fa, product(fb, fc))) { case (a, (b, c)) => f(a, b, c) }
    // map(product((product(fa, fb)), fc)) { case ((a, b), c) => f(a, b, c) }

}

trait Applicative[F[_]] extends Apply[F] {
  def pure[A](a: A): F[A]
}

trait ApplicativeError[F[_], E] extends Applicative[F] {
  def raiseError[A](e: E): F[A]
}

trait Monad[F[_]] extends Applicative[F] {
  def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]
}
