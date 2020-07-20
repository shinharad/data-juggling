package com.example.refined

import cats.implicits._

import eu.timepit.refined._
import eu.timepit.refined.api._
import eu.timepit.refined.auto._
import eu.timepit.refined.numeric._
import eu.timepit.refined.types.numeric._

object `03_TypesInRefined1` extends App with TraceSupport {
  println("-" * 100)

  // val good: String Either (Int Refined Positive) =
  //   refineV(5)

  // val bad: String Either (Int Refined Positive) =
  //   refineV[Positive](-5).leftMap(red)

  val good: String Either PosInt =
    refineV(5)

  val bad: String Either PosInt =
    refineV[Positive](-5).leftMap(red)

  final case class Person(age: PosInt)

  println(
    Person(
      age = 5
    )
  )

  val result1: PosInt = Person(5).age
  val result2: Int = result1

}

object `03_TypesInRefined2` extends App with TraceSupport {
  println("-" * 100)

  println(PosInt.from(5))
  println(PosInt.from(-5).leftMap(red))

  println(PosInt.unsafeFrom(5))
  // println(PosInt.unsafeFrom(-5)) // compile error

  5 match {
    case PosInt(value) => println(green("good"))
    case _ => println(red("bad"))
  }

  -5 match {
    case PosInt(value) => println(green("good"))
    case _ => println(red("bad"))
  }

}

object `03_TypesInRefined3` extends App with TraceSupport {
  println("-" * 100)

  val good: String Either PosInt =
    refineV(5)

  val bad: String Either PosInt =
    refineV[Positive](-5).leftMap(red)

  // final case class Person(age: PosInt)

  // 5 match {
  //   case PosInt(value) => println(green("good"))
  //   case _ => println(red("bad"))
  // }

  // -5 match {
  //   case PosInt(value) => println(green("good"))
  //   case _ => println(red("bad"))
  // }

  type Number = Int Refined Positive
  object Number extends RefinedTypeOps.Numeric[Number, Int]

  5 match {
    case Number(value) => println(green("good"))
    case _ => println(red("bad"))
  }

  -5 match {
    case Number(value) => println(green("good"))
    case _ => println(red("bad"))
  }

  println("-" * 100)
}
