package com.example.monocle.part2.step3

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

object UsingLensesToSetMultipleFields extends App {
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

  val iso: Iso[Nested, Flat] =
    Iso[Nested, Flat](_.toFlat)(_.toNested)

  def styled(in: String): String =
    Console.GREEN + in + Console.RESET

  val streetName: Lens[Nested, String] =
    GenLens[Nested](_.address.street.name)

  val cityZipCode: Lens[Nested, String] =
    GenLens[Nested](_.address.city.zipCode)

  val f: Nested => Nested = streetName.modify(styled)
  val g: Nested => Nested = cityZipCode.modify(styled)

  val fAfterG = f compose g
  val gAfterF = f andThen g

  println(fAfterG(nested))
  println(gAfterF(nested))

  println(
    Seq(
      GenLens[Nested](_.address.street.name),
      GenLens[Nested](_.address.city.zipCode)
    ).map(_.modify(styled)).reduceLeft(_ compose _)(nested)
  )

  println(
    Seq(
      GenLens[Nested](_.address.street.name).modify(styled),
      GenLens[Nested](_.address.city.zipCode).modify(styled)
    ).reduceLeft(_ compose _)(nested)
  )

  println(
    nested
      .lens(_.address.street.name)
      .modify(styled)
      .lens(_.address.city.zipCode)
      .modify(styled)
  )

  println(
    nested
      .lens(_.address.street.name)
      .modify(styled)
      .lens(_.address.city.zipCode)
      .set("adfsadf")
  )

  // println(nested.lens(_.address.street.name).modify(styled))
  // println(nested.lens(_.address.city.zipCode).modify(styled))

  println("─" * 75)
}
