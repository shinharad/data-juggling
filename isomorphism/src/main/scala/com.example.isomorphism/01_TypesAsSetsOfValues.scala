package com.example.isomorphism

object `01_TypesAsSetsOfValues` extends App {
  println("-" * 100)

  // val x: Nothing = ???
  val x: Unit = ()

  type `0` = Nothing
  type `1` = Unit

  case object Single
  type Single = Single.type

  // type A = Unit
  type A = `1`
  type B = Single

  def aToB(a: A): B = Single
  def bToA(b: B): A = ()

  println(aToB(a = ()))
  println(bToA(b = Single))

  println("-" * 100)
}
