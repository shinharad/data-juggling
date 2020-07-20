package com.example.refined

import cats.implicits._

import eu.timepit.refined._
import eu.timepit.refined.api._
import eu.timepit.refined.auto._
import eu.timepit.refined.numeric._
import eu.timepit.refined.types.net._
import eu.timepit.refined.types.numeric._

object `04_LiteralBasedSingletonTypes1` extends App with TraceSupport {
  println("-" * 100)

  // type PortNumber = Int Refined Interval.Closed[W.`0`.T, W.`65535`.T]

  val port: PortNumber = 1337
  // val port: PortNumber = -1337 // compile error

  type From = W.`0`.T
  type To = W.`65535`.T
  type OurOwnNumber = Int Refined Interval.Closed[From, To]

  case object Whatever
  type Whatever = Whatever.type

  val port2: OurOwnNumber = 1337

  // val to: To = 1337
  // val to: W.`65535`.T = 1337
  val to: W.`65535`.T = 65535

  // var to1: W.`65535`.T = 65535

  println("-" * 100)
}

object `04_LiteralBasedSingletonTypes2` extends App with TraceSupport {
  println("-" * 100)

  type OurOwnNumber = Int Refined Interval.Closed[0, 65535]
  // type OurOwnNumber = Int Refined Interval.Closed[From, To]

  val port: OurOwnNumber = 1337

  // val to: W.`65535`.T = 65535
  val to: 65535 = 65535
  var to1: 65535 = 65535

  println("-" * 100)
}

object `04_LiteralBasedSingletonTypes3` extends App with TraceSupport {
  println("-" * 100)

  type OurOwnNumber = Int Refined Interval.Closed[0, 65535]

  // val 1337: 1337 = 1337
  // val 1337: 1338 = 1338

  // var x: "hello" = "hello"
  // x = "world"

  println("-" * 100)
}
