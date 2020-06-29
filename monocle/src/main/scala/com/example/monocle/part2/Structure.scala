package com.example.monocle.part2

import io.scalaland.chimney.dsl._

import monocle.macros._

@Lenses final case class Street(name: Street.Name, number: Street.Number)
case object Street {
  @Lenses final case class Name(value: String) extends AnyVal {
    final override def productPrefix: String =
      s"${Street}.${Name}"
  }

  @Lenses final case class Number(value: String) extends AnyVal {
    final override def productPrefix: String =
      s"${Street}.${Number}"
  }

  @Lenses final case class Raw(name: String, number: String) {
    final override def productPrefix: String =
      s"${Street}.${Raw}"
  }
}

@Lenses final case class City(name: City.Name, zipCode: City.ZipCode)
case object City {
  @Lenses final case class Name(value: String) extends AnyVal {
    final override def productPrefix: String =
      s"${City}.${Name}"
  }

  @Lenses final case class ZipCode(value: String) extends AnyVal {
    final override def productPrefix: String =
      s"${City}.${ZipCode}"
  }

  @Lenses final case class Raw(name: String, zipCode: String) {
    final override def productPrefix: String =
      s"${City}.${Raw}"
  }
}

@Lenses final case class Address(city: City, street: Street)
case object Address {
  @Lenses final case class Raw(city: City.Raw, street: Street.Raw) {
    final override def productPrefix: String =
      s"${Address}.${Raw}"
  }
}

@Lenses final case class Phone(value: String) extends AnyVal

@Lenses final case class Person(
    name: Person.Name,
    age: Person.Age,
    heightInCm: Person.HeightInCm,
    phone: Phone,
    address: Address
  )
case object Person {
  @Lenses final case class Name(value: String) extends AnyVal {
    final override def productPrefix: String =
      s"${Person}.${Name}"
  }

  @Lenses final case class Age(value: Int) extends AnyVal {
    final override def productPrefix: String =
      "Person.Age"
      // s"${Person}.${Age}"
  }
  case object Age extends (Int => Age)

  @Lenses final case class HeightInCm(value: Int) extends AnyVal {
    final override def productPrefix: String =
      "Person.HeightInCm"
      // s"${Person}.${HeightInCm}"
  }
  case object HeightInCm extends (Int => HeightInCm)

  case object Raw {
    @Lenses final case class Nested(
        name: String,
        age: String,
        heightInCm: String,
        phone: String,
        address: Address.Raw
      ) {
      final override def productPrefix: String =
        s"${Person}.${Raw}.${Nested}"

      lazy val toFlat: Flat = {
        import address._

        this
          .into[Flat]
          .withFieldConst(_.cityName, city.name)
          .withFieldConst(_.cityZipCode, city.zipCode)
          .withFieldConst(_.streetName, street.name)
          .withFieldConst(_.streetNumber, street.number)
          .transform

      }
    }

    @Lenses final case class Flat(
        name: String,
        age: String,
        heightInCm: String,
        phone: String,
        cityName: String,
        cityZipCode: String,
        streetName: String,
        streetNumber: String
      ) {
      final override def productPrefix: String =
        s"${Person}.${Raw}.${Flat}"

      lazy val toNested: Nested =
        this
          .into[Nested]
          .withFieldConst(
            _.address,
            Address.Raw(
              City.Raw(cityName, cityZipCode),
              Street.Raw(streetName, streetNumber)
            )
          )
          .transform
    }
  }
}
