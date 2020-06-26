package com.example.monocle.part2.step1

import java.util.UUID

import cats._
import cats.data._
import cats.implicits._

import io.scalaland.chimney._
import io.scalaland.chimney.dsl._

import monocle._
import monocle.macros._
import monocle.macros.syntax.lens._

object IsoExample extends App {
  println("─" * 75)

  final case class Dog(name: String)
  final case class Owner(name: String, pet: Dog)

  type A = (Int, String, Dog)
  type B = (Int, (String, Dog))
  type C = (Int, Owner)

  val isoAB: Iso[A, B] = Iso[A, B] {
    case (int, string, dog) => (int, (string, dog))
  } {
    case (int, (string, dog)) => (int, string, dog)
  }

  val isoBC: Iso[B, C] = Iso[B, C] {
    case (int, (string, dog)) => (int, Owner(string, dog))
  } {
    case (int, Owner(string, dog)) => (int, (string, dog))
  }

  val isoAC: Iso[A, C] = isoAB composeIso isoBC

  val isoCA: Iso[C, A] = isoAC.reverse

  println(isoAC.get((27, "Bob", Dog("Snoopy"))))

  val isoIsALens: Lens[C, A] = isoCA.asLens

  val fields: Iso[Owner, (String, Dog)] =
    GenIso.fields[Owner]

  val tuple: (String, Dog) =
    fields.get(Owner("Bob", Dog("Snoopy")))

  val owner1: Owner = fields.reverse.get(tuple)
  val owner2: Owner = fields.reverseGet(tuple)
  val owner3: Owner = fields(tuple)

  println(owner1)
  println(owner2)
  println(owner3)

  final case class Name(value: String) extends AnyVal

  val iso: Iso[Name, String] =
    GenIso[Name, String]

  val isoReversed: Iso[String, Name] =
    iso.reverse

  println(iso.get(Name("Vlad")))
  println(isoReversed.get("Vlad"))
  println(iso("Vlad"))

  println("─" * 75)
}
