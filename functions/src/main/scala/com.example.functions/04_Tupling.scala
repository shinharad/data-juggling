package com.example.functions

import Library._

object `04_Tupling` extends App {
  println("-" * 100)

  def plus(x: Int, y: Int): Int =
    x + y

  def plusTupled(tuple: (Int, Int)): Int = tuple match {
    case (x, y) => x + y
  }

  println(plusTupled((1, 2)))
  println(plusTupled(1 -> 2))

  val plusTupledFunc: ((Int, Int)) => Int = {
    case (x, y) => x + y
  }

  println(plusTupledFunc((1, 2)))

  def plusTupledAutomatically: ((Int, Int)) => Int =
    (plus _).tupled

  println(plusTupledAutomatically((1, 2)))

  val plusUntupled: (Int, Int) => Int =
    Function.untupled(plusTupled _)

  final case class PlusTupled(tuple: (Int, Int)) extends (() => Int) {
    final override def apply: Int = tuple match {
      case (x, y) => x + y
    }
  }

  println(PlusTupled((1, 2)).apply)

  println(1 -> 2 --> (plus _).tupled)
  println(1 -> 2 --> plusTupledAutomatically)

  println("-" * 100)
}
