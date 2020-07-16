package com.example.cats.part7

import cats._
import cats.data._
import cats.implicits._

object `09_Parallel` extends App {
  println("-" * 100)

  println(
    (
      List('a', 'b', 'c'),
      List(1, 2, 3)
    ).mapN { (c, n) =>
      c -> n
    }
  )
  // List((a,1), (a,2), (a,3), (b,1), (b,2), (b,3), (c,1), (c,2), (c,3))

  println {
    for {
      c <- List('a', 'b', 'c')
      n <- List(1, 2, 3)
    } yield c -> n
  }
  // List((a,1), (a,2), (a,3), (b,1), (b,2), (b,3), (c,1), (c,2), (c,3))

  println(
    (
      List('a', 'b', 'c'),
      List(1, 2, 3)
    ).mapN(Tuple2[Char, Int])
  )

  println(
    (
      List('a', 'b', 'c'),
      List(1, 2, 3)
    ).mapN(Tuple2.apply)
  )

  println(
    (
      List('a', 'b', 'c'),
      List(1, 2, 3)
    ).tupled
  )

  // parMapN
  println(
    (
      List('a', 'b', 'c'),
      List(1, 2, 3)
    ).parMapN { (c, n) =>
      c -> n
    }
  )
  // List((a,1), (b,2), (c,3))

  // Either
  println(
    (
      "error 1".leftNec[Int],
      "error 2".leftNec[Int],
      "error 3".leftNec[Int]
    ) mapN { (one, two, three) =>
      one + two + three
    }
  )
  // Left(Chain(error 1))

  println(
    (
      "error 1".leftNec[Int],
      "error 2".leftNec[Int],
      "error 3".leftNec[Int]
    ).parMapN { (one, two, three) =>
      one + two + three
    }
  )
  // Left(Chain(error 1, error 2, error 3))

  println("-" * 100)
}
