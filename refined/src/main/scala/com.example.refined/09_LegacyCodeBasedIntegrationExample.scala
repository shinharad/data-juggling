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

object `09_LegacyCodeBasedIntegrationExample` extends App with TraceSupport {
  println("-" * 100)

  def sum1(x: PosInt, y: PosInt): Either[String, PosInt] =
    PosInt.from(x + y)

  def sum2(x: PosInt, y: PosInt): Either[String, PosInt] =
    refineV(x + y) 

  def sum3(x: Int, y: PosInt): Either[String, PosInt] =
    refineV(x + y) 

  def sum4(x: PosInt, y: Int): Either[String, PosInt] =
    refineV(x + y) 

  println("-" * 100)
}
