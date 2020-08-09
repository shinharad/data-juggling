import squants._
import squants.mass.MassConversions._

implicit val tolerance: Mass = 10.grams

990.grams approx 1.kilograms
991.grams approx 1.kilograms

991.grams =~ kilogram
991.grams ~= kilogram
991.grams ≈ kilogram

