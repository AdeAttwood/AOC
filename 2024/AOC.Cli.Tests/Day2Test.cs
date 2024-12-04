namespace AOC.Cli.Tests;

public class Day2Test
{
    private readonly Day2 _day = new Day2();

    [Fact]
    public void TestIsSafe()
    {
        Assert.True(_day.IsSafe(new[] { 7, 6, 4, 2, 1 }));
    }

    [Fact]
    public void TestDay2PartOne()
    {
        Assert.Equal("2", _day.PartOne(_day.TestInput(2, 1)));
        Assert.Equal("383", _day.PartOne(_day.Input(2, 1)));
    }

    [Fact]
    public void TestDay2PartTwo()
    {
        Assert.Equal("4", _day.PartTwo(_day.TestInput(2, 1)));
        Assert.Equal("436", _day.PartTwo(_day.Input(2, 1)));
    }
}