package com.example.chimney.step3

import java.util.UUID

import io.scalaland.chimney._
import io.scalaland.chimney.dsl._

object Limitations extends App {
  println("─" * 100)

  // Person.AgeはIntだが、Person.RawはStringを渡している
  val raw: Person.Raw =
    Person.Raw(
      name = "Bob",
      age = "37",
      phone = "+1-541-754-3010",
      address = Address.Raw(
        city = City.Raw(name = "New Yourk", zipCode = "10007"),
        street = Street.Raw(name = "5th Ave", "711")
      )
    )

  // このままではtransformできないので変換する必要がある
  val person: Person =
    raw.into[Person]
    .withFieldConst(_.age, Person.Age(raw.age.toInt))
    .transform

  println(raw)
  println(person)

  println("─" * 100)
}
