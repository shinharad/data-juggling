import squants._
import squants.time.TimeConversions._
import squants.information.InformationConversions._

val infor1: information.Information =
  1.megabytes

val info2: information.Information =
  1.mebibytes

val infoConversion1: information.Information =
  1.megabytes.in(information.Kilobytes)

val infoConversion2: information.Information =
  1.mebibytes.in(information.Kibibytes)

val value0 =
  infoConversion2.to(information.Mebibytes)

val value1: Double =
  infoConversion2.to(information.Mebibytes)

val value2: BigDecimal =
  infoConversion2.toMegabytes

val value3: BigDecimal =
  infoConversion2.value

val value4: BigDecimal =
  infoConversion2.toKibibytes

val string1: String =
  infoConversion2.toString

val string2: String =
  infoConversion2.toString(information.Kibibytes)

val string3: String =
  infoConversion2.toString(information.Mebibytes)

val valueAndString1: (Double, String) =
  infoConversion2.toTuple

val valueAndString2: (Double, String) =
  infoConversion2.toTuple(information.Kibibytes)

val valueAndString3: (Double, String) =
  infoConversion2.toTuple(information.Mebibytes)

val symbol1: String =
  infoConversion2.unit.symbol

val symbol2: String =
  infoConversion2.in(information.Mebibytes).unit.symbol

val rate: information.DataRate =
  1024.mebibytes / second