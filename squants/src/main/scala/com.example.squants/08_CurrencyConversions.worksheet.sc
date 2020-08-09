import squants._
import squants.energy.EnergyConversions._
import squants.energy.PowerConversions._
import squants.time.TimeConversions._
import squants.mass.MassConversions._
import squants.market.MoneyConversions._
import squants.space.LengthConversions._
import squants.space.VolumeConversions._

val r1: market.CurrencyExchangeRate =
  market.USD / 0.91.euros

val r2: market.CurrencyExchangeRate =
  100.dollars -> 91.euros

val r3: market.CurrencyExchangeRate =
  100.dollars toThe 91.euros

// val money1 =
//   100.dollars / 91.euros

r2.convert(10.dollars)
r2 * 10.dollars

r2.convert(9.1.euros)
r2 * 9.1.euros

10.dollars * r2
9.1.dollars * r2

val exchangeRates =
  List(
    market.USD / 0.91.euros,
    market.USD / market.BRL(4.24),
    market.BRL / 0.35.AUD
  )

implicit val moneyContext =
  market
    .defaultMoneyContext
    .withExchangeRates(exchangeRates)
    .copy(
      allowIndirectConversions = true
    )

val money1: BigDecimal =
  100.dollars / 91.euros

// val money2 =
//   100.dollars * 91.euros

val money3: Money =
  100.dollars + 91.euros

val money4: Money =
  100.dollars - 91.euros

// exchangeRates.map(_ * 10.dollars)

// 10.dollars in market.AUD

1.money
1.money(moneyContext)

1.dollars + 0.91.euros
1.dollars + 0.91.euros in market.USD
1.dollars + 0.91.euros in market.EUR

1.dollars + 0.91.euros to market.USD
1.dollars + 0.91.euros to market.EUR

1.dollars + 0.91.euros toString market.USD
1.dollars + 0.91.euros toString market.EUR

10.123456789.dollars.toString(market.USD)
10.123456789.dollars.toFormattedString(market.USD)
10.123456789.dollars.toFormattedString(market.EUR)
println(10.123456789.dollars.toFormattedString(market.EUR))
10.123456789.dollars.toFormattedString(market.BRL)

implicit val moneyNum = new MoneyNumeric()

List(2.dollars, 4.dollars).sum
List(2.dollars, 5.euros).sum
List(2.dollars, 5.euros).sum.toFormattedString(market.USD)

// List(2.dollars, 4.dollars).product

val energyPrice: Price[Energy] = 45.25.money / megawattHour

val energyUsage: Energy = 345.kilowatts * 5.4.hours

val energyCost: Money = energyPrice * energyUsage

val dodgeViper: Acceleration = 60.miles / hour / 3.9.seconds

val speedAfter5Seconds: Velocity = dodgeViper * 5.seconds

val timeTo100MPH: Time = 100.miles / hour / dodgeViper

val density: Density = 1200.kilograms / cubicMeter

val volFlowRate: VolumeFlow = 10.gallons / minute

val flowTime: Time = 30.minutes

val totalMassFlow: Mass = volFlowRate * flowTime * density
