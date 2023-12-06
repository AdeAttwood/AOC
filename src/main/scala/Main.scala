@main def main(dayNumber: Int): Unit = {
  var day = dayNumber match
    case 1 => Day1
    case 2 => Day2
    case 3 => Day3
    case 4 => Day4
    case 5 => Day5

  day.run()
}
