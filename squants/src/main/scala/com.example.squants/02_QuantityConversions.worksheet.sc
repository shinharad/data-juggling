import squants._
import squants.energy.PowerConversions._
import squants.time.TimeConversions._

val power: Power = 2.0.megawatts
val days: time.Time = 5.days

// NG
// power + time

power + power
days + days

power * 2
days * 3

power * 2 + 10.megawatts
power.map(_ * 2 + 10)

days * 2 + 10.days
days.map(_ * 2 + 10)

power * 2 + (10 * 1000).kilowatts
days * 2 + (10 * 24).hours

power * 2 + 18.kilowatts
days * 2 + 18.hours

power == 2000.kilowatts
2.megawatts == 2000.kilowatts
2.megawatts == 2.kilowatts * 1000
1.megawatts == 1.kilowatts * 1000
1.megawatts == kilowatt * 1000

val powerRamp: PowerRamp = power / days

import squants.mass.MassConversions._
import squants.space.LengthConversions._

val force1: Force = 10.kilograms * (100.meters / (second * second))
val force2: Force = 10.kilograms * (100.meters / second.squared)

val mass: Mass = 10.kilograms
val frequency: time.Frequency = 60 / second
val massFlow1: MassFlow = mass * frequency
val massFlow2: MassFlow = frequency * mass
