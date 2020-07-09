package com.example.isomorphism

object `03_AlgebraicDataTypes` extends App {
  println("-" * 100)

  // val x: Nothing = ???
  val x: Unit = ()

  type `0` = Nothing
  type `1` = Unit

  type +[+L, +R] = Either[L, R]
  type +:[+L, +R] = Either[L, R]
  type *[+L, +R] = (L, R)

  type `2` = `1` + `1` // Either[Unit, Unit] // Left(()) | Right(())
  type `2a` = Boolean // false | true
  type `3a` = `1` + `1` + `1` // Left(Left(())) | Left(Right(())) | Right(())
  type `3b` = `1` + (`1` + `1`) // Left(()) | Right(Left()) | Right(Right(()))
  type `3c` = `1` +: `1` +: `1` // Left(()) | Right(Left()) | Right(Right(()))

  // type `3d` = 1 + (2 * 1) // Either[Unit, (Boolean, Unit)]]
  // 1 + 2 // Either[Unit, Boolean]

  // (Boolean, Unit) // false -> ()
  // (Boolean, Unit) // true -> ()

  println("-" * 100)

}
