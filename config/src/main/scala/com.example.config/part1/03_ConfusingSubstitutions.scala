package com.example.config.part1

import com.typesafe.config._

// bloop run config -m com.example.config.part1.03_ConfusingSubstitutions1 -- -J-Dkey1="value 1 from system.properties" -J-Dconfig.override_with_env_vars="true"
object `03_ConfusingSubstitutions1` extends App {
  println("-" * 50)

  val config: Config =
    ConfigFactory.load("application1.conf")

  println(config.getString("key2"))

  // ↓↓↓ Exception ↓↓↓
  // key3 = ${KEY1} and ${key1} and ${user.home} all loaded from application.conf
  val properties: Config =
    ConfigFactory
      .parseResources("application1.conf")
      .resolve()

  println(properties.getString("key1"))
  println(properties.getString("key2"))

  println("-" * 50)

}

// bloop run config -m com.example.config.part1.03_ConfusingSubstitutions2 -- -J-Dkey1="value 1 from system.properties" -J-Dconfig.override_with_env_vars="true"
object `03_ConfusingSubstitutions2` extends App {
  println("-" * 50)

  val config: Config =
    ConfigFactory.load("application1.conf")

  println(config.getString("key2"))

  // val properties: Config =
  //   ConfigFactory
  //     .parseResources("application.conf")
  //     .resolve()

  println(config.getString("key1"))
  println(config.getString("key2"))
  println(config.getString("key3"))
  // => value 1 from .env and value 1 from system.properties and /Users/xxx all loaded from application.conf

  println("-" * 50)

}

// bloop run config -m com.example.config.part1.03_ConfusingSubstitutions3 -- -J-Dkey1="value 1 from system.properties" -J-Dconfig.override_with_env_vars="true"
object `03_ConfusingSubstitutions3` extends App {
  println("-" * 50)

  // application.conf
  // `key2 = ${?KEY2}`
  //
  // lower.conf
  // `key2 = "it's all good"`
  val config: Config =
    ConfigFactory
      .load("application2.conf")
      .withFallback(ConfigFactory.load("lower.conf"))

  println(config.getString("key1"))
  println(config.getString("key2"))

  println("-" * 50)

}

// bloop run config -m com.example.config.part1.03_ConfusingSubstitutions4 -- -J-Dkey1="value 1 from system.properties" -J-Dconfig.override_with_env_vars="true"
object `03_ConfusingSubstitutions4` extends App {
  println("-" * 50)

  // application.conf
  // `key2 = ${?KEY2}`
  //
  // lower.conf
  // `key2 = "it's all good"`
  val config: Config =
    ConfigFactory
      .load("application3.conf")
      .withFallback(ConfigFactory.load("lower.conf"))

  println(config.getString("key1"))
  println(config.getString("key2"))

  println("-" * 50)

}

// bloop run config -m com.example.config.part1.03_ConfusingSubstitutions5 -- -J-Dkey1="value 1 from system.properties" -J-Dconfig.override_with_env_vars="true"
object `03_ConfusingSubstitutions5` extends App {
  println("-" * 50)

  val config: Config =
    ConfigFactory
      .load("application4.conf")

  println(config.getString("key1"))
  println(config.getString("key2"))

  println("-" * 50)

}
