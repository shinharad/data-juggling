package com.example.cats.part6

import cats._
import cats.data._
import cats.implicits._

object `04_Either` extends App {
  println("-" * 100)

  println(Right(1))
  println(Right[String, Int](1))
  println(1.asRight)
  println(1.asRight[String])
  
  type EitherStringOr[+A] = Either[String, A]
  println(1.pure[EitherStringOr]) 
  println(1.pure[Either[String, *]])
  // println(1.pure[Either[String, +*]])

  println(Left("error"))
  println(Left[String, Int]("error"))
  println("error".asLeft)
  println("error".asLeft[Int])

  println("-" * 100)
}