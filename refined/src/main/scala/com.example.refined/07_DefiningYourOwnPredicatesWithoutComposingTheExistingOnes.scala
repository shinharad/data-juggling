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

object `07_DefiningYourOwnPredicatesWithoutComposingTheExistingOnes1` extends App with TraceSupport {
  println("-" * 100)

  type FavoriteNumber = FavoriteNumber.type
  case object FavoriteNumber

  implicit val v1: Validate[Int, FavoriteNumber] =
    Validate.fromPartial(
      pf = { case 1337 => },
      name = "is favorite number",
      p = FavoriteNumber
    )

  println(refineV[FavoriteNumber](1337))
  println(refineV[FavoriteNumber](1338).leftMap(red))

  println("-" * 100)
}

object `07_DefiningYourOwnPredicatesWithoutComposingTheExistingOnes2` extends App with TraceSupport {
  println("-" * 100)

  type FavoriteNumber = FavoriteNumber.type
  case object FavoriteNumber

  implicit val v2: Validate[Int, FavoriteNumber] =
    Validate.fromPredicate(
      f = _ == 1337,
      showExpr = n => s"$n is not my favorite number",
      p = FavoriteNumber
    )

  println(refineV[FavoriteNumber](1337))
  println(refineV[FavoriteNumber](1338).leftMap(red))

  println(refineV(1337))

  println("-" * 100)
}
