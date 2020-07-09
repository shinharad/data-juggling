package com.example.isomorphism

object EquationalReasoning extends App {
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

  // (Boolean, Unit) // false -> ()
  // (Boolean, Unit) // true -> ()

  //------------------------------------------------

  // type `4` = `2` * `2` // (Either[Unit, Unit], Either[Unit, Unit])
  // val a: `4` = (Left(()), Left(()))
  // val b: `4` = (Left(()), Right(()))
  // val c: `4` = (Right(()), Left(()))
  // val d: `4` = (Right(()), Right(()))

  // type `4b` = Boolean * Boolean // (Boolean, Boolean)
  // val a1: `4b` = (false, false)
  // val b1: `4b` = (false, true)
  // val c1: `4b` = (true, false)
  // val d1: `4b` = (true, true)

  // final case class Person(isAdult: Boolean, isTall: Boolean)
  // val p1: Person = Person(false, false)
  // val p2: Person = Person(false, true)
  // val p3: Person = Person(true, false)
  // val p4: Person = Person(true, true)

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
  //------------------------------------------------

  // type `2` = Boolean
  // type `3` = Either[Unit, Boolean]

  // def m(in: `3`): `2` =
  //   in match {
  //     case Left(())     => false
  //     case Right(false) => true
  //     case Right(true)  => false
  //   }
  //------------------------------------------------

  // ((1 + 2 * 4) ^ 3) ^ 2 // = 531441
  // format: OFF
  type F =
    Boolean => Either[Unit, Boolean] => Either[Unit, (Boolean, (Boolean, Boolean))]

  // (1 + 2 * 4) ^ (2 * 3)
  type F1 =
    (Boolean, Either[Unit, Boolean]) => Either[Unit, (Boolean, (Boolean, Boolean))]
  
  // (8 + 1) ^ (2 * 3)
  type F2 =
    (Boolean, Either[Unit, Boolean]) => Either[(Boolean, Boolean, Boolean), Unit]
  
  // 9 ^ 6
  // (3 * 3) ^ 6
  // (3 ^ 2) ^ 6
  type F3 =
    (Boolean, Either[Unit, Boolean]) => Boolean => Either[Unit, Boolean]

  // (3 ^ (2 * 6))
  // (3 ^ (2 * 3 * 2))
  type F4 =
    (Boolean, Either[Unit, Boolean], Boolean) => Either[Unit, Boolean]

  // (3 ^ (3 * 2 * 2))
  type F5 =
    (Either[Unit, Boolean], Boolean, Boolean) => Either[Unit, Boolean]
    // Boolean => Either[Unit, Boolean] => Either[Unit, (Boolean, (Boolean, Boolean))]
  
  // 3 ^ 12
  // 531441

  def m(int: Int): Boolean = ??? // 2 ^ (2 ^ 32) = 2 ^ 4294967296L = gazilion

  println("-" * 100)

}
