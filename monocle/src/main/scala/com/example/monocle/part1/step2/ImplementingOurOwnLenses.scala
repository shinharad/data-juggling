package com.example.monocle.part1.step2

import java.util.UUID

import cats._
import cats.data._
import cats.implicits._

import io.scalaland.chimney._
import io.scalaland.chimney.dsl._

import com.example.monocle.part1._

// step2 - streetNameに特化したLens
object ImplementingOurOwnLenses extends App {
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

  val lens = StreetNameLens

  println(lens.set(styled("new street"))(nested))
  println(lens.modify(styled)(nested))
  println(lens.get(nested))

  println("─" * 75)
}

// def modifyStreetName2(nested: Nested, transform: String => String): Nested =
//   GenLens[Nested](_.name.addredd.street.name).modify(transform)(nested)
//   nested.lens(_.name.addredd.street.name).modify(transform)
