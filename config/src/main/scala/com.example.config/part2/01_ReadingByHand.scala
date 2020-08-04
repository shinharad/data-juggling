package com.example.config.part2

import com.typesafe.config._

// bloop run config -m com.example.config.part2.01_ReadingByHand
object `01_ReadingByHand` extends App {
  println("-" * 50)

  val config: Config =
    ConfigFactory.load("part2/application.conf")

  println(config.getConfig("boolean"))

  println("-" * 50)

  println(config.getConfig("boolean").getBoolean("key1"))
  println(config.getConfig("boolean").getBoolean("key2"))
  println(config.getConfig("boolean").getBoolean("key3"))

  println("-" * 50)

  println(config.getBoolean("boolean.key1"))
  println(config.getBoolean("boolean.key2"))
  println(config.getBoolean("boolean.key3"))
  println(config.getBoolean("boolean.key4"))
  println(config.getBoolean("boolean.key5"))
  println(config.getBoolean("boolean.key6"))

  println("-" * 50)

  println(config.getString("number.key1"))
  println(config.getInt("number.key1"))
  println(config.getDouble("number.key1"))

  println("-" * 50)

  println(config.getDouble("number.key2"))
  println(config.getInt("number.key2"))

  println("-" * 50)

  println(config.getNumber("number.key1"))
  println(config.getNumber("number.key2"))

  println("-" * 50)

  println(config.getNumber("number.key1").intValue)
  println(config.getNumber("number.key2").doubleValue)

  println("-" * 50)

  println(config.getList("simpleKey"))
  println(config.getList("simpleKey").unwrapped)

  println("-" * 50)

  println(config.getString("stringLike.key1"))
  println(config.getString("stringLike.key2"))
  println(config.getString("stringLike.key3"))

  println("-" * 50)

  println(config.getMemorySize("stringLike.key2"))
  println(config.getDuration("stringLike.key3"))

  import scala.concurrent.duration._

  println(
    FiniteDuration(
      config.getDuration("stringLike.key3").toMillis,
      java.util.concurrent.TimeUnit.MILLISECONDS
    )
  )

  println(
    FiniteDuration(
      config.getDuration("stringLike.key3").toMinutes(),
      java.util.concurrent.TimeUnit.MINUTES
    )
  )

  println("-" * 50)
}
