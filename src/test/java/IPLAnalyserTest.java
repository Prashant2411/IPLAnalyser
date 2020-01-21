import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class IPLAnalyserTest {
    public String MOST_RUNS_CSV = "/home/admin1/IdeaProjects/IPLAnalyser/src/test/resources/Batting.csv";
    public String INCORRECT_FILE = "/home/admin1/IdeaProjects/IPL_Analyser/src/test/resources/MostRuns";
    public String INCORRECT_FILE_FORMAT = "/home/admin1/IdeaProjects/IPLAnalyser/src/test/resources/InvalidHeader.csv";

    public String BOWLING_CSV = "/home/admin1/IdeaProjects/IPLAnalyser/src/test/resources/Bowling.csv";

    //Batting

    @Test
    public void givenMostRunCSV_shouldReturnCorrectValues() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            int size = iplAnalyser.loadBattingData(MOST_RUNS_CSV);
            Assert.assertEquals(100, size);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLCSV_shouldReturnTopBattingAverages() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(ComparatorToSort.Sorting_Fields.AVERAGE, IPLAnalyser.BatOrBowl.BATTING);
            int size = iplAnalyser.loadBattingData(MOST_RUNS_CSV);
            List<CricketDataDAO> sortedList = iplAnalyser.getSorted();
            Assert.assertEquals(83.2, sortedList.get(0).avg, 0);
            Assert.assertEquals(0.0, sortedList.get(99).avg, 0);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenIPLCSV_shouldReturnTopBattingStrikeRate() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(ComparatorToSort.Sorting_Fields.STRIKE_RATE, IPLAnalyser.BatOrBowl.BATTING);
            int size = iplAnalyser.loadBattingData(MOST_RUNS_CSV);
            List<CricketDataDAO> sortedList = iplAnalyser.getSorted();
            Assert.assertEquals(333.33,sortedList.get(0).strikeRate,0);
            Assert.assertEquals(63.15,sortedList.get(99).strikeRate,0);
        } catch (IPLAnalyserException e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLCSV_shouldReturnMOst6sAnd4s() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(ComparatorToSort.Sorting_Fields.COMPARE_6s_4s, IPLAnalyser.BatOrBowl.BATTING);
            int size = iplAnalyser.loadBattingData(MOST_RUNS_CSV);
            List<CricketDataDAO> sortedList = iplAnalyser.getSorted();
            Assert.assertEquals("Andre Russell", sortedList.get(0).player);
            Assert.assertEquals("Tim Southee", sortedList.get(99).player);
        } catch (IPLAnalyserException e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLCSV_shouldReturnMOst6sAnd4sWithStrikeRate() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(ComparatorToSort.Sorting_Fields.STRIKE_RATE_4S_6S, IPLAnalyser.BatOrBowl.BATTING);
            int size = iplAnalyser.loadBattingData(MOST_RUNS_CSV);
            List<CricketDataDAO> sortedList = iplAnalyser.getSorted();
            Assert.assertEquals("Andre Russell", sortedList.get(0).player);
            Assert.assertEquals("Shakib Al Hasan", sortedList.get(99).player);
        } catch (IPLAnalyserException e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLCSV_shouldReturnMostStrikeRateWithAverages() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(ComparatorToSort.Sorting_Fields.STRIKE_RATE_AVERAGE, IPLAnalyser.BatOrBowl.BATTING);
            int size = iplAnalyser.loadBattingData(MOST_RUNS_CSV);
            List<CricketDataDAO> sortedList = iplAnalyser.getSorted();
            Assert.assertEquals("MS Dhoni", sortedList.get(0).player);
            Assert.assertEquals("Tim Southee", sortedList.get(99).player);
        } catch (IPLAnalyserException e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLCSV_shouldReturnMostRunsWithStrikeRate() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(ComparatorToSort.Sorting_Fields.MAX_RUNS_AVERAGES, IPLAnalyser.BatOrBowl.BATTING);
            int size = iplAnalyser.loadBattingData(MOST_RUNS_CSV);
            List<CricketDataDAO> sortedList = iplAnalyser.getSorted();
            Assert.assertEquals("David Warner", sortedList.get(0).player);
            Assert.assertEquals("Tim Southee", sortedList.get(99).player);
        } catch (IPLAnalyserException e){
            e.printStackTrace();
        }
    }

    //Bowling

    @Test
    public void givenBowlingCSV_shouldReturnCorrectValues() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            int size = iplAnalyser.loadBowlingData(BOWLING_CSV);
            Assert.assertEquals(99, size);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLCSV_shouldReturnTopBowlingAverages() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(ComparatorToSort.Sorting_Fields.AVERAGE, IPLAnalyser.BatOrBowl.BOWLING);
            int size = iplAnalyser.loadBowlingData(BOWLING_CSV);
            List<CricketDataDAO> sortedList = iplAnalyser.getSorted();
            Assert.assertEquals(0.0, sortedList.get(0).avg, 0);
            Assert.assertEquals(166.0, sortedList.get(98).avg, 0);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenIPLCSV_shouldReturnTopBowlingStrikerRate() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(ComparatorToSort.Sorting_Fields.STRIKE_RATE, IPLAnalyser.BatOrBowl.BOWLING);
            int size = iplAnalyser.loadBowlingData(BOWLING_CSV);
            List<CricketDataDAO> sortedList = iplAnalyser.getSorted();
            Assert.assertEquals(0.0, sortedList.get(0).strikeRate, 0);
            Assert.assertEquals(120.0, sortedList.get(98).strikeRate, 0);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLCSV_shouldReturnTopBowlingEconomyRate() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(ComparatorToSort.Sorting_Fields.ECONOMY_RATE, IPLAnalyser.BatOrBowl.BOWLING);
            int size = iplAnalyser.loadBowlingData(BOWLING_CSV);
            List<CricketDataDAO> sortedList = iplAnalyser.getSorted();
            Assert.assertEquals(4.8, sortedList.get(0).economyRate, 0);
            Assert.assertEquals(13.5, sortedList.get(98).economyRate, 0);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    //Exception

    @Test
    public void givenInvalidPath_shouldThrowException() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            int size = iplAnalyser.loadBattingData(INCORRECT_FILE);
        } catch (IPLAnalyserException e){
            Assert.assertEquals(IPLAnalyserException.ExceptionType.FILE_PATH_PROBLEM,e.type);
        }
    }

    @Test
    public void givenInvalidHeader_shouldThrowException() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            int size = iplAnalyser.loadBattingData(INCORRECT_FILE_FORMAT);
        } catch (IPLAnalyserException e){
            Assert.assertEquals(IPLAnalyserException.ExceptionType.INVALID_FILE_DATA_FORMAT,e.type);
        }
    }
}