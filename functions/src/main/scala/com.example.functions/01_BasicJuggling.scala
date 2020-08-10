package com.example.functions

object `01_BasicJuggling` extends App {
  println("-" * 100)

  def f(int: Int): String =
    int.toString

  def g(string: String): Option[Char] =
    string.headOption

  println(f(1337))
  println(g("1337"))
  println(g(f(1337)))

  val ff: Int => String = f
  val gg: String => Option[Char] = g

  def combine1(int: Int): Option[Char] =
    g(f(int))

  def combine2(int: Int): Option[Char] =
    (gg compose f)(int)
    // (g _ compose f)(int)

  def combine3(int: Int): Option[Char] =
    (ff andThen g)(int)

  def combine4(int: Int): Option[Char] = {
    import scala.util.chaining._

    int pipe f pipe g
  }

  println(combine1(1337))
  println(combine2(1337))

  Seq[Int => Option[Char]](
    combine1,
    combine2,
    combine3,
    combine4
  ).map(f => f(1337)).foreach(println)

  println("-" * 100)
}
