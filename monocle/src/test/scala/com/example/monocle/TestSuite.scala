package com.example.monocle

import org.scalacheck.ScalacheckShapeless

import org.scalatest._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should

import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

import org.typelevel.discipline.scalatest.FunSuiteDiscipline

trait TestSuite
    extends AnyFunSuite
       with should.Matchers
       with BeforeAndAfterAll
       with BeforeAndAfterEach
       with ScalaCheckPropertyChecks
       with ScalacheckShapeless
       with FunSuiteDiscipline {
  final protected type Assertion =
    org.scalatest.compatible.Assertion

  def assert[I, E](input: I, expected: E)(act: I => E): Assertion =
    act(input) shouldBe expected
}
