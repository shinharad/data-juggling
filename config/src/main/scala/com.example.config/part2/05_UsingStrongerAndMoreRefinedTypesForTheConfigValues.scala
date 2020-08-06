package com.example.config.part2

import java.net._

import scala.concurrent.duration._

import eu.timepit.refined.types.all._

object `05_UsingStrongerAndMoreRefinedTypesForTheConfigValues` extends App {
  println("-" * 50)

  import Config._

  final case class Config(db: Db, redis: Redis) extends NestedRendering(level = 1)

  object Config {
    implicit val spaces: Int = 4

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
      import pureconfig.generic.auto._

      ConfigSource
        .fromConfig(config.ConfigFactory.load("part2/application3.conf"))
        .loadOrThrow[Config]

    }
  }

  println(Config.instance)

  println("-" * 50)
}
