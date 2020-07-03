package com.example.monocle.part2.step2

import cats.derived.auto.eq._
import cats.implicits._

import monocle.Iso
import monocle.law.discipline.IsoTests

import org.scalacheck.Prop
import org.scalacheck.ScalacheckShapeless

import org.scalatestplus.scalacheck._

import com.example.monocle._

import com.example.monocle.part2._

final class ExampleSuite extends TestSuite with Checkers {
  test("iso") {
    import Person.Raw._

    val iso: Iso[Nested, Flat] =
      Iso[Nested, Flat](_.toFlat)(_.toNested)

    // NG
    // val iso: Iso[Nested, Flat] =
    //   Iso[Nested, Flat](_.toFlat.copy(name = "bla"))(_.toNested)

    val tests: IsoTests.RuleSet = IsoTests(iso)

    val properties: Seq[Prop] =
      tests.all.properties.map(_._2).to(Seq)

    properties.foreach(check(_))

  }
}
