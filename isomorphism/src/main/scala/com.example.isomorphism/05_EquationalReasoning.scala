package com.example.isomorphism

object `05_EquationalReasoning` extends App {
  println("-" * 100)

  // ((1 + 2 * 4) ^ 3) ^ 2 // = 531441
  // format: OFF
  type F =
    Boolean => Either[Unit, Boolean] => Either[Unit, (Boolean, (Boolean, Boolean))]

  // (1 + 2 * 4) ^ (2 * 3)
  type F1 =
    (Boolean, Either[Unit, Boolean]) => Either[Unit, (Boolean, (Boolean, Boolean))]
  
  // (8 + 1) ^ (2 * 3)
  type F2 =
    (Boolean, Either[Unit, Boolean]) => Either[(Boolean, Boolean, Boolean), Unit]
  
  // 9 ^ 6
  // (3 * 3) ^ 6
  // (3 ^ 2) ^ 6
  type F3 =
    (Boolean, Either[Unit, Boolean]) => Boolean => Either[Unit, Boolean]

  // (3 ^ (2 * 6))
  // (3 ^ (2 * 3 * 2))
  type F4 =
    (Boolean, Either[Unit, Boolean], Boolean) => Either[Unit, Boolean]

  // (3 ^ (3 * 2 * 2))
  type F5 =
    (Either[Unit, Boolean], Boolean, Boolean) => Either[Unit, Boolean]
    // Boolean => Either[Unit, Boolean] => Either[Unit, (Boolean, (Boolean, Boolean))]
  
  // 3 ^ 12
  // 531441

  def m(int: Int): Boolean = ??? // 2 ^ (2 ^ 32) = 2 ^ 4294967296L = gazilion

  println("-" * 100)

}
