import org.junit.Assert;
import org.junit.Test;

public class IPLAnalyserTest {
    public String MOST_RUNS_CSV = "/home/admin1/IdeaProjects/IPL_Analyser/src/test/resources/Batting.csv";

    @Test
    public void givenMostRunCSV_shouldReturnCorrectValues() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            int size = iplAnalyser.loadBattingAverages(MOST_RUNS_CSV);
            Assert.assertEquals(100,size);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }
}
