package com.example.cats.part7

import cats._
import cats.data._
import cats.implicits._

object `10_Nested_ApplicativeTransformer` extends App {
  println("-" * 100)

  final case class Report(days: Int, earned: Int)

  println(
    (
      Report(10, 100).validNec[String],
      Report(20, 200).validNec[String]
    ).mapN { (firstReport, secondReport) =>
      Report(
        days = firstReport.days + secondReport.days,
        earned = firstReport.earned + secondReport.earned
      )
    }
  )
  // Valid(Report(30,300))

  import scala.concurrent._
  import scala.concurrent.duration._
  implicit val ec = scala.concurrent.ExecutionContext.global

  def awaitResult[A](future: Future[A]): A =
    Await.result(future, 5.seconds)

  println(
    awaitResult {
      (
        Future(Report(10, 100).validNec[String]),
        Future(Report(20, 200).validNec[String])
      ).mapN { (firstValidated, secondValidated) =>
        (
          firstValidated,
          secondValidated
        ).mapN { (firstReport, secondReport) =>
          Report(
            days = firstReport.days + secondReport.days,
            earned = firstReport.earned + secondReport.earned
          )
        }
      }
    }
  )

  // Nestedを使う
  println(
    awaitResult {
      (
        Nested(Future(Report(10, 100).validNec[String])),
        Nested(Future(Report(20, 200).validNec[String]))
      ).mapN { (firstReport, secondReport) =>
        Report(
          days = firstReport.days + secondReport.days,
          earned = firstReport.earned + secondReport.earned
        )
      }.value
    }
  )

  println(
    awaitResult {
      (
        Future(Report(10, 100).validNec[String]).nested,
        Future(Report(20, 200).validNec[String]).nested
      ).mapN { (firstReport, secondReport) =>
        Report(
          days = firstReport.days + secondReport.days,
          earned = firstReport.earned + secondReport.earned
        )
      }.value
    }
  )

  println(
    awaitResult {
      (
        for {
          firstReport <- EitherT(Future(Report(10, 100).rightNec[String]))
          secondReport <- EitherT(Future(Report(20, 200).rightNec[String]))
        } yield Report(
          days = firstReport.days + secondReport.days,
          earned = firstReport.earned + secondReport.earned
        )
      ).value
    }
  )
  // Right(Report(30,300))

  println(
    awaitResult {
      (
        for {
          firstReport <- Future(Report(10, 100).rightNec[String])
          secondReport <- Future(Report(20, 200).rightNec[String])
        } yield (firstReport, secondReport).mapN { (firstReport, secondReport) =>
          Report(
            days = firstReport.days + secondReport.days,
            earned = firstReport.earned + secondReport.earned
          )
        }
      )
    }
  )

  println(
    awaitResult {
      (
        for {
          firstReport <- Future("error 1".invalidNec[Report])
          secondReport <- Future("error 2".invalidNec[Report])
        } yield (firstReport, secondReport).mapN { (firstReport, secondReport) =>
          Report(
            days = firstReport.days + secondReport.days,
            earned = firstReport.earned + secondReport.earned
          )
        }
      )
    }
  )
  // Invalid(Chain(error 1, error 2))

  println(
    awaitResult {
      (
        for {
          firstReport <- Future("error 1".leftNec[Report])
          secondReport <- Future("error 2".leftNec[Report])
        } yield (firstReport, secondReport).mapN { (firstReport, secondReport) =>
          Report(
            days = firstReport.days + secondReport.days,
            earned = firstReport.earned + secondReport.earned
          )
        }
      )
    }
  )
  // Left(Chain(error 1))

  println("-" * 100)
}
