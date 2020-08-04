package com.example.config.part1

// sbt "config/runMain com.example.config.part1.FromDirenv" 
object FromDirenv extends App {
  println("-" * 50)

  println(System.getenv("KEY1"))

  println("-" * 50)
}

// bloop run config -m com.example.config.part1.FromJvmArgs -- -J-Dkey1="value 1 from system.properties"
// or
// sbt "config/runMain com.example.config.part1.FromJvmArgs" -J-Dkey1="value 1 from system.properties"
object FromJvmArgs extends App {
  println("-" * 50)

  println(System.getProperty("key1"))

  println("-" * 50)
}

// sbt "config/runMain com.example.config.part1.WithProperties" -J-Dkey1="value 1 from system.properties"
object WithProperties extends App {
  println("-" * 50)

  println(scala.util.Properties.envOrNone("KEY1"))
  // => Some(value 1 from .env)
  println(scala.util.Properties.propOrNone("key1"))
  // => Some(value 1 from system.properties)

  // println(System.getProperty("user.home"))
  // println(scala.util.Properties.userHome)
  // println(scala.util.Properties.versionNumberString)

  println("-" * 50)
}

// bloop run config -m com.example.config.part1.FromProperties -- -J-Dkey1="value 1 from system.properties"
// 
// これはException
// sbt "config/runMain com.example.config.part1.FromProperties" -J-Dkey1="value 1 from system.properties"
object FromProperties extends App {
  println("-" * 50)

  val properties: java.util.Properties = {
    val mutableProperties =
      new java.util.Properties

    val stream: java.io.InputStream =
      getClass
        .getClassLoader
        .getResourceAsStream("part1/.properties")

    mutableProperties.load(stream)

    mutableProperties
  }

  println(properties.getProperty("key1"))
  // => value 1 from .properties

  println("-" * 50)
}

// sbt "config/runMain com.example.config.part1.FromProperties2" -J-Dkey1="value 1 from system.properties"
object FromProperties2 extends App {
  println("-" * 50)

  val properties: java.util.Properties = {
    val mutableProperties =
      new java.util.Properties

    val stream: java.io.InputStream =
      getClass
        .getClassLoader
        .getResourceAsStream("part1/config.properties")

    mutableProperties.load(stream)

    mutableProperties
  }

  println(properties.getProperty("key1"))
  // => value 1 from config.properties

  println("-" * 50)
}

// .jvmopts
// ```
// -Dkey1=jvmopts
// ```
//
// sbt "config/runMain com.example.config.part1.FromJvmopts" 
//
// bloopはnullになる
// bloop run config -m com.example.config.part1.FromJvmopts
object FromJvmopts extends App {
  println("-" * 50)

  println(System.getProperty("key1"))
  // => jvmopts

  println("-" * 50)
}

// .sbtopts
// ```
// -Dkey1=sbtopts
// ```
//
// .jvmopts
// ```
// -Dkey1=jvmopts
// ```
//
// sbt "config/runMain com.example.config.part1.FromSbtopts"
//
// bloopはnullになる
// bloop run config -m com.example.config.part1.FromSbtopts
object FromSbtopts extends App {
  println("-" * 50)

  println(System.getProperty("key1"))
  // => sbtopts

  println("-" * 50)
}
