package com.example.refined

import cats.implicits._

import eu.timepit.refined._
import eu.timepit.refined.api._
import eu.timepit.refined.auto._
import eu.timepit.refined.numeric._
import eu.timepit.refined.string._
import eu.timepit.refined.types.net._
import eu.timepit.refined.types.numeric._
import eu.timepit.refined.boolean._
import eu.timepit.refined.cats._
import eu.timepit.refined.cats.syntax._

// https://github.com/fthomas/refined#external-modules

object `08_IntegrationsWithTheRestOfTheEcosystem1` extends App with TraceSupport {
  println("-" * 100)

  type OneToTen = Int Refined Interval.Closed[1, 10]
  object OneToTen extends RefinedTypeOps[OneToTen, Int] with CatsRefinedTypeOpsSyntax

  println(OneToTen.from(5))
  println(OneToTen.from(-5).leftMap(red))
  // Right(5)
  // Left(Left predicate of (!(-5 < 1) && !(-5 > 10)) failed: Predicate (-5 < 1) did not fail.)

  println(OneToTen.validateNec(5))
  println(OneToTen.validateNec(-5).leftMap(_.map(red)))
  // Valid(5)
  // Invalid(Chain(Left predicate of (!(-5 < 1) && !(-5 > 10)) failed: Predicate (-5 < 1) did not fail.))

  println("-" * 100)
}

object `08_IntegrationsWithTheRestOfTheEcosystem2` extends App with TraceSupport {
  println("-" * 100)

  type OneToTen = Int Refined Interval.Closed[1, 10]
  object OneToTen extends RefinedTypeOps[OneToTen, Int] with CatsRefinedTypeOpsSyntax

  println(PosInt.validateNec(5))
  println(PosInt.validateNec(-5).leftMap(_.map(red)))
  // Valid(5)
  // Invalid(Chain(Predicate failed: (-5 > 0).))

  println(PosInt.validateNel(5))
  println(PosInt.validateNel(-5).leftMap(_.map(red)))
  // Valid(5)
  // Invalid(NonEmptyList(Predicate failed: (-5 > 0).))

  println(PosInt.validate(5))
  println(PosInt.validate(-5).leftMap(_.map(red)))
  // Valid(5)
  // Invalid(NonEmptyList(Predicate failed: (-5 > 0).))

  println(OneToTen.from(5))
  println(OneToTen.from(-5).leftMap(red))
  // Right(5)
  // Left(Left predicate of (!(-5 < 1) && !(-5 > 10)) failed: Predicate (-5 < 1) did not fail.)

  println(OneToTen.from(5).toValidated)
  println(OneToTen.from(-5).toValidated.leftMap(red))
  // Valid(5)
  // Invalid(Left predicate of (!(-5 < 1) && !(-5 > 10)) failed: Predicate (-5 < 1) did not fail.)

  println("-" * 100)
}
