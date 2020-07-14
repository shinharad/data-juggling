package com.example.cats.part7

import cats._
import cats.data._
import cats.implicits._

object `04_MapN` extends App {
  println("-" * 100)

  import com.example.cats.MonadForOption._

  println(
    ap[Int, Int](1.some) {
      ap[Int, Int => Int](2.some) {
        Some[Int => Int => Int] { two => one =>
          one + two
        }
      }
    }
  )

  println(
    Apply[Option].map2(Some(1), Some(2)) { (one, two) =>
      one + two
    }
  )

  println(
    Apply[Option].map3(Some(1), Some(2), Some(3)) { (one, two, three) =>
      one + two + three
    }
  )

  println(
    (1.some, 2.some, 3.some).mapN { (one, two, three) =>
      one + two + three
    }
  )

  println("-" * 100)
}
