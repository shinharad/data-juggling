package com.example.function

import Library._

object `03_Currying` extends App {
  println("-" * 100)

  def plus(x: Int, y: Int): Int =
    x + y

  println(plus(1, 2))

  def plusCurried(x: Int)(y: Int): Int =
    x + y

  println(plusCurried(1)(2))

  def plusCurriedManually(x: Int): Int => Int =
    y => x + y

  println(plusCurriedManually(1)(2))

  def plusCurriedEvenMoreManually: Int => Int => Int =
    x => y => x + y

  println(plusCurriedEvenMoreManually(1)(2))

  def plusCurriedAutomatically: Int => Int => Int =
    (plus _).curried

  println(plusCurriedAutomatically(1)(2))

  def plusUncurried: (Int, Int) => Int =
    Function.uncurried(plusCurried _)

  println(plusUncurried(1, 2))

  final case class Plus(x: Int) extends (Int => Int) {
    final override def apply(y: Int): Int =
      x + y
  }

  println(Plus(1)(2))

  println("-" * 100)
}
