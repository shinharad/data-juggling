package com.example.refined

import cats.data._
import cats.implicits._

import eu.timepit.refined.api._
import eu.timepit.refined.boolean._
import eu.timepit.refined.cats._
import eu.timepit.refined.cats.syntax._
import eu.timepit.refined.collection._
import eu.timepit.refined.string._
import eu.timepit.refined.types.numeric._

import Person._

final case class Person(name: Name, age: Age)
object Person {
  type Name = String Refined (NonEmpty And Trimmed)
  object Name extends RefinedTypeOps[Name, String] with CatsRefinedTypeOpsSyntax

  type Age = NonNegInt
  val Age = NonNegInt

  final case class Raw(name: String, age: Int)

  def apply(raw: Raw): ValidatedNec[String, Person] =
    (
      Name.validateNec(raw.name),
      Age.validateNec(raw.age)
    ).mapN(apply)
}
