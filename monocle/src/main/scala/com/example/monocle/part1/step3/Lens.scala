package com.example.monocle.part1.step3

import com.example.monocle.part1._
import Person.Raw._

abstract class Lens {
  def get(nested: Nested): String
  def modify(transform: String => String): Nested => Nested

  final def set(newValue: String): Nested => Nested =
    modify(_ => newValue)
}
