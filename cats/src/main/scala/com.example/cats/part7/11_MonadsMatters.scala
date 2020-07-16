package com.example.cats.part7

import cats._
import cats.data._
import cats.implicits._

import cats.effect._

object `11_MonadsMatters` extends App {
  println("-" * 100)

  val program: IO[Unit] =
    for {
      _ <- IO(println("What's your name"))
      name <- IO(io.StdIn.readLine)
      _ <- IO(println(s"You entered: $name"))
    } yield ()

  println("-" * 100)

  program.unsafeRunSync()

  println("-" * 100)
}
