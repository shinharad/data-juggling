import squants._
import squants.energy.{ Megawatts, Power }

val power1: scala.util.Try[Power] = Power("2.0 MW")
val power2: Power = Megawatts(2.0)
val power3: Power = Megawatts(BigDecimal(2.0))
val power4: Power = Megawatts(BigDecimal("2.0"))

import squants.energy.PowerConversions._

val power5: Power = 2.0.mW
val power6: Power = 2.0.MW
val power7: Power = 2.0.megawatts