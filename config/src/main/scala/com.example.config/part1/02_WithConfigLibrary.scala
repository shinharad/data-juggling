package com.example.config.part1

import com.typesafe.config._

// bloop run config -m com.example.config.part1.02_OverrideEnvVars -- -J-Dkey1="value 1 from system.properties" -J-Dconfig.override_with_env_vars="true"
object `02_OverrideEnvVars` extends App {
  println("-" * 50)

  val config: Config =
    ConfigFactory.load()

  println(config.getString("KEY1"))
  println(config.getString("key1"))

  // Exception
  // println(config.getString("key2"))

  val properties: Config =
    ConfigFactory.load("config.properties")

  println(properties.getString("key1"))

  val properties2: Config =
    ConfigFactory.parseResources("application.conf")

  println(properties2.getString("key1"))

  println("-" * 50)
}
