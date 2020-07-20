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

object `06_RefinedTypeWidening1` extends App with TraceSupport {
  println("-" * 100)

  type OurOwnPortNumber[Predicate] = Int Refined Predicate
  type Predicate1 = Not[Less[0]] And Not[Greater[65535]]
  type Predicate2 = GreaterEqual[0] And LessEqual[65535]
  type Predicate3 = Greater[-1] And LessEqual[65535]

  val port1: OurOwnPortNumber[Predicate1] = 1337
  val port2: OurOwnPortNumber[Predicate2] = 1337
  val port3: OurOwnPortNumber[Predicate3] = 1337

  // compile error
  // val int1: NonNegInt = port1
  // val int2: NonNegInt = port2
  // val int3: NonNegInt = port3

  // NonNegIntの定義
  // type NonNegInt = Int Refined NonNegative
  // type NonNegative = Not[Negative]
  // type Negative = Less[_0]
  // final case class Less[N](n: N)

  val int4: Int Refined GreaterEqual[0] = port1
  val int5: Int Refined GreaterEqual[0] = port2
  // val int6: Int Refined GreaterEqual[0] = port3 // これはcompile error

  println("-" * 100)
}

object `06_RefinedTypeWidening2` extends App with TraceSupport {
  println("-" * 100)

  val x: Int Refined Greater[5] = 6
  val y: Int Refined Greater[4] = x

  val zeroA: Int Refined GreaterEqual[0] = 0
  // val zeroB: Int Refined Greater[-1] = zeroA
  val zeroB: Int Refined Greater[-1] = 0
  // val zeroC: Int Refined Greater[-1] = zeroA
  val zeroD: Int Refined Greater[-1] = zeroB

  val zeroE: Int Refined Greater[-1] = zeroA.asInstanceOf[zeroE.type]
  println(zeroE)

  val zeroF: Int Refined Greater[-1] =
    Either.catchOnly[ClassCastException](zeroA.asInstanceOf[zeroF.type]).getOrElse(0)

  val zeroG: Int Refined Greater[-1] =
    refineV[Greater[-1]](zeroA.value).getOrElse(0)

  println("-" * 100)
}

object `06_RefinedTypeWidening3` extends App with TraceSupport {
  println("-" * 100)

  type Str = String Refined (ValidInt And StartsWith["1"] And EndsWith["37"])
  val whatever1: Str = "1337"

  // compile error
  // val whatever2: Str = "1a37"
  // val whatever3: Str = "2337"
  // val whatever3: Str = "1357"

  println("-" * 100)
}

object `06_RefinedTypeWidening4` extends App with TraceSupport {
  println("-" * 100)

  import shapeless._
  // type Str = String Refined (ValidInt And StartsWith["1"] And EndsWith["37"])
  type Str = String Refined AllOf[ValidInt :: StartsWith["1"] :: EndsWith["37"] :: HNil]

  // val whatever: Str = "1a37"

  // https://github.com/fthomas/refined

  println("-" * 100)
}
