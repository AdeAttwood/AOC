namespace AOC.Cli;

using System.Text.RegularExpressions;

/// <summary>
/// Solution for day 3
///
/// See https://adventofcode.com/2024/day/3
/// </summary>
public class Day3 : Day, IDay
{
    private static readonly string MUL_PATTERN = @"mul\((\d{1,3}),(\d{1,3})\)";
    private static readonly string DO_DONT_MUL_PATTERN = @"don't\(\)|do\(\)|mul\((\d+),(\d+)\)";

    public string PartOne(string input)
    {
        var results = new List<int>();
        foreach (Match match in Regex.Matches(input, MUL_PATTERN, RegexOptions.IgnoreCase))
            results.Add(int.Parse(match.Groups[1].Value) * int.Parse(match.Groups[2].Value));

        return results.Sum().ToString();
    }

    public string PartTwo(string input)
    {
        var total = 0;
        var shouldMul = true;

        foreach (Match match in Regex.Matches(input, DO_DONT_MUL_PATTERN, RegexOptions.IgnoreCase))
        {
            if (match.Value == "do()")
            {
                shouldMul = true;
                continue;
            }

            if (match.Value == "don't()")
            {
                shouldMul = false;
                continue;
            }

            if (shouldMul)
            {
                total += int.Parse(match.Groups[1].Value) * int.Parse(match.Groups[2].Value);
            }
        }

        return total.ToString();
    }
}