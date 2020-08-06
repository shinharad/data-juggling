package com.example.config.part2

object `03_RenderingTheConfigInACoolWay` extends App {
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
