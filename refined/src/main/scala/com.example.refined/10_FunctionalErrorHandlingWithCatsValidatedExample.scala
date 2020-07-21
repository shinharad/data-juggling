package com.example.refined

import cats.data._
import cats.implicits._

import eu.timepit.refined._
import eu.timepit.refined.api._
import eu.timepit.refined.auto._
import eu.timepit.refined.numeric._
import eu.timepit.refined.string._
import eu.timepit.refined.types.net._
import eu.timepit.refined.types.numeric._
import eu.timepit.refined.boolean._
import eu.timepit.refined.cats._
import eu.timepit.refined.cats.syntax._

object `10_FunctionalErrorHandlingWithCatsValidatedExample` extends App with TraceSupport {
  println("-" * 100)

  val raw: Person.Raw =
    Person.Raw(name = "", age = -32)
    // Person.Raw(name = " ", age = -32)
    // Person.Raw(name = "Good", age = -32)
    // Person.Raw(name = "Bad ", age = 32)
    // Person.Raw(name = "Good", age = 32)

  println(raw)

  val person: ValidatedNec[String, Person] =
    Person(raw).leftMap(_.map(red))

  println(person)

  println(Person(name = "Good", age = 32))
  // println(Person(name = "Good ", age = 32)) // compile error

  println("-" * 100)
}
