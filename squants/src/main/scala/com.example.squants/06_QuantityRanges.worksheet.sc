import squants._
import squants.time.TimeConversions._

QuantityRange(10.minutes, 20.minutes)
10.minutes to 20.minutes
10 to 20

15.minutes.plusOrMinus(5.minutes)
15.minutes +- 5.minutes
15.minutes +- 30.minutes

(10.minutes to 20.minutes).toSeq
(15.minutes +- 5.minutes).toSeq

Seq(1, 2, 3, 4).sum
Seq(1, 2, 3, 4).product

(10.minutes to 20.minutes).toSeq.sum
// (10.minutes to 20.minutes).toSeq.product

Seq(30.minutes, 0.5.hours, 2.days).sum
Seq(30.minutes, 0.5.hours, 2.days).sum.in(time.Days)
Seq(30.minutes, 0.5.hours, 2.days, 23.hours).sum
Seq(30.minutes, 0.5.hours, 2.days, 23.hours).sum.in(time.Days)
Seq(30.minutes, 0.5.hours, 2.days + 23.hours).sum.in(time.Days)
