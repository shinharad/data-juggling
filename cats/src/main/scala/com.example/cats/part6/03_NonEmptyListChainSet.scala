package com.example.cats.part6

import cats._
import cats.data._
import cats.implicits._

object `03_NonEmptyListChainSet` extends App {
  println("-" * 100)

  // NonEmptyList
  println(NonEmptyList.of(1, 2, 3))
  println(NonEmptyList.of(1))
  println(NonEmptyList.one(1))
  println(1.pure[NonEmptyList])

  println(NonEmptyList.fromList(List(1, 2, 3))) // Some(NonEmptyList(1, 2, 3))
  println(NonEmptyList.fromList(List.empty)) // None

  // NonEmptyChain
  println(NonEmptyChain(1, 2, 3))
  // println(NonEmptyChain.of(1, 2, 3))
  println(NonEmptyChain(1))
  println(NonEmptyChain.one(1))
  println(1.pure[NonEmptyChain])

  println(NonEmptyChain.fromSeq(List(1, 2, 3))) // Some(Chain(1, 2, 3))
  println(NonEmptyChain.fromSeq(List.empty)) // None

  // Chain
  println(Chain(1, 2, 3))
  println(Chain(1))
  println(Chain.one(1))
  println(1.pure[Chain])
  println(Chain.fromSeq(List(1, 2, 3))) // Chain(1, 2, 3)

  // NonEmptySet
  // println(NonEmptySet(1, 2, 3))
  println(NonEmptySet.of(1, 2, 3)) // TreeSet(1, 2, 3)
  println(NonEmptySet.of(1))
  println(NonEmptySet.one(1))
  // println(1.pure[NonEmptySet])
  println(NonEmptySet.fromSet(scala.collection.immutable.SortedSet(1, 2, 3))) // Some(TreeSet(1, 2, 3))

  println("-" * 100)
}