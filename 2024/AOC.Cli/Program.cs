namespace AOC.Cli;

class Cli
{
    private static readonly List<IDay> _days = new List<IDay> { new Day1(), };

    public static int Main(string[] args)
    {
        for (var i = 0; i < _days.Count; i++)
        {
            var day = _days[i];
            var day1Result = day.PartOne(day.Input(1, 1));
            var day2Result = day.PartTwo(day.Input(1, 1));

            Console.WriteLine($"Day {i + 1} Part 1: {day1Result}");
            Console.WriteLine($"Day {i + 1} Part 2: {day2Result}");
        }

        return 0;
    }
}