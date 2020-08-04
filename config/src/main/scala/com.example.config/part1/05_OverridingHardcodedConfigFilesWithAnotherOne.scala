package com.example.config.part1

import com.typesafe.config._

// bloop run config -m com.example.config.part1.05_OverridingHardcodedConfigFilesWithAnotherOne -- -J-Dkey1="value 1 from system.properties" -J-Dconfig.file=".global.conf" -J-Dconfig.override_with_env_vars="true"
object `05_OverridingHardcodedConfigFilesWithAnotherOne` extends App {
  println("-" * 50)

  val config: Config =
    ConfigFactory
      .load()

  println(config.getString("KEY1"))
  // => value 1 from .env
  println(config.getString("key1"))
  // => value 1 from system.properties
  println(config.getString("key2"))
  // => value 2 from .global.conf
  println(config.getString("key3"))
  // => value 3 from application.conf

  println("-" * 50)
}
