package com.example.config.part2

import java.net._

import scala.concurrent.duration._

import eu.timepit.refined.auto._
import eu.timepit.refined.types.all._

import squants.space._
import squants.thermal._

object `06_IntegrationWithSquants` extends App {
  println("-" * 50)

  abstract class NestedRendering(
      level: PosInt
    )(implicit
      amountOfSpacesToIndentWith: PosInt = 2
    ) {
    this: Product =>

    final override def toString: String =
      if (productArity == 0)
        productPrefix // for case objects
      else
        s"$productPrefix(\n$currentLevelSpaces$pairs\n$previousLevelSpaces)"

    private[this] lazy val currentLevelSpaces: String =
      " " * (level * amountOfSpacesToIndentWith)

    private[this] lazy val previousLevelSpaces: String =
      " " * ((level - 1) * amountOfSpacesToIndentWith)

    private[this] lazy val pairs: String =
      productElementNames
        .zip(productIterator)
        .map {
          case (key, value) =>
            s"$key = $value"
        }
        .mkString(s"\n$currentLevelSpaces")

  }

  import Config._

  final case class Config(
      db: Db,
      redis: Redis,
      length: Length,
      temperature: Temperature
    ) extends NestedRendering(level = 1)

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
      import pureconfig.module.squants._

      ConfigSource
        .fromConfig(config.ConfigFactory.load("part2/application4.conf"))
        .loadOrThrow[Config]

    }
  }

  println(Config.instance)

  println("-" * 50)
}
