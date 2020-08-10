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
  }

  final implicit class SyntaxForFlip[A, B, C](private val abc: A => B => C) {
    @inline final def flipped: B => A => C =
      PointFree.flip(abc)
  }
}