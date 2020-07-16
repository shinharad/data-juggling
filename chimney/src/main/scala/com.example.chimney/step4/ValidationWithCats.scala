package com.example.chimney.step4

import java.util.UUID

import cats._
import cats.data._
import cats.implicits._

import io.scalaland.chimney._
import io.scalaland.chimney.dsl._

object ValidationWithCats extends App {
  println("─" * 100)

  def intOrErrorMessage(
      field: String,
      fieldName: => String
    ): Either[String, Int] =
    Either
      .fromOption(
        field.toIntOption,
        s"${Console.RED}$fieldName must be a valid integer, which $field was not.${Console.RESET}"
      )
      .filterOrElse(
        _ >= 0,
        s"${Console.RED}$fieldName must be >= 0, which $field was not.${Console.RESET}"
      )

  def mapToValidatedNec[Result](
      either: Either[String, Int],
      transform: Int => Result
    ): ValidatedNec[String, Result] =
    either.map(transform).toValidatedNec

  val raw: Person.Raw =
    Person.Raw(
      name = "Bob",
      age = "hogehoge", // 不正な値
      // age = "37",
      heightInCm = "-100", // 不正な値
      // heightInCm = "170",
      phone = "+1-541-754-3010",
      address = Address.Raw(
        city = City.Raw(name = "New Yourk", zipCode = "10007"),
        street = Street.Raw(name = "5th Ave", "711")
      )
    )

  // toIntで安全じゃない
  // val person: Person =
  //   raw
  //     .into[Person]
  //     .withFieldConst(_.age, Person.Age(raw.age.toInt))
  //     .withFieldConst(_.heightInCm, Person.HeightInCm(raw.heightInCm.toInt))
  //     .transform

  println(raw)
  // println(person)

  println("─" * 50)

  // catsを利用したValidation（その1）
  val person1: ValidatedNec[String, Person] = {
    val fancy = Apply[ValidatedNec[String, *]]

    fancy.map2(
      mapToValidatedNec(intOrErrorMessage(raw.age, "age"), Person.Age),
      mapToValidatedNec(
        intOrErrorMessage(raw.heightInCm, "heightInCm"),
        Person.HeightInCm
      )
    )((age, heightInCm) =>
      raw
        .into[Person]
        .withFieldConst(_.age, age)
        .withFieldConst(_.heightInCm, heightInCm)
        .transform
    )
  }
  println(person1)

  println("─" * 50)

  // catsを利用したValidation（その2）
  val person2: ValidatedNec[String, Person] = {
    type ValidatedNecString[A] = ValidatedNec[String, A]
    val fancy = Apply[ValidatedNecString]

    fancy.map2(
      mapToValidatedNec(intOrErrorMessage(raw.age, "age"), Person.Age),
      mapToValidatedNec(
        intOrErrorMessage(raw.heightInCm, "heightInCm"),
        Person.HeightInCm
      )
    )((age, heightInCm) =>
      raw
        .into[Person]
        .withFieldConst(_.age, age)
        .withFieldConst(_.heightInCm, heightInCm)
        .transform
    )
  }
  println(person2)
  println("─" * 50)

  // catsを利用したValidation（その3）
  val person3: ValidatedNec[String, Person] = {
    Apply[ValidatedNec[String, *]].map2(
      mapToValidatedNec(intOrErrorMessage(raw.age, "age"), Person.Age),
      mapToValidatedNec(
        intOrErrorMessage(raw.heightInCm, "heightInCm"),
        Person.HeightInCm
      )
    )((age, heightInCm) =>
      raw
        .into[Person]
        .withFieldConst(_.age, age)
        .withFieldConst(_.heightInCm, heightInCm)
        .transform
    )
  }
  println(person3)

  println("─" * 100)
}
