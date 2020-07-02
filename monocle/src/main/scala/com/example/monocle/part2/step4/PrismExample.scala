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

object PrismExample1 extends App {
  println("─" * 75)

  val prism1: Prism[TrafficLight, TrafficLight.Green] =
    Prism[TrafficLight, TrafficLight.Green] {
      case green @ TrafficLight.Green(_) => Some(green)
      case _                             => None
    }(identity)

  val prism2: Prism[TrafficLight, TrafficLight.Green] =
    Prism.partial[TrafficLight, TrafficLight.Green] {
      case green @ TrafficLight.Green(_) => green
    }(identity)

  val prism3: Prism[TrafficLight, TrafficLight.Green] =
    GenPrism[TrafficLight, TrafficLight.Green]

  Seq(prism1, prism2, prism3).foreach { prism =>
    println(prism.getOption(TrafficLight.Red(0.8)))
    println(prism.getOption(TrafficLight.Yellow(0.8)))
    println(prism.getOption(TrafficLight.Green(0.8)))
  }

  println("─" * 75)
}

object PrismExample2 extends App {
  println("─" * 75)

  val typicalPrism: Prism[TrafficLight, Double] =
    Prism.partial[TrafficLight, Double] {
      case TrafficLight.Green(opacity) => opacity
    }(TrafficLight.Green)

  println(typicalPrism.getOption(TrafficLight.Red(0.8)))
  println(typicalPrism.getOption(TrafficLight.Yellow(0.8)))
  println(typicalPrism.getOption(TrafficLight.Green(0.8)))

  println(typicalPrism.modify(_ + 1)(TrafficLight.Red(0.8)))
  println(typicalPrism.modify(_ + 1)(TrafficLight.Yellow(0.8)))
  println(typicalPrism.modify(_ + 1)(TrafficLight.Green(0.8)))

  println("─" * 75)
}

object PrismExample3 extends App {
  println("─" * 75)

  val typicalPrism: Prism[TrafficLight, Double] =
    Prism.partial[TrafficLight, Double] {
      case TrafficLight.Green(opacity) => opacity
    }(TrafficLight.Green)

  val prism: Prism[TrafficLight, Int] =
    typicalPrism
      .composePrism(
        Prism[Double, Int](double =>
          if (double % 1 == 0)
            Some(double.toInt)
          else
            None
        )(_.toDouble)
      )

  println(prism.modify(_ + 1)(TrafficLight.Red(0.8)))
  println(prism.modify(_ + 1)(TrafficLight.Yellow(0.8)))
  println(prism.modify(_ + 1)(TrafficLight.Green(0.8)))

  println(prism.modify(_ + 1)(TrafficLight.Green(1))) // 合致するものだけmodifyが適用される

  // マッチする場合は正常終了するが、しない場合はruntime error
  TrafficLight.Green(2) match {
    case prism(value) => println(value)
  }

  // runtime error
  // TrafficLight.Green(0.8) match {
  //   case prism(value) => println(value)
  // }

  // 静的にチェックできる
  // println(prism(0.8)) // compile error!!

  println(prism(2))

  println("─" * 75)
}
