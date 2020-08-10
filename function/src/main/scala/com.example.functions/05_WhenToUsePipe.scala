package com.example.functions

import Library._

object `05_WhenToUsePipe` extends App {
  println("-" * 100)

  val isEven: Int => Boolean =
    _ % 2 == 0

  val didPersonStartOnDayOfYear: Person => Boolean =
    // isEven(_.startedOnDayOfYear) // compile error
    // p => isEven(p.startedOnDayOfYear)
    // _.startedOnDayOfYear.pipe(isEven)
    // _.startedOnDayOfYear --> isEven
    // (_.startedOnDayOfYear).andThen(isEven) // compile error
    // isEven.compose(_.startedOnDayOfYear)
    isEven <-- (_.startedOnDayOfYear)

  didPersonStartOnDayOfYear(Person("Alice", 1)) --> println
  didPersonStartOnDayOfYear(Person("Bob", 2)) --> println

  val aliceWithStartedOnDayOfYear: Int => Person =
    // Person("Alice", _)
    Person curried "Alice"
  
  aliceWithStartedOnDayOfYear(1) --> println
  aliceWithStartedOnDayOfYear(2) --> println

  val createPersonStepByStep: String => Int => Person =
    Person.curried

  createPersonStepByStep("Alice")(1) --> println
  createPersonStepByStep("Bob")(2) --> println

  println("-" * 100)
}
