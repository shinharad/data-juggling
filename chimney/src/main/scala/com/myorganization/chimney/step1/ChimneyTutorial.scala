package com.example.chimney.step1

// 同一のフィールドを持つクラス同士
object ChimneyTutorial01 extends App {
  import io.scalaland.chimney.dsl._

  println("─" * 100)

  final case class Person(name: String, age: Int)
  final case class Customer(name: String, age: Int)

  val person: Person =
    Person(
      name = "Bob",
      age = 27
    )

  val customer: Customer =
    person
      .into[Customer]
      .transform

  println(person)
  println(customer)

  // 簡潔にこう書ける
  val customer2: Customer =
    person.transformInto[Customer]

  println(customer2)

  println("─" * 100)
}

// 元のクラスには存在しないフィールドが対象に存在する場合
object ChimneyTutorial02 extends App {
  import io.scalaland.chimney.dsl._
  import java.util.UUID

  println("─" * 100)

  final case class Person(name: String, age: Int)
  final case class Customer(
      id: UUID,
      name: String,
      age: Int
    )

  val person: Person =
    Person(
      name = "Bob",
      age = 27
    )

  val customer: Customer =
    person
      .into[Customer]
      // 足らないフィールドを設定する
      .withFieldConst(_.id, UUID.randomUUID())
      .transform

  println(person)
  println(customer)

  println("─" * 100)
}

// 対象のフィールド名が違う場合
object ChimneyTutorial03 extends App {
  import io.scalaland.chimney.dsl._
  import java.util.UUID

  println("─" * 100)

  final case class Person(name: String, age: Int)
  final case class Customer(
      id: UUID,
      fullName: String,
      age: Int
    )

  val person: Person =
    Person(
      name = "Bob",
      age = 27
    )

  val customer: Customer =
    person
      .into[Customer]
      .withFieldConst(_.id, UUID.randomUUID())
      // フィールド名が違う場合は、これのどれかで
      .withFieldRenamed(_.name, _.fullName)
      .withFieldComputed(_.fullName, _.name)
      .withFieldConst(_.fullName, person.name)
      .transform

  println(person)
  println(customer)

  println("─" * 100)
}

// 対象のフィールドに対して計算が必要な場合
object ChimneyTutorial04 extends App {
  import io.scalaland.chimney.dsl._
  import java.time.LocalDateTime
  import java.util.UUID

  println("─" * 100)

  final case class Person(name: String, age: Int)
  final case class Customer(
      id: UUID,
      fullName: String,
      birthYear: Int
    )

  val person: Person =
    Person(
      name = "Bob",
      age = 27
    )

  val customer: Customer =
    person
      .into[Customer]
      .withFieldConst(_.id, UUID.randomUUID())
      .withFieldRenamed(_.name, _.fullName)
      // 計算して設定する
      // .withFieldComputed(_.birthYear, p => LocalDateTime.now.getYear - p.age)
      .withFieldConst(_.birthYear, LocalDateTime.now.getYear - person.age)
      .transform

  println(person)
  println(customer)

  println("─" * 100)
}

// 対象がValue Classだった場合
object ChimneyTutorial05 extends App {
  import io.scalaland.chimney.dsl._
  import java.time.LocalDateTime
  import java.util.UUID

  println("─" * 100)

  final case class Name(value: String) extends AnyVal
  final case class Person(name: String, age: Int)
  final case class Customer(
      id: UUID,
      fullName: Name,
      birthYear: Int
    )

  val person: Person =
    Person(
      name = "Bob",
      age = 27
    )

  val customer: Customer =
    person
      .into[Customer]
      .withFieldConst(_.id, UUID.randomUUID())
      // 対象がValue Classだった場合
      .withFieldRenamed(_.name, _.fullName) // 自動的にValue Classに設定できる
      .withFieldComputed(_.fullName, p => Name(p.name)) // Nameに変換が必要
      .withFieldConst(_.fullName, Name(person.name)) // Nameに変換が必要
      .withFieldConst(_.birthYear, LocalDateTime.now.getYear - person.age)
      .transform

  println(person)
  println(customer)

  println("─" * 100)
}

// 対象がValue Classでも同一の名前の場合
object ChimneyTutorial06 extends App {
  import io.scalaland.chimney.dsl._
  import java.time.LocalDateTime
  import java.util.UUID

  println("─" * 100)

  final case class Name(value: String) extends AnyVal
  final case class Person(name: String, age: Int)
  final case class Customer(
      id: UUID,
      name: Name,
      birthYear: Int
    )

  val person: Person =
    Person(
      name = "Bob",
      age = 27
    )

  val customer: Customer =
    person
      .into[Customer]
      .withFieldConst(_.id, UUID.randomUUID())
      // 対象が同一の名前だったら自動的にValue Classに設定できる
      // .withFieldRenamed(_.name, _.fullName)
      .withFieldConst(_.birthYear, LocalDateTime.now.getYear - person.age)
      .transform

  println(person)
  println(customer)

  println("─" * 100)
}

// Option型のデフォルトをNoneに設定する
object ChimneyTutorial07 extends App {
  import io.scalaland.chimney.dsl._

  println("─" * 100)

  final case class Person(name: String, age: Int)
  final case class Customer(
      name: String,
      age: Int,
      email: Option[String]
    )

  val person: Person =
    Person(
      name = "Bob",
      age = 27
    )

  val customer: Customer =
    person
      .into[Customer]
      .enableOptionDefaultsToNone
      .transform

  println(person)
  println(customer)

  println("─" * 100)
}

// patchをあてる
object ChimneyTutorial08 extends App {
  import io.scalaland.chimney.dsl._

  println("─" * 100)

  final case class Person(
      name: String,
      age: Int,
      email: String
    )
  final case class Customer(
      name: String,
      age: Int,
      email: String
    )

  final case class Patch(name: String, age: Int)

  val person: Person =
    Person(
      name = "Bob",
      age = 27,
      email = "a@b.com"
    )

  val patch: Patch =
    Patch("Frank", 45)

  val patchedPerson: Person =
    person.patchWith(patch)

  val customer: Customer =
    person
      .into[Customer]
      .transform

  val patchedCustomer: Customer =
    customer.patchWith(patch)

  println(person)
  println(customer)

  println("─" * 100)

  println(patchedPerson)
  println(patchedCustomer)

  println("─" * 100)
}

// ADT
object ChimneyTutorial09 extends App {
  import io.scalaland.chimney.dsl._

  println("─" * 100)

  sealed abstract class Color extends Product with Serializable
  object Color {
    case object Red extends Color
    case object Green extends Color
    case object Blue extends Color
  }

  sealed abstract class Channel extends Product with Serializable
  object Channel {
    case object Alpha extends Channel
    case object Red extends Channel
    case object Green extends Channel
    case object Blue extends Channel
  }

  val color: Color =
    Color.Red

  val channel: Channel =
    color.into[Channel].transform

  // Colorに戻す際、ColorにはAlphaが存在しないので代替の値を設定
  val backToColor: Color =
    (Channel.Alpha: Channel)
      .into[Color]
      .withCoproductInstance { (_: Channel.Alpha.type) =>
        Color.Blue
      }
      .transform

  println(color)
  println(channel)
  println(backToColor)

  println("─" * 100)
}

// Transformerで事前に変換ルールを定義しておく
object ChimneyTutorial10 extends App {
  import io.scalaland.chimney._
  import io.scalaland.chimney.dsl._

  println("─" * 100)

  final case class Person(name: String, age: Int)
  final case class Customer(name: String, age: Int)

  implicit val personToCustomer: Transformer[Person, Customer] =
    person => Customer(person.name + " line 42", person.age + 10)

  final case class Patch(name: String, age: Int)

  val person: Person =
    Person(
      name = "Bob",
      age = 27
    )

  val patch: Patch =
    Patch("Frank", 45)

  val patchedPerson: Person =
    person.patchWith(patch)

  val customer: Customer =
    person
      .into[Customer]
      .transform

  val patchedCustomer: Customer =
    customer.patchWith(patch)

  println(person)
  println(customer)

  println("─" * 100)

  println(patchedPerson)
  println(patchedCustomer)

  println("─" * 100)
}

// patchWithのルールを定義
object ChimneyTutorial11 extends App {
  import io.scalaland.chimney._
  import io.scalaland.chimney.dsl._

  println("─" * 100)

  final case class Person(name: String, age: Int)
  final case class Customer(name: String, age: Int)

  implicit val customPatcher: Patcher[Customer, Int] =
    (customer, newAge) => customer.copy(age = newAge)

  final case class Patch(name: String, age: Int)

  val person: Person =
    Person(
      name = "Bob",
      age = 27
    )

  val patch: Patch =
    Patch("Frank", 45)

  val patchedPerson: Person =
    person.patchWith(patch)

  val customer: Customer =
    person
      .into[Customer]
      .transform

  val patchedCustomer: Customer =
    customer.patchWith(patch)

  println(person)
  println(customer)

  println("─" * 100)

  println(patchedPerson)
  println(patchedCustomer)

  println("─" * 100)

  println(customer.patchWith(25))

  println("─" * 100)
}
