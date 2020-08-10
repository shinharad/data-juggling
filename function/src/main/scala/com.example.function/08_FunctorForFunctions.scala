package com.example.function

import Library._

object `08_FunctorForFunctions` extends App {
  println("-" * 100)

  final case class Person(name: String, startedOnDayOfYear: Int)

  val isEven: Int => Boolean =
    _ % 2 == 0

  def f(int: Int): String =
    int.toString

  def g(string: String): Option[Char] =
    string.headOption

  val ff: Int => String = f
  val gg: String => Option[Char] = g

  val a: Seq[Option[Char]] = 1 to 3 map f map gg tap println
  val b: Seq[Option[Char]] = 1 to 3 map ff -->> g tap println
  val c: Seq[Option[Char]] = 1 to 3 map (gg <<-- ff) tap println

  val didPersonStartOnDayOfYear: Person => Boolean =
    // isEven <-- (_.startedOnDayOfYear)
    // isEven contramap (_.startedOnDayOfYear)
    isEven <<-- (_.startedOnDayOfYear)

  println("-" * 100)
}