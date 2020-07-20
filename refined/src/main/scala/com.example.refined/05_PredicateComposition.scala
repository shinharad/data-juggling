package com.example.refined

import cats.implicits._

import eu.timepit.refined._
import eu.timepit.refined.api._
import eu.timepit.refined.auto._
import eu.timepit.refined.numeric._
import eu.timepit.refined.types.net._
import eu.timepit.refined.types.numeric._
import eu.timepit.refined.boolean._

object `05_PredicateComposition1` extends App with TraceSupport {
  println("-" * 100)

//   type OurOwnNumber = Int Refined Interval.Closed[0, 65535]

  type OurOwnNumber = Int Refined Predicate
  type Predicate = Interval.Closed[0, 65535]

  println("-" * 100)
}

object `05_PredicateComposition2` extends App with TraceSupport {
  println("-" * 100)

  type OurOwnNumber = Int Refined Predicate
  // type Predicate = Interval.Closed[0, 65535]

  // type Closed[L, H] = GreaterEqual[L] And LessEqual[H]
  type Predicate = GreaterEqual[0] And LessEqual[655]

  println("-" * 100)
}

object `05_PredicateComposition3` extends App with TraceSupport {
  println("-" * 100)

  type OurOwnNumber = Int Refined Predicate
  // type Predicate = GreaterEqual[0] And LessEqual[655]

  type Predicate = Not[Less[0]] And Not[Greater[65535]]

  println("-" * 100)
}
