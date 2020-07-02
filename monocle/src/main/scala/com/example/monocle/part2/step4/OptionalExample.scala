package com.example.monocle.part2.step4

import java.util.UUID

import cats._
import cats.data._
import cats.implicits._

import io.scalaland.chimney._
import io.scalaland.chimney.dsl._

import monocle._
import monocle.macros._
import monocle.macros.syntax.lens._

object OptionalExample1 extends App {
  println("─" * 75)

  val optional: Optional[Int, String] =
    Optional[Int, String](int =>
      if (int % 2 == 0)
        Some(int.toString)
      else
        None
    )(string =>
      currentInt => string.toIntOption.filter(_ % 2 == 0).getOrElse(currentInt)
    )

  println(optional.getOption(99))
  println(optional.getOption(100))
  println(optional.getOption(100).map(_.reverse))
  println(optional.modify(_.reverse)(100) + 10)
  println(optional.modify(_.reverse)(100): Int)

  // 100
  //   .toString            // "100"
  //   .reverse             // "001"
  //   .toIntOption         // Some(1)
  //   .filter(_ % 2 == 0)  // None
  //   .getOrElse(100)      // 100

  println("─" * 75)
}

object OptionalExample2 extends App {
  println("─" * 75)

  val optional: Optional[Int, String] =
    Optional[Int, String](int =>
      if (int % 2 == 0)
        Some(int.toString)
      else
        None
    )(string =>
      currentInt => string.toIntOption.filter(_ % 2 == 0).getOrElse(currentInt)
    )

  val crazyOptional: Optional[TrafficLight, String] =
    GenPrism[TrafficLight, TrafficLight.Green]
      .composeIso(GenIso[TrafficLight.Green, Double])
      .composePrism(
        Prism[Double, Int](double =>
          if (double % 1 == 0)
            Some(double.toInt)
          else
            None
        )(_.toDouble)
      )
      .composeOptional(optional)

  println(crazyOptional.modify(_.reverse)(TrafficLight.Red(1338)))
  println(crazyOptional.modify(_.reverse)(TrafficLight.Green(1338.2)))
  println(crazyOptional.modify(_.reverse)(TrafficLight.Green(1337)))
  println(crazyOptional.modify(_.reverse)(TrafficLight.Green(1338)))
  println(crazyOptional.modify(_.reverse)(TrafficLight.Green(2338))) // match
  println(crazyOptional.modify(_.reverse)(TrafficLight.Red(2338)))

  // TrafficLight -> Green -> Double -> Int -> Even -> ReverseAlsoEven

  println("─" * 75)
}
