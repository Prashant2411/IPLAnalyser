import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class IPLAnalyserTest {
    public String MOST_RUNS_CSV = "/home/admin1/IdeaProjects/IPLAnalyser/src/test/resources/Batting.csv";

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
}