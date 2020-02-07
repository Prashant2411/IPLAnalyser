import csvBuilder.CsvBuilderException;
import org.apache.commons.collections.map.HashedMap;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

public class IPLAnalyserTestUsingMockito {

    public String MOST_RUNS_CSV = "/home/admin1/Documents/Prashant/IPLAnalyzer/src/test/resources/Batting.csv";

    public String BOWLING_CSV = "/home/admin1/Documents/Prashant/IPLAnalyzer/src/test/resources/Bowling.csv";

    Map<String, CricketDataDAO> csvMap = new HashMap<>();
    IPLAnalyser iplAnalyser = null;

    @Mock
     LoadDataFactory loadDataFactory;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void givenMostRunCSV_shouldReturnCorrectValues() throws CsvBuilderException {
        try {
            csvMap.put("A", new CricketDataDAO());
            csvMap.put("B", new CricketDataDAO());
            IPLAnalyser iplAnalyser = new IPLAnalyser(loadDataFactory, ComparatorToSort.Sorting_Fields.COMPARE_6s_4s, IPLAnalyser.BatOrBowl.BATTING);;
            when(loadDataFactory.getLoadData(IPLAnalyser.BatOrBowl.BATTING, MOST_RUNS_CSV)).thenReturn(csvMap);
            int size = iplAnalyser.loadData(MOST_RUNS_CSV);
            Assert.assertEquals(2, size);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenBowlingCSV_shouldReturnCorrectValues() throws CsvBuilderException {
        try {
            csvMap.put("A", new CricketDataDAO());
            csvMap.put("B", new CricketDataDAO());
            IPLAnalyser iplAnalyser = new IPLAnalyser(loadDataFactory, ComparatorToSort.Sorting_Fields.COMPARE_5W_AND_4W, IPLAnalyser.BatOrBowl.BOWLING);
            when(loadDataFactory.getLoadData(IPLAnalyser.BatOrBowl.BOWLING, BOWLING_CSV)).thenReturn(csvMap);
            int size = iplAnalyser.loadData(BOWLING_CSV);
            Assert.assertEquals(2, size);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenBattingBowlingCSV_shouldReturnCorrectValues() throws CsvBuilderException {
        try {
            csvMap.put("A", new CricketDataDAO());
            csvMap.put("B", new CricketDataDAO());
            IPLAnalyser iplAnalyser = new IPLAnalyser(ComparatorToSort.Sorting_Fields.COMPARE_5W_AND_4W, IPLAnalyser.BatOrBowl.BATTING);
            when(loadDataFactory.getLoadData(IPLAnalyser.BatOrBowl.BOWLING, MOST_RUNS_CSV, BOWLING_CSV)).thenReturn(csvMap);
            int size = iplAnalyser.loadData(MOST_RUNS_CSV,BOWLING_CSV);
            Assert.assertEquals(100, size);
        } catch (IPLAnalyserException e) {
            e.printStackTrace();
        }
    }
}
