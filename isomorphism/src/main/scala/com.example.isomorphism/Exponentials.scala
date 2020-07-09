package com.example.isomorphism

object Exponentials extends App {
  println("-" * 100)

  // val x: Nothing = ???
  val x: Unit = ()

  type `0` = Nothing
  type `1` = Unit

  type +[+L, +R] = Either[L, R]
  type +:[+L, +R] = Either[L, R]
  type *[+L, +R] = (L, R)

  // type `2` = `1` + `1` // Either[Unit, Unit] // Left(()) | Right(())
  type `2a` = Boolean // false | true
  type `3a` = `1` + `1` + `1` // Left(Left(())) | Left(Right(())) | Right(())
  type `3b` = `1` + (`1` + `1`) // Left(()) | Right(Left()) | Right(Right(()))
  type `3c` = `1` +: `1` +: `1` // Left(()) | Right(Left()) | Right(Right(()))

  // type `3d` = 1 + (2 * 1) // Either[Unit, (Boolean, Unit)]]
  // 1 + 2 // Either[Unit, Boolean]

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
