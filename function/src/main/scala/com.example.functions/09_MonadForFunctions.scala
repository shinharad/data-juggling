package com.example.functions

import Library._

object `09_MonadForFunctions` extends App {
  println("-" * 100)

  def f(int: Int): String =
    int.toString

  def g(string: String): Option[Char] =
    string.headOption

  val ff: Int => String = f
  val gg: String => Option[Char] = g

  val a: Seq[Option[Char]] = 1 to 3 map f map gg tap println
  val b: Seq[Option[Char]] = 1 to 3 map ff -->> g tap println
  val c: Seq[Option[Char]] = 1 to 3 map (gg <<-- ff) tap println

  implicit def MonadForOption[A]: Monad[Option] =
    new Monad[Option] {
      override def pure[B](b: B): Option[B] = Some(b)
      override def map[B, C](fb: Option[B])(f: B => C): Option[C] = fb.map(f)
      override def flatten[B](ffb: Option[Option[B]]): Option[B] = ffb.flatten
    }

  val fKleisli: Int => Option[String] =
    // ff map the[Applicative[Option]].pure
    ff map (_.pure[Option])

  val fKleisliAndThenG: Int => /* through Option[String] */ Option[Char] =
    fKleisli >==> g

  println(fKleisliAndThenG(1337))

  val isEven: Int => Boolean =
    _ % 2 == 0

  val didPersonStartOnDayOfYear: Person => Boolean =
    isEven <<-- (_.startedOnDayOfYear)

  val renderedPerson: (Person, Boolean) => String = { (person, startedOnEvenDay) =>
    val didOrDidNotStart: String =
      if (startedOnEvenDay)
        "started"
      else
        "did not start"

    s"$person $didOrDidNotStart on even day"
  }

  List("Alpha", "Bravo", "Charlie")
    .zip(1 to 3)
    .map(
      Person
        .tupled
        .andThen(
          didPersonStartOnDayOfYear.flatMap(renderedPerson.curried.flipped)
        )
    )
    .foreach(println)

  println("-" * 100)

  val betterRenderedPerson: Boolean => Person => String = { startedOnEvenDay => person =>
    val didOrDidNotStart: String =
      if (startedOnEvenDay)
        "started"
      else
        "did not start"

    s"$person $didOrDidNotStart on even day"
  }

  List("Alpha", "Bravo", "Charlie")
    .zip(1 to 3)
    .map(
      Person
        .tupled
        .andThen(
          didPersonStartOnDayOfYear.flatMap(betterRenderedPerson)
        )
    )
    .foreach(println)

  println("-" * 100)

  List("Alpha", "Bravo", "Charlie")
    .zip(1 to 3)
    .map(Person.tupled)
    .map(didPersonStartOnDayOfYear.flatMap(betterRenderedPerson))
    .foreach(println)

  println("-" * 100)

  List("Alpha", "Bravo", "Charlie")
    .zip(1 to 3)
    .map(Person.tupled)
    .map(p => betterRenderedPerson(didPersonStartOnDayOfYear(p))(p))
    .foreach(println)

  println("-" * 100)

  List("Alpha", "Bravo", "Charlie")
    .zip(1 to 3)
    .map(Person.tupled)
    .map(p => betterRenderedPerson(didPersonStartOnDayOfYear(p))(p))
    .foreach(println)

  println("-" * 100)
}
