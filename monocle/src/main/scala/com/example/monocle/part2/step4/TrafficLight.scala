package com.example.monocle.part2.step4

sealed abstract class TrafficLight extends Product with Serializable
object TrafficLight {
  final case class Red(opacity: Double) extends TrafficLight {
    final override def toString: String =
      Console.RED + s"Red($opacity)" + Console.RESET
  }

  final case class Yellow(opacity: Double) extends TrafficLight {
    final override def toString: String =
      Console.YELLOW + s"Yellow($opacity)" + Console.RESET
  }

  final case class Green(opacity: Double) extends TrafficLight {
    final override def toString: String =
      Console.GREEN + s"Green($opacity)" + Console.RESET
  }
}
