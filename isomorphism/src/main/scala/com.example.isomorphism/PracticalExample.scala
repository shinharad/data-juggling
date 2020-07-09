package com.example.isomorphism

object PracticalExample extends App {
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
  // format: ON

  // 3 ^ 12
  // 531441

  //------------------------------------------------------

  // def m(int: Int): Boolean = ??? // 2 ^ (2 ^ 32) = 2 ^ 4294967296L = gazilion

  // def m[A](a: A): Boolean = false // 2 ^ 0 - 1

  // def m[A](a: A): Boolean = a match {
  //   case a: Int => a % 2 == 0
  //   case a: String => a.startsWith("hello")
  //   case a => false
  // }

  // def m[A](a: A, f: A => Boolean): Boolean = f(a) // 2 ^ (0 * 2 ^ 0) // 1

  // ---------------------

  def m1(list: List[Int]): List[String] = // Infinity
    list.map(_.toString)

  println(m1(List(1, 2, 3, 4)))

  // def m2(list: List[Int]): List[String] =
  //   list.map(_ + 1).map(_.toString)

  // def m2[A](list: List[A]): List[String] =
  //   list.map(a => Console.YELLOW + a + Console.RESET)

  // println(m2(List(1, 2, 3, 4))(_.toString))

  // def m2[A, B](list: List[A])(f: A => B): List[B] =
  //   list.reverse.map(f)

  // println(m2(List(1, 2, 3, 4))(a => Console.YELLOW + a + Console.RESET))

  trait Functor[C[_]] {
    def map[A, B](ca: C[A])(f: A => B): C[B]
  }

  implicit val ListFunctor: Functor[List] =
    new Functor[List] {
      def map[A, B](list: List[A])(f: A => B): List[B] =
        list.map(f)
    }

  final implicit class InfixNotationForHigherKinds[C[_], A](
    private val ca: C[A]
  ) extends AnyVal {
    @inline def map[B](ab: A => B)(implicit F: Functor[C]): C[B] =
      F.map(ca)(ab)
  }

  sealed abstract class Subset[-A, +B] extends Function1[A, B]
  object Subset {
    def WithColors[A]: Subset[A, String] =
      new Subset[A, String] {
        final override def apply(a: A): String =
          Console.YELLOW + a + Console.RESET
      }

    // def WithColors2[A]: Subset[A, String] =
    //   a => Console.YELLOW + a + Console.RESET

    def JustToString[A]: Subset[A, String] =
      new Subset[A, String] {
        final override def apply(a: A): String =
          a.toString
      }

    val IsEven: Subset[Int, Boolean] =
      new Subset[Int, Boolean] {
        final override def apply(a: Int): Boolean =
          a % 2 == 0
      }
  }

  def m2[L[_]: Functor, A, B](list: L[A])(f: Subset[A, B]): L[B] =
    list.map(f)

  println(m2(List(1, 2, 3, 4))(Subset.WithColors))
  println(m2(List(1, 2, 3, 4))(Subset.IsEven))

  def m3[L[_], A, B](list: L[A], f: Subset[A, B])(implicit F: Functor[L]): L[B] =
    list.map(f)

  println(m3(List(1, 2, 3, 4), Subset.WithColors))

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
