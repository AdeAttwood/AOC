namespace AOC.Cli.Tests;

public class Day3Test
{
    private readonly Day3 _day = new Day3();

    [Fact]
    public void TestDay3PartOne()
    {
        Assert.Equal("161", _day.PartOne(_day.TestInput(3, 1)));
        Assert.Equal("179571322", _day.PartOne(_day.Input(3, 1)));
    }

    [Fact]
    public void TestDay3PartTwo()
    {
        Assert.Equal("48", _day.PartTwo(_day.TestInput(3, 2)));
        Assert.NotEqual("101161704", _day.PartTwo(_day.Input(3, 1)));
        Assert.Equal("103811193", _day.PartTwo(_day.Input(3, 1)));
    }
}