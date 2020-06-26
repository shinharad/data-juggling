package com.example.monocle.part1.step2

import com.example.monocle.part1._

object StreetNameLens {
  import Person.Raw._

  def set(newStreetName: String): Nested => Nested =
    modify(_ => newStreetName)

  // def set(newStreetName: String): Nested => Nested =
  //   nested => modify(_ => newStreetName)(nested)

  // format: OFF
  def modify(transform: String => String): Nested => Nested =
    nested => 
      nested.copy(
        address = nested.address.copy(
          street = nested.address.street.copy(
              name = transform(get(nested))
            )
        )
      )
  // format: ON

  def get(nested: Nested): String =
    nested.address.street.name
}
