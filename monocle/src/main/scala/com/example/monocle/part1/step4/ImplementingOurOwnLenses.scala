package com.example.monocle.part1.step4

import java.util.UUID

import cats._
import cats.data._
import cats.implicits._

import io.scalaland.chimney._
import io.scalaland.chimney.dsl._

import com.example.monocle.part1._

// step4 - Lensに高階関数get, modifyを渡せるように
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

  // format: OFF
  val streetName = Lens(_.address.street.name)(
    transform =>
      nested => 
        nested.copy(
          address = nested.address.copy(
            street = nested.address.street.copy(
                name = transform(nested.address.street.name)
              )
          )
        )
  )
  // format: ON

  println(streetName.set(styled("new street"))(nested))
  println(streetName.modify(styled)(nested))
  println(streetName.get(nested))

  // format: OFF
  val cityZipCode = Lens(_.address.city.zipCode)(
    transform =>
      nested => 
        nested.copy(
          address = nested.address.copy(
            city = nested.address.city.copy(
              zipCode = transform(nested.address.city.zipCode)
            )
          )
        )
  )
  // format: ON

  println(cityZipCode.set(styled("new zip code"))(nested))
  println(cityZipCode.modify(styled)(nested))
  println(cityZipCode.get(nested))

  println("─" * 75)
}
