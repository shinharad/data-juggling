import squants._
import squants.energy.PowerConversions._
import squants.market.MoneyConversions._
import squants.space.VolumeConversions._
import squants.time.TimeConversions._

market.Money(1, market.USD)
1.USD
1.dollars
100.cents
val CuritisJackson = 50.cents

1.dollars / 1
10.dollars / 5
10.dollars / 3
10.dollars % 3
10.dollars /% 3

val price1 = 10.dollars / Each(1)
val price2: Price[Dimensionless] = 10.dollars / Each(1)
val price3: Price[Volume] = 10.dollars / space.UsGallons(1)
val price4: Price[Volume] = 10.dollars / gallon
val price5: Price[Volume] = 10.dollars / 2.gallons

val price6: Price[Power] = 10.dollars / 2.megawatts
val price7: Price[PowerRamp] = 10.dollars / (2.megawatts / hour)

10.dollars.toString
10.dollars.toFormattedString
