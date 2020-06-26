package com.example.monocle.part1.step5

import com.example.monocle.part1._
import Person.Raw._

sealed abstract class Lens {
  def get(nested: Nested): String
  def set(newValue: String): Nested => Nested

  final def modify(transform: String => String): Nested => Nested =
    nested => set(transform(get(nested)))(nested)

  // final def modify(transform: String => String): Nested => Nested = { nested =>
  //   val currentValue = get(nested)
  //   val newValue = transform(currentValue)
  //   val newNested = set(newValue)(nested)
  //   newNested
  // }
}

object Lens {
  def apply(_get: Nested => String)(_set: String => (Nested => Nested)): Lens =
    new Lens {
      def get(nested: Nested): String = _get(nested)
      def set(newValue: String): Nested => Nested = _set(newValue)
    }
}
