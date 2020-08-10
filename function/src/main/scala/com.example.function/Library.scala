package com.example.function

object Library {
  object PointFree {
    def andThen[A, B, C](ab: A => B, bc: B => C): A => C =
      a => bc(ab(a))

    def compose[A, B, C](bc: B => C, ab: A => B): A => C =
      a => bc(ab(a))

    def flip[A, B, C](abc: A => B => C): B => A => C =
      b => a => abc(a)(b)
  }

  final implicit class SyntaxForAndThen[A, B](private val ab: A => B) {
    @inline final def -->[C](bc: B => C): A => C =
      PointFree.andThen(ab, bc)
  }

  final implicit class SyntaxForCompose[B, C](private val bc: B => C) {
    @inline final def <--[A](ab: A => B): A => C =
      PointFree.compose(bc, ab)

    @inline final def after[A](ab: A => B): A => C =
      PointFree.compose(bc, ab)
  }

  final implicit class SyntaxForPipe[A](private val a: A) {
    @inline final def pipe[B](ab: A => B): B = ab(a)

    @inline final def -->[B](ab: A => B): B = ab(a)

    @inline final def tap[U](ab: A => U): A = { ab(a); a }
  }

  final implicit class SyntaxForFlip[A, B, C](private val abc: A => B => C) {
    @inline final def flipped: B => A => C =
      PointFree.flip(abc)
  }

  trait Functor[F[_]] {
    def map[B, C](fa: F[B])(f: B => C): F[C]
  }

  trait ContravariantFunctor[F[_]] {
    def contramap[B, C](fb: F[B])(f: C => B): F[C]
  }

  type Contra[F[_]] = ContravariantFunctor[F]

  final implicit def FunctorForFunctionsFrom[A]: Functor[A => *] =
    new Functor[From[A]#To] {
      def map[B, C](fa: A => B)(f: B => C): A => C =
        fa andThen f
    }
  
  final implicit def ContraForFunctionsFrom[A]: Contra[* => A] =
    new Contra[To[A]#From] {
      def contramap[B, C](fb: B => A)(f: C => B): C => A =
        fb compose f
    }

  // private[this] type From[-A] = {
  //   type To[+B] = A => B
  // }
  private[this] type From[A] = {
    type To[+B] = A => B
  }

  // private[this] type To[+A] = {
  //   type From[-B] = B => A
  // }
  private[this] type To[A] = {
    type From[-B] = B => A
  }

  final implicit class SyntaxForMap[F[_]: Functor, B](private val fb: F[B]) {
    @inline final def map[C](f: B => C): F[C] =
      the[Functor[F]].map(fb)(f)

    @inline final def -->>[C](f: B => C): F[C] =
      map(f)
  }

  final implicit class SyntaxForContraMap[F[_]: Contra, B](
    private val fb: F[B]
  ) {
    @inline final def contramap[C](f: C => B): F[C] =
      the[Contra[F]].contramap(fb)(f)

    @inline final def <<--[C](f: C => B): F[C] =
      contramap(f)
  }

  final implicit class SyntaxForContraMapSpecificallyForFunctionos[A, B](
    private val fb: B => A
  ) {
    @inline final def contramap[C](f: C => B): C => A =
      the[Contra[* => A]].contramap(fb)(f)

    @inline final def <<--[C](f: C => B): C => A =
      contramap(f)
  }

  object the {
    def apply[A](implicit a: A): A = a
  }

}
