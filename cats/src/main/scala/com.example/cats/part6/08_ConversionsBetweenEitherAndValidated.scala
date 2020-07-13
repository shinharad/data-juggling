package com.example.cats.part6

import cats._
import cats.data._
import cats.implicits._

object `08_ConversionsBetweenEitherAndValidated` extends App {
  println("-" * 100)

  println(
    (
      for {
        one <- 1.valid[String].toEither
        two <- 2.asRight[String]
      } yield one + two
    ).toValidated
  )

  println(
    1.valid[String]
      .toEither
      .flatMap { one =>
        2.asRight[String].map { two =>
          one + two
        }
      }
      .toValidated
  )

  println(
    1.valid[String].withEither {
      _.flatMap { one =>
        2.asRight[String].map { two =>
          one + two
        }
      }
    }
  )

  println(
    1.valid[String].andThen { one =>
        2.valid[String].map { two =>
          one + two
        }
      }
  )

  println(
    1.asRight[String].flatMap { one =>
        2.asRight[String].map { two =>
          one + two
        }
      }
  )

  println("-" * 100)
}
