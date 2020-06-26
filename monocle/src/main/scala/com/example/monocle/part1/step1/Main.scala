package com.example.monocle.part1.step1

import java.util.UUID

import cats._
import cats.data._
import cats.implicits._

import io.scalaland.chimney._
import io.scalaland.chimney.dsl._

import com.example.monocle.part1._

// step1 - Lensを使う前
object Main extends App {
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

  // format: OFF
  def setStreetName(nested: Nested, newStreetName: String): Nested =
    nested.copy(
      address = nested.address.copy(
        street = nested.address.street.copy(
            name = newStreetName
          )
      )
    )
  // format: ON

  // format: OFF
  def modifyStreetName(nested: Nested, transform: String => String): Nested =
    nested.copy(
      address = nested.address.copy(
        street = nested.address.street.copy(
            name = transform(nested.address.street.name)
          )
      )
    )
  // format: ON

  def styled(in: String): String =
    Console.GREEN + in + Console.RESET

  println("─" * 75)

  println(setStreetName(nested, styled("new street")))
  println(modifyStreetName(nested, styled))

  println("─" * 75)
}
