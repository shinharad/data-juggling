package com.example.monocle.part1.step4

import com.example.monocle.part1._
import Person.Raw._

sealed abstract class Lens {
  def get(nested: Nested): String
  def modify(transform: String => String): Nested => Nested

  final def set(newValue: String): Nested => Nested =
    modify(_ => newValue)
}

object Lens {
  def apply(
      _get: Nested => String
    )(
      _modify: (String => String) => (Nested => Nested)
    ): Lens =
    new Lens {
      def get(nested: Nested): String =
        _get(nested)
      def modify(transform: String => String): Nested => Nested =
        _modify(transform)
    }
}
