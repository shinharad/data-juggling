package com.example.isomorphism

object SumTypeAndProducts1 extends App {
  println("-" * 100)

  // val x: Nothing = ???
  val x: Unit = ()

  type `0` = Nothing
  type `1` = Unit

  type A = `1` // Unit
  type B = `0` + `1` // Either[Nothing, Unit]

  def aToB(a: A): B = Right(a) // Right(())
  def bToA(b: B): A =
    (b: @unchecked) match {
      case Right(value) => value
    }

  println(aToB(a = ()))
  // println(bToA(b = Left(???)))

  // 0 + 0
  // 0 + 1 // 1 + 0
  // 1 + 1 // 2

  // type StillNothing = Either[Nothing, Nothing] // Left(???) Right(???)
  // type StillNothing = Either[`0`, `0`]
  // type StillNothing = `0` Either `0`

  type +[+L, +R] = Either[L, R]
  type StillZero = `0` + `0`

  println("-" * 100)

}

object SumTypeAndProducts2 extends App {
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

  type `4` = `2` * `2` // (Either[Unit, Unit], Either[Unit, Unit])
  val a: `4` = (Left(()), Left(()))
  val b: `4` = (Left(()), Right(()))
  val c: `4` = (Right(()), Left(()))
  val d: `4` = (Right(()), Right(()))

  type `4b` = Boolean * Boolean // (Boolean, Boolean)
  val a1: `4b` = (false, false)
  val b1: `4b` = (false, true)
  val c1: `4b` = (true, false)
  val d1: `4b` = (true, true)

  final case class Person(isAdult: Boolean, isTall: Boolean)
  val p1: Person = Person(false, false)
  val p2: Person = Person(false, true)
  val p3: Person = Person(true, false)
  val p4: Person = Person(true, true)

  // object Custom {
  //   sealed abstract class Boolean extends Product with Serializable
  //   object Boolean {
  //     case object False extends Boolean
  //     case object True extends Boolean
  //   }

  //   type Option[+A] = Either[Unit, A] // () | ... A.size
  //   def Some[A](a: A): Option[A] = Right(a)
  //   val None: Option[Nothing] = Left(())

  //   type Maybe[+A] = scala.Boolean
  //   def Just[A](a: A): Maybe[A] = true
  //   val Nothing: Maybe[Nothing] = false
  // }

  println("-" * 100)

}
