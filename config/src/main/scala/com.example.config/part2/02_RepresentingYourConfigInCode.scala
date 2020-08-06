package com.example.config.part2

object `02_RepresentingYourConfigInCode` extends App {
  println("-" * 50)

  import Config._

  final case class Config(db: Db, redis: Redis)

  object Config {
    final case class Db(
        uri: String,
        user: String,
        pw: String
      )

    final case class Redis(host: String, port: Int)

    val instance: Config = {
      import com.typesafe._

      val reader =
        config.ConfigFactory.load("part2/application2.conf")

      Config(
        db = Db(
          uri = reader.getString("db.uri"),
          user = reader.getString("db.user"),
          pw = reader.getString("db.pw")
        ),
        redis = Redis(
          host = reader.getString("redis.host"),
          port = reader.getInt("redis.port")
        )
      )

    }
  }

  println(Config.instance)

  println("-" * 50)
}
