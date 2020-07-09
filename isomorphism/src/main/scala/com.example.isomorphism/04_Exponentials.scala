package com.example.isomorphism

object `04_Exponentials` extends App {
  println("-" * 100)

  type `2` = Boolean
  type `3` = Either[Unit, Boolean]

  def m(in: `3`): `2` =
    in match {
      case Left(())     => false
      case Right(false) => true
      case Right(true)  => false
    }

  // false = 0
  // true =  1
  // 0 0 0 | 0
  // 0 0 1 | 1
  // 0 1 0 | 2
  // 0 1 1 | 3
  // 1 0 0 | 4
  // 1 0 1 | 5
  // 1 1 0 | 6
  // 1 1 1 | 7

  println("-" * 100)

}
