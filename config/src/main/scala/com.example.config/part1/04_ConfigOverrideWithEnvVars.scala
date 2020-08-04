package com.example.config.part1

import com.typesafe.config._

// bloop run config -m com.example.config.part1.04_ConfigOverrideWithEnvVars1 -- -J-Dkey1="value 1 from system.properties" -J-Dconfig.override_with_env_vars="true"
object `04_ConfigOverrideWithEnvVars1` extends App {
  println("-" * 50)

  val config: Config =
    ConfigFactory
      .load("application5.conf")

  println(config.getString("KEY1"))
  println(config.getString("key1"))

  // application5.conf
  // `key5 = "value 5 from application.conf"`
  //
  // .env
  // `key5="value 5 from .env"` 
  println(config.getString("key5"))
  // => value 5 from .env

  println("-" * 50)

}

// bloop run config -m com.example.config.part1.04_ConfigOverrideWithEnvVars2 -- -J-Dkey1="value 1 from system.properties" -J-Dconfig.override_with_env_vars="true"
//
// .env
// CONFIG_FORCE_parent_key6__with__dashes__and___underscores="value 6 from .env"
object `04_ConfigOverrideWithEnvVars2` extends App {
  println("-" * 50)

  val config: Config =
    ConfigFactory
      .load("application6.conf")

  println(config.getString("KEY1"))
  println(config.getString("key1"))
  println(config.getString("key5"))
  println(config.getString("parent.key6-with-dashes-and_underscores"))

  println("-" * 50)
}
