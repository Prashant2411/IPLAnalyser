import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class IPLAnalyserTest {
    public String MOST_RUNS_CSV = "/home/admin1/IdeaProjects/IPLAnalyser/src/test/resources/Batting.csv";
    public String INCORRECT_FILE = "/home/admin1/IdeaProjects/IPL_Analyser/src/test/resources/MostRuns";
    public String INCORRECT_FILE_FORMAT = "/home/admin1/IdeaProjects/IPL_Analyser/src/test/resources/InvalidHeader.csv";

    @Test
    public void givenMostRunCSV_shouldReturnCorrectValues() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            int size = iplAnalyser.loadBattingAverages(MOST_RUNS_CSV);
            Assert.assertEquals(100, size);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLCSV_shouldReturnTopBattingAverages() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            int size = iplAnalyser.loadBattingAverages(MOST_RUNS_CSV);
            List<MostRunsCSV> sortedList = iplAnalyser.getSortedBattingAverages();
            Assert.assertEquals(83.2, sortedList.get(0).avg, 0);
            Assert.assertEquals(0.0, sortedList.get(99).avg, 0);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenIPLCSV_shouldReturnTopBattingStrikeRate() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            int size = iplAnalyser.loadBattingAverages(MOST_RUNS_CSV);
            List<MostRunsCSV> sortedList = iplAnalyser.getSortedBattingStrikeRate();
            Assert.assertEquals(333.33,sortedList.get(0).strikeRate,0);
            Assert.assertEquals(63.15,sortedList.get(99).strikeRate,0);
        } catch (IPLAnalyserException e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLCSV_shouldReturnMOst6sAnd4s() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            int size = iplAnalyser.loadBattingAverages(MOST_RUNS_CSV);
            List<MostRunsCSV> sortedList = iplAnalyser.getSortedMost6sAnd4s();
            Assert.assertEquals("Andre Russell", sortedList.get(0).player);
            Assert.assertEquals("Tim Southee", sortedList.get(99).player);
        } catch (IPLAnalyserException e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLCSV_shouldReturnMOst6sAnd4sWithStrikeRate() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            int size = iplAnalyser.loadBattingAverages(MOST_RUNS_CSV);
            List<MostRunsCSV> sortedList = iplAnalyser.getSortedMost6sAnd4sWithStrikeRate();
            Assert.assertEquals("Andre Russell", sortedList.get(0).player);
            Assert.assertEquals("Shakib Al Hasan", sortedList.get(99).player);
        } catch (IPLAnalyserException e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLCSV_shouldReturnMostStrikeRateWithAverages() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            int size = iplAnalyser.loadBattingAverages(MOST_RUNS_CSV);
            List<MostRunsCSV> sortedList = iplAnalyser.getSortedMostStrikeRateWithAverage();
            Assert.assertEquals("MS Dhoni", sortedList.get(0).player);
            Assert.assertEquals("Tim Southee", sortedList.get(99).player);
        } catch (IPLAnalyserException e){
            e.printStackTrace();
        }
    }

    //Exception

    @Test
    public void givenInvalidPath_shouldThrowException() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            int size = iplAnalyser.loadBattingAverages(INCORRECT_FILE);
        } catch (IPLAnalyserException e){
            Assert.assertEquals(IPLAnalyserException.ExceptionType.FILE_PATH_PROBLEM,e.type);
        }
    }

    @Test
    public void givenInvalidHeader_shouldThrowException() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            int size = iplAnalyser.loadBattingAverages(INCORRECT_FILE_FORMAT);
        } catch (IPLAnalyserException e){
            Assert.assertEquals(IPLAnalyserException.ExceptionType.INVALID_FILE_DATA_FORMAT,e.type);
        }
    }
}