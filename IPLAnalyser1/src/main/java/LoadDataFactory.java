import csvBuilder.CsvBuilderException;

import java.util.HashMap;
import java.util.Map;

public class LoadDataFactory {

    Map<String, CricketDataDAO> csvMap = new HashMap<>();

    public Map<String, CricketDataDAO> getLoadData(IPLAnalyser.BatOrBowl batOrBowl, String... csvFilePath) throws IPLAnalyserException, CsvBuilderException {
        if (IPLAnalyser.BatOrBowl.BATTING.equals(batOrBowl)) {
            return new BattingAdapter().loadCSVRecord(csvFilePath);
        }
        else if (IPLAnalyser.BatOrBowl.BOWLING.equals(batOrBowl)) {
            return new BowlingAdapter().loadCSVRecord(csvFilePath);
        }
        return null;
    }
}
