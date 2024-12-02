namespace AOC.Cli.Tests;

public class Day1Test
{
    private readonly Day1 _day = new Day1();

    [Fact]
    public void Part_One_With_Test_Data()
    {
        var result = _day.PartOne(_day.TestInput(1, 1));
        Assert.Equal("11", result);
    }

    [Fact]
    public void Part_One_With_Real_Data()
    {
        var result = _day.PartOne(_day.Input(1, 1));
        Assert.Equal("2066446", result);
    }

    [Fact]
    public void Part_Two_With_Test()
    {
        var result = _day.PartTwo(_day.TestInput(1, 1));
        Assert.Equal("31", result);
    }

    [Fact]
    public void Part_Two_With_Real_Data()
    {
        var result = _day.PartTwo(_day.Input(1, 1));
        Assert.Equal("24931009", result);
    }
}