package com.example.refined

trait TraceSupport {
  def green(input: Any): String =
    Console.GREEN + input + Console.RESET

  def red(input: Any): String =
    Console.RED + input + Console.RESET

}

// requireだとruntime error
object `01_ConstrainingTypesWithoutRefined1` extends App with TraceSupport {
  println("-" * 100)

  final case class PositiveInt(value: Int) {
    require(
      requirement = value > 0,
      message = red(s"$value was supposed to be > 0 but it wasn't :(")
    )
  }

  println(PositiveInt(5))
  // println(PositiveInt(-5))

  final case class Greater10(value: Int) {
    require(
      requirement = value > 10,
      message = red(s"$value was supposed to be > 10 but it wasn't :(")
    )
  }

  val positive: PositiveInt = PositiveInt(Greater10(20).value)
  // val positive: PositiveInt = PositiveInt(Greater10(-20).value)

  println("-" * 100)
}

// requireがあるのでvalue classにできない
object `01_ConstrainingTypesWithoutRefined2` extends App with TraceSupport {

  // final case class PositiveInt(value: Int) extends AnyVal {
  //   require(
  //     requirement = value > 0,
  //     message = red(s"$value was supposed to be > 0 but it wasn't :(")
  //   )
  // }

}

// value classでコンストラクタをprivateにしてもcopyで回避できてしまう
object `01_ConstrainingTypesWithoutRefined3` extends App with TraceSupport {
  println("-" * 100)

  final case class PositiveInt private (value: Int) extends AnyVal

  object PositiveInt extends (Int => Either[String, PositiveInt]) {
    final override def apply(value: Int): Either[String, PositiveInt] =
      if (value > 0)
        Right(new PositiveInt(value))
      else
        Left(red(s"$value was supposed to be > 0 but it wasn't :("))
  }

  println(PositiveInt(5))
  println(PositiveInt(-5))
  println(PositiveInt(5).map(_.copy(value = -2)))

  println("-" * 100)
}

// copyをoverrideすることで一応回避はできる
object `01_ConstrainingTypesWithoutRefined4` extends App with TraceSupport {
  println("-" * 100)

  final case class PositiveInt private (value: Int) extends AnyVal {
    def copy(value: Int = value): Either[String, PositiveInt] =
      PositiveInt(value)
  }

  object PositiveInt extends (Int => Either[String, PositiveInt]) {
    final override def apply(value: Int): Either[String, PositiveInt] =
      if (value > 0)
        Right(new PositiveInt(value))
      else
        Left(red(s"$value was supposed to be > 0 but it wasn't :("))
  }

  println(PositiveInt(5))
  println(PositiveInt(-5))
  println(PositiveInt(5).flatMap(_.copy(value = -2)))

  println("-" * 100)
}
