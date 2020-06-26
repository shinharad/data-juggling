package com.example.monocle.part1.step8

import java.util.UUID

import cats._
import cats.data._
import cats.implicits._

import io.scalaland.chimney._
import io.scalaland.chimney.dsl._

import com.example.monocle.part1._

import monocle.Lens
import monocle.macros.GenLens
import monocle.macros.syntax.lens._

// step8 - Lens composition
object LensComposition extends App {
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

  def styled(in: String): String =
    Console.GREEN + in + Console.RESET

  println("─" * 75)

  val streetName: Lens[Nested, String] = {
    // GenLens[Nested](_.address.street.name)

    GenLens[Nested](_.address)
      .composeLens(GenLens[Address.Raw](_.street))
      .composeLens(GenLens[Street.Raw](_.name))
  }

  println(nested.lens(_.address.street.name).set(styled("new street")))
  println(streetName.modify(styled)(nested))
  println(streetName.get(nested))

  val cityZipCode: Lens[Nested, String] =
    GenLens[Nested](_.address.city.zipCode)

  println(cityZipCode.set(styled("new zip code"))(nested))
  println(cityZipCode.modify(styled)(nested))
  println(cityZipCode.get(nested))

  println("─" * 75)

  ValidationExample
    .person(nested)
    .map { person =>
      def transform(age: Person.Age): Person.Age =
        Person.Age(age.value + 10)

      person.lens(_.age).modify(transform)
    }
    .foreach(println)

  println("─" * 75)
}
