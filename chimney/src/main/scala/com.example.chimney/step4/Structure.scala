package com.example.chimney.step4

// import io.scalaland.chimney.dsl._

final case class Street(name: Street.Name, number: Street.Number)
case object Street {
  final case class Name(value: String) extends AnyVal {
    final override def productPrefix: String =
      s"${Street}.${Name}"
  }

  final case class Number(value: String) extends AnyVal {
    final override def productPrefix: String =
      s"${Street}.${Number}"
  }

  final case class Raw(name: String, number: String) {
    final override def productPrefix: String =
      s"${Street}.${Raw}"
  }
}

final case class City(name: City.Name, zipCode: City.ZipCode)
case object City {
  final case class Name(value: String) extends AnyVal {
    final override def productPrefix: String =
      s"${City}.${Name}"
  }

  final case class ZipCode(value: String) extends AnyVal {
    final override def productPrefix: String =
      s"${City}.${ZipCode}"
  }

  final case class Raw(name: String, zipCode: String) {
    final override def productPrefix: String =
      s"${City}.${Raw}"
  }
}

final case class Address(city: City, street: Street)
case object Address {
  final case class Raw(city: City.Raw, street: Street.Raw) {
    final override def productPrefix: String =
      s"${Address}.${Raw}"
  }
}

final case class Phone(value: String) extends AnyVal

final case class Person(
    name: Person.Name,
    age: Person.Age,
    heightInCm: Person.HeightInCm,
    phone: Phone,
    address: Address
  )
case object Person {
  final case class Name(value: String) extends AnyVal {
    final override def productPrefix: String =
      s"${Person}.${Name}"
  }

  final case class Age(value: Int) extends AnyVal {
    final override def productPrefix: String =
      s"${Person}.${Age}"
  }

  final case class HeightInCm(value: Int) extends AnyVal {
    final override def productPrefix: String =
      s"${Person}.${HeightInCm}"
  }

  final case class Raw(
      name: String,
      age: String,
      heightInCm: String,
      phone: String,
      address: Address.Raw
    ) {
    final override def productPrefix: String =
      s"${Person}.${Raw}"
  }
}
