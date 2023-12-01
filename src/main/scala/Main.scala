@main def main(dayNumber: Int): Unit = {
  var day = dayNumber match
    case 1 => Day1

  day.run()
}
