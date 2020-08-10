package com.example.functions

import Library._

object `02_LetsDefineOurOwnFunctionCombinators` extends App {
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

  def combine2a(int: Int): Option[Char] =
    (gg after f)(int)

  def combine2b(int: Int): Option[Char] =
    (gg <-- f)(int)

  def combine3(int: Int): Option[Char] =
    (ff andThen g)(int)

  def combine3a(int: Int): Option[Char] =
    (ff --> g)(int)

  def combine4(int: Int): Option[Char] =
    int pipe f pipe g

  def combine4a(int: Int): Option[Char] =
    int --> f --> g

  println(combine1(1337))
  println(combine2(1337))

  Seq[Int => Option[Char]](
    combine1,
    combine2,
    combine2a,
    combine2b,
    combine3,
    combine3a,
    combine4,
    combine4a
  ).map(f => f(1337)).foreach(println)

  println("-" * 100)
}
