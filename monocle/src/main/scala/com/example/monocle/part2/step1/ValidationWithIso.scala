package com.example.monocle.part2.step1

import java.util.UUID

import cats._
import cats.data._
import cats.implicits._

import io.scalaland.chimney._
import io.scalaland.chimney.dsl._

import monocle._
import monocle.macros._
import monocle.macros.syntax.lens._

import com.example.monocle.part2._

object ValidationWithIso extends App {
  println("─" * 75)

  import Person.Raw._

  val flat: Flat =
    Flat(
      name = "Bob",
      age = "37",
      heightInCm = "170",
      phone = "+1-541-754-3010",
      cityName = "New York",
      cityZipCode = "10007",
      streetName = "5th Ave",
      streetNumber = "711"
    )

  val nested: Nested =
    flat.toNested

  println(flat)
  println(nested)

  println("─" * 75)

  def styled(in: String): String =
    Console.GREEN + in + Console.RESET

  // println(nested.lens(_.address.street.name).modify(styled))
  // println(nested.lens(_.address.city.zipCode).modify(styled))

  // 段階的に簡潔な書き方にしていく
  ValidationExample
    .person(nested)
    .map { person =>
      def transform(age: Person.Age): Person.Age =
        Person.Age(age.value + 10)

      person.lens(_.age).modify(transform)
    }
    .foreach(println)

  println("─" * 75)

  ValidationExample
    .person(nested)
    .map { person =>
      GenLens[Person](_.age)
        .composeIso(GenIso[Person.Age, Int])
        .modify(_ + 10)(person)
    }
    .foreach(println)

  println("─" * 75)

  ValidationExample
    .person(nested)
    .map { person =>
      person
        .lens(_.age)
        .composeIso(GenIso[Person.Age, Int])
        .modify(_ + 10)
    }
    .foreach(println)

  println("─" * 75)

  // 段階的に簡潔な書き方にしていく
  val iso: Iso[Nested, Flat] =
    Iso[Nested, Flat](_.toFlat)(_.toNested)

  println(
    iso.modify(flat => flat.copy(streetName = styled(flat.streetName)))(nested)
  )

  println(
    iso.modify(_.lens(_.streetName).modify(styled))(nested)
  )

  println(
    iso.composeLens(GenLens[Flat](_.streetName)).modify(styled)(nested)
  )

  println(
    nested.lens(_.address.street.name).modify(styled)
  )

  println("─" * 75)
}
