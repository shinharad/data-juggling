import squants._
import squants.time.TimeConversions._

final implicit class TimeOps(private val self: squants.time.Time) {
  import scala.concurrent.duration._

  final def toConcurrentDuration: Duration =
    self.toNanoseconds.nanos
}

val t = 29999.milliseconds
val d = t.toConcurrentDuration

hour.toConcurrentDuration
2.hours.toConcurrentDuration
24.hours.toConcurrentDuration
