package com.example.cats.part7

import cats._
import cats.data._
import cats.implicits._

object `01_SimilaritiesBetweenApAndFlatMap` extends App {
  println("-" * 100)

  import com.example.cats.MonadForOption._

  println(
    for {
      one <- 1.some
      two <- 2.some
    } yield one + two
  )

  println(
    flatMap(1.some) { one =>
      map(2.some) { two =>
        one + two
      }
    }
  )

  println(
    flatMap(1.some) { one =>
      flatMap(2.some) { two =>
        (one + two).some
      }
    }
  )

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
    flatMap(none[Int]) { one =>
      flatMap(none[Int]) { two =>
        (one + two).some
      }
    }
  )

  println(
    flatMap(1.some) { one =>
      flatMap(2.some) { two =>
        none[Int]
      }
    }
  )

  println(
    ap[Int, Int](none) {
      ap[Int, Int => Int](2.some) {
        Some[Int => Int => Int] { two => one =>
          one + two
        }
      }
    }
  )

  println(
    ap[Int, Int](1.some) {
      ap[Int, Int => Int](none) {
        Some[Int => Int => Int] { two => one =>
          one + two
        }
      }
    }
  )
              
  println(
    ap[Int, Int](1.some) {
      ap[Int, Int => Int](2.some) {
        none[Int => Int => Int]
      }
    }
  )

  println("-" * 100)
}
