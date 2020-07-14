package com.example.cats.part7

import cats._
import cats.data._
import cats.implicits._

object `02_DifferencesBetweenApAndFlatMap` extends App {
  println("-" * 100)

  import com.example.cats.MonadForOption._

  println(
    flatMap(1.some) { one =>
      flatMap((one + 5).some) { two =>
        (one + two).some
      }
    }
  )

  val independent: Option[Int => Int] =
    ap[Int, Int => Int](2.some) {
      Some[Int => Int => Int] { two => anything =>
        anything + two
      }
    }

  println(
    ap[Int, Int](1.some) {
      independent
    }
  )

  def dependent(anything: Int): Option[Int] =
    flatMap((anything + 5).some) { two =>
      (anything + two).some
    }

  println(
    flatMap(1.some) {
      dependent
    }
  )

  println("-" * 100)
}
