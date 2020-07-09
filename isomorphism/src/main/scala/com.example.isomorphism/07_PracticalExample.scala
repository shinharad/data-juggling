package com.example.isomorphism

object `07_PracticalExample` extends App {
  println("-" * 100)

  def m1(list: List[Int]): List[String] = // Infinity
    list.map(_.toString)
  println(m1(List(1, 2, 3, 4)))

  def m2(list: List[Int]): List[String] =
    list.map(_ + 1).map(_.toString)
  println(m2(List(1, 2, 3, 4)))

  def m3[A](list: List[A]): List[String] =
    list.map(a => Console.YELLOW + a + Console.RESET)
  println(m3(List(1, 2, 3, 4)))

  def m4[A, B](list: List[A])(f: A => B): List[B] =
    list.reverse.map(f)
  println(m4(List(1, 2, 3, 4))(a => Console.YELLOW + a + Console.RESET))

  println("-" * 100)

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

  def m5[L[_]: Functor, A, B](list: L[A])(f: Subset[A, B]): L[B] =
    list.map(f)

  println(m5(List(1, 2, 3, 4))(Subset.WithColors))
  println(m5(List(1, 2, 3, 4))(Subset.IsEven))

  def m6[L[_], A, B](list: L[A], f: Subset[A, B])(implicit F: Functor[L]): L[B] =
    list.map(f)
  println(m6(List(1, 2, 3, 4), Subset.WithColors))

  println("-" * 100)

}
