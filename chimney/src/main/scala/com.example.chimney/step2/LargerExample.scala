package com.example.chimney.step2

import io.scalaland.chimney._
import io.scalaland.chimney.dsl._

object LargerExample extends App {
  println("─" * 100)

  val raw: Person.Raw =
    Person.Raw(
      name = "Bob",
      phone = "+1-541-754-3010",
      address = Address.Raw(
        city = City.Raw(name = "New Yourk", zipCode = "10007"),
        street = Street.Raw(name = "5th Ave", "711")
      )
    )

  val person: Person =
    raw.into[Person].transform

  println(raw)
  println(person)

  println("─" * 100)
}
