namespace AOC.Cli;

/// <summary>
/// Solution for day 2
///
/// See https://adventofcode.com/2024/day/2
/// </summary>
public class Day2 : Day, IDay
{
    private IEnumerable<(int, int)> Enumerate(int[] input)
    {
        for (var i = 0; i < input.Length - 1; i++)
        {
            var left = input[i];
            var right = input[i + 1];

            yield return (left, right);
        }
    }

    private bool IsDecreasing(int left, int right)
    {
        var diff = left - right;
        return diff >= -3 && diff <= -1;
    }

    private bool IsIncreasing(int left, int right)
    {
        var diff = left - right;
        return diff <= 3 && diff >= 1;
    }

    public bool IsSafe(int[] report)
    {
        return this.Enumerate(report).All(x => this.IsIncreasing(x.Item1, x.Item2))
            || this.Enumerate(report).All(x => this.IsDecreasing(x.Item1, x.Item2));
    }

    public bool IsOkForPartTwo(int[] report)
    {
        if (IsSafe(report))
            return true;

        for (var i = 0; i < report.Length; i++)
        {
            var copy = new List<int>(report);
            copy.RemoveAt(i);
            if (IsSafe(copy.ToArray()))
                return true;
        }

        return false;
    }

    public string PartOne(string input)
    {
        var count = 0;
        var reports = input.Split("\n");
        foreach (var rawReport in reports)
        {
            var report = rawReport.Split(" ").Select(int.Parse).ToArray();
            if (IsSafe(report))
                count++;
        }

        return count.ToString();
    }

    public string PartTwo(string input)
    {
        var count = 0;
        var reports = input.Split("\n");
        foreach (var rawReport in reports)
        {
            var report = rawReport.Split(" ").Select(int.Parse).ToArray();
            if (IsOkForPartTwo(report))
                count++;
        }

        return count.ToString();
    }
}