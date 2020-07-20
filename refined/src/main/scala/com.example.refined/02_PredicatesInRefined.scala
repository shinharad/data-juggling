package com.example.refined

import cats.implicits._

import eu.timepit.refined._
import eu.timepit.refined.api._
import eu.timepit.refined.auto._
import eu.timepit.refined.numeric._

object `02_PredicatesInRefined01` extends App with TraceSupport {
  println("-" * 100)

  println(refineV[Positive](5))
  // Right(5)
  println(refineV[Positive](-5).leftMap(red))
  // Left(Predicate failed: (-5 > 0).)

  val good: Either[String, Refined[Int, Positive]] =
    refineV(5)

  val bad: Either[String, Refined[Int, Positive]] =
    refineV[Positive](-5).leftMap(red)

  println(good)
  println(bad)

  println("-" * 100)
}

// refineVの型パラメータは省略可能
object `02_PredicatesInRefined02` extends App with TraceSupport {
  println("-" * 100)

  val good: Either[String, Refined[Int, Positive]] =
    refineV(5)

  val bad: Either[String, Refined[Int, Positive]] =
    refineV[Positive](-5).leftMap(red)

  println(good)
  println(bad)

  println("-" * 100)
}

// Refinedを中置表記に
object `02_PredicatesInRefined03` extends App with TraceSupport {
  println("-" * 100)

  val good: Either[String, Int Refined Positive] =
    refineV(5)

  val bad: Either[String, Int Refined Positive] =
    refineV[Positive](-5).leftMap(red)

  println(good)
  println(bad)

  println("-" * 100)
}

// Eitherを中置表記に
object `02_PredicatesInRefined04` extends App with TraceSupport {
  println("-" * 100)

  val good: String Either (Int Refined Positive) =
    refineV(5)

  val bad: String Either (Int Refined Positive) =
    refineV[Positive](-5).leftMap(red)

  println(good)
  println(bad)

  println("-" * 100)
}

// refineMV(macros value)でcompile time error
object `02_PredicatesInRefined05` extends App with TraceSupport {
  println("-" * 100)

  println(refineMV[Positive](5))
  // println(refineMV[Positive](-5))

  val good1: Int Refined Positive =
    refineMV(5)

  // val bad1: Int Refined Positive =
  //   refineMV[Positive](-5)

  println("-" * 100)
}

// compile-time refinement only works with literals
object `02_PredicatesInRefined06` extends App with TraceSupport {
  println("-" * 100)

  // val notALiteral: Int = 5
  // val good1: Int Refined Positive =
  //   refineMV[Positive](notALiteral)

  println("-" * 100)
}

object `02_PredicatesInRefined07` extends App with TraceSupport {
  println("-" * 100)

  final case class Person(age: Int Refined Positive)

  println(
    Person(
      age = refineMV[Positive](5)
    )
  )

  // println(
  //   Person(
  //     age = refineMV[Positive](-5)
  //   )
  // )

  val result1: Int Refined Positive = Person(refineMV(5)).age
  val result2: Int = result1.value

  println("-" * 100)
}

// eu.timepit.refined.auto._ で refineMV を省略
object `02_PredicatesInRefined08` extends App with TraceSupport {
  println("-" * 100)

  final case class Person(age: Int Refined Positive)

  println(
    Person(
      age = 5
    )
  )

  // println(
  //   Person(
  //     age = -5
  //   )
  // )

  val result1: Int Refined Positive = Person(5).age
  val result2: Int = result1.value

  println("-" * 100)
}
