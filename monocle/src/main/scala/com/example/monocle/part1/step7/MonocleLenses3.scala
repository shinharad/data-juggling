package com.example.monocle.part1.step7

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

// step7 - syntaxを使用する
object MonocleLenses3 extends App {
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

  val streetName: Lens[Nested, String] =
    GenLens[Nested](_.address.street.name)

  // syntaxでこう書ける
  println(nested.lens(_.address.street.name).set(styled("new street")))
  // println(streetName.set(styled("new street"))(nested))
  println(streetName.modify(styled)(nested))
  println(streetName.get(nested))

  val cityZipCode: Lens[Nested, String] =
    GenLens[Nested](_.address.city.zipCode)

  println(cityZipCode.set(styled("new zip code"))(nested))
  println(cityZipCode.modify(styled)(nested))
  println(cityZipCode.get(nested))

  println("─" * 75)

  ValidationExample
    .person(
      nested
    ) // Validated.Valid[Person]: ValidatedNec[String, Person]: Validated[NonEmptyChain[String], Person]
    .map { person =>
      val lens = Lens[Person, Person.Age](_.age) { target => source =>
        source.copy(age = target)
      }

      def transform(age: Person.Age): Person.Age =
        Person.Age(age.value + 10)

      lens.modify(transform)(person)
    }
    .foreach(println)

  println("─" * 75)
}
