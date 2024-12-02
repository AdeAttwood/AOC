namespace AOC.Cli;

/// <summary>
/// Solution for day 1
///
/// See https://adventofcode.com/2024/day/1
/// </summary>
public class Day1 : Day, IDay
{
    public string PartOne(string input)
    {
        var left = input.Split("\n").Select(x => Int32.Parse(x.Split(" ").First())).Order();
        var right = input.Split("\n").Select(x => Int32.Parse(x.Split(" ").Last())).Order();

        return left.Zip(right, (l, r) => Math.Max(l, r) - Math.Min(l, r)).Sum().ToString();
    }

    public string PartTwo(string input)
    {
        var left = input.Split("\n").Select(x => Int32.Parse(x.Split(" ").First()));
        var right = input.Split("\n").Select(x => Int32.Parse(x.Split(" ").Last()));

        return left.Select(x => x * right.Where(y => x == y).Count()).Sum().ToString();
    }
}