package com.example.monocle.part1.step6

import com.example.monocle.part1._
import Person.Raw._

sealed abstract class Lens[Source, Target] {
  def get(source: Source): Target
  def set(target: Target): Source => Source

  final def modify(transform: Target => Target): Source => Source =
    source => set(transform(get(source)))(source)
}

object Lens {
  def apply[Source, Target](_get: Source => Target)(_set: Target => (Source => Source)): Lens[Source, Target] =
    new Lens[Source, Target] {
      def get(source: Source): Target = _get(source)
      def set(newValue: Target): Source => Source = _set(newValue)
    }
}
