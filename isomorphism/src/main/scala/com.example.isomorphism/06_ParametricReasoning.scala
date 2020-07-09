package com.example.isomorphism

object `06_ParametricReasoning` extends App {
  println("-" * 100)

  // def m(int: Int): Boolean = ??? // 2 ^ (2 ^ 32) = 2 ^ 4294967296L = gazilion

  // def m[A](a: A): Boolean = false // 2 ^ 0 - 1

  // def m[A](a: A): Boolean = a match {
  //   case a: Int => a % 2 == 0
  //   case a: String => a.startsWith("hello")
  //   case a => false
  // }

  // def m[A](a: A, f: A => Boolean): Boolean = f(a) // 2 ^ (0 * 2 ^ 0) // 1

  println("-" * 100)

}
