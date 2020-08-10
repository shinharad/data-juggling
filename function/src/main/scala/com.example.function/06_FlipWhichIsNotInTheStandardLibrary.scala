package com.example.function

import Library._

object `06_FlipWhichIsNotInTheStandardLibrary` extends App {
  println("-" * 100)

  final case class Person(name: String, startedOnDayOfYear: Int)

  println(Person.curried("Alice")(1))
  println(Person.curried("Bob")(2))

  println(Person.curried.flipped(1)("Alice"))
  println(Person.curried.flipped(2)("Bob"))

  def f(int: Int): String =
    int.toString

  def g(string: String): Option[Char] =
    string.headOption

  val ff: Int => String = f
  val gg: String => Option[Char] = g

  println("-" * 100)

  1 to 3 map f map gg --> println
  1 to 3 map ff --> g --> println
  1 to 3 map (gg <-- ff) --> println

  1 to 3 map f map gg --> println
  1 to 3 map (ff andThen g) --> println
  1 to 3 map (gg compose ff) --> println

  println("-" * 100)
}
