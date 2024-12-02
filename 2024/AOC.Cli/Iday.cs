namespace AOC.Cli;

public interface IDay
{
    public string PartOne(string input);
    public string PartTwo(string input);

    public string Input(int number, int part);
    public string TestInput(int number, int part);
}

public abstract class Day
{
    public string Input(int number, int part)
    {
        return this.RawInput(number, part, false).Trim();
    }

    public string TestInput(int number, int part)
    {
        return this.RawInput(number, part, true).Trim();
    }

    private string RawInput(int number, int part, bool test)
    {
        var baseDir = Directory.GetParent(AppContext.BaseDirectory)?.Parent?.Parent?.Parent?.Parent;
        if (baseDir == null)
            throw new Exception("Could not find the base directory");

        var dataDir = Path.Combine(baseDir.ToString(), "data");
        return File.ReadAllText(
            Path.Combine(dataDir, $"day-{number}-step-{part}{(test ? "-test" : "")}.txt")
        );
    }
}