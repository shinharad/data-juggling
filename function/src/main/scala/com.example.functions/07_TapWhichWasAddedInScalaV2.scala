package com.example.functions

import Library._

object `07_TapWhichWasAddedInScalaV2` extends App {
  println("-" * 100)

  def f(int: Int): String =
    int.toString

  def g(string: String): Option[Char] =
    string.headOption

  val ff: Int => String = f
  val gg: String => Option[Char] = g

  1 to 3 map f map gg pipe println
  1 to 3 map (ff andThen g) pipe println
  1 to 3 map (gg compose ff) pipe println

  val a: Seq[Option[Char]] = 1 to 3 map f map gg tap println
  val b: Seq[Option[Char]] = 1 to 3 map (ff andThen g) tap println
  val c: Seq[Option[Char]] = 1 to 3 map (gg compose ff) tap println

  println("-" * 100)
}
