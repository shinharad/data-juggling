package com.example.config.part2

object `04_Pureconfig1` extends App {
  println("-" * 50)

  import Config._

  final case class Config(db: Db, redis: Redis) extends NestedRendering(level = 1)

  object Config {
    implicit val spaces: Int = 4

    final case class Db(
        uri: String,
        user: String,
        pw: String
      ) extends NestedRendering(level = 2)

    final case class Redis(host: String, port: Int) extends NestedRendering(level = 2)

    val instance: Config = {
      import com.typesafe._

      import pureconfig._
      import pureconfig.generic.auto._

      ConfigSource
        .fromConfig(config.ConfigFactory.load("part2/application2.conf"))
        .loadOrThrow[Config]

    }
  }

  println(Config.instance)

  println("-" * 50)
}

object `04_Pureconfig2` extends App {
  println("-" * 50)

  import Config._

  // フィールド名が同じであればクラス名は関係なく
  final case class Config(db: Whatever, redis: Redis) extends NestedRendering(level = 1)

  object Config {
    implicit val spaces: Int = 4

    final case class Whatever(
        uri: String,
        user: String,
        pw: String,
        myUser: String, // kebab caseは変換
      ) extends NestedRendering(level = 2)

    final case class Redis(host: String, port: Int) extends NestedRendering(level = 2)

    val instance: Config = {
      import com.typesafe._

      import pureconfig._
      import pureconfig.generic.auto._

      ConfigSource
        .fromConfig(config.ConfigFactory.load("part2/application2.conf"))
        .loadOrThrow[Config]

    }
  }

  println(Config.instance)

  println("-" * 50)
}

// confのcamel caseを読み込む
object `04_Pureconfig3` extends App {
  println("-" * 50)

  import Config._

  final case class Config(db: Whatever, redis: Redis) extends NestedRendering(level = 1)

  object Config {
    implicit val spaces: Int = 4

    final case class Whatever(
        uri: String,
        user: String,
        pw: String,
        myPw: String // camel case
      ) extends NestedRendering(level = 2)

    final case class Redis(host: String, port: Int) extends NestedRendering(level = 2)

    val instance: Config = {
      import com.typesafe._

      import pureconfig._
      import pureconfig.generic._
      import pureconfig.generic.auto._

      implicit def productHint[A]: ProductHint[A] =
        ProductHint(ConfigFieldMapping(CamelCase, CamelCase))

      ConfigSource
        .fromConfig(config.ConfigFactory.load("part2/application2.conf"))
        .loadOrThrow[Config]

    }
  }

  println(Config.instance)

  println("-" * 50)
}
