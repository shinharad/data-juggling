package com.example.cats.part7

import cats._
import cats.data._
import cats.implicits._

import cats.effect._

object `12_productLR` extends App {
  println("-" * 100)

  // val program: IO[Unit] =
  //   for {
  //     _ <- IO(println("What's your name"))
  //     name <- IO(io.StdIn.readLine)
  //     _ <- IO(println(s"You entered: $name"))
  //   } yield ()

  // println("-" * 100)

  // program.unsafeRunSync()

  println(
    (
      10.validNec[String],
      20.validNec[String],
      30.validNec[String]
    ).mapN { (first, second, third) =>
      third
    }
  )
  // Valid(30)

  println(
    (
      10.validNec[String] *>
        20.validNec[String] *>
        30.validNec[String]
    ).map { third =>
      third
    }
  )
  // Valid(30)

  println(
    (
      10.validNec[String],
      20.validNec[String],
      30.validNec[String]
    ).mapN { (first, second, third) =>
      first
    }
  )
  // Valid(10)

  println(
    (
      10.validNec[String] <*
        20.validNec[String] <*
        30.validNec[String]
    ).map { first =>
      first
    }
  )
  // Valid(10)

  println(
    (
      10.validNec[String] productL
        20.validNec[String] productL
        30.validNec[String]
    ).map { first =>
      first
    }
  )
  // Valid(10)

  val program: IO[Unit] =
    for {
      // _ <- IO(println("What's your name"))

      // 入力する前に What's your name が表示される
      name <- IO(println("What's your name")) *> IO(io.StdIn.readLine)

      // 入力した後に What's your name が表示される
      // name <- IO(io.StdIn.readLine) <* IO(println("What's your name"))
      _ <- IO(println(s"You entered: $name"))
    } yield ()

  println("-" * 100)

  program.unsafeRunSync()

  println("-" * 100)
}
