package com.example.config.part2

import java.net._

import scala.concurrent.duration._

import eu.timepit.refined.api._
import eu.timepit.refined.auto._
import eu.timepit.refined.generic._
import eu.timepit.refined.types.all._

import squants.space._
import squants.thermal._

// bloop run config -m com.example.config.part2.07_Misc -- -J-Dconfig.override_with_env_vars="true"
object `07_Misc` extends App {
  println("-" * 50)

  import Config._

  final case class Config(
      config: AlosoConfig,
      db: Db,
      redis: Redis,
      length: Length,
      temperature: Temperature
    ) extends NestedRendering(level = 1)

  object Config {
    final case class AlosoConfig(
        override_with_env_vars: Boolean Refined Equal[true]
      ) extends NestedRendering(level = 2) {
      override val productPrefix: String =
        "Config"
    }

    final case class Db(
        uri: URI,
        user: NonEmptyString,
        pw: NonEmptyString,
        timeout: Duration
      ) extends NestedRendering(level = 2)

    final case class Redis(
        host: URI,
        port: UserPortNumber,
        timeout: Duration
      ) extends NestedRendering(level = 2)

    val instance: Config = {
      import com.typesafe._

      import eu.timepit.refined.pureconfig._

      import pureconfig._
      import pureconfig.generic._
      import pureconfig.generic.auto._
      import pureconfig.module.squants._
      
      implicit def productHint[A]: ProductHint[A] =
        ProductHint(ConfigFieldMapping(CamelCase, CamelCase))

      ConfigSource
        .fromConfig(config.ConfigFactory.load("part2/application4.conf"))
        .loadOrThrow[Config]

    }
  }

  println(Config.instance)

  println("-" * 50)
}
