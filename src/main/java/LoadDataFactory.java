import java.util.HashMap;
import java.util.Map;

public class LoadDataFactory {

    Map<String, CricketDataDAO> csvMap = new HashMap<>();

    public Map<String, CricketDataDAO> getLoadData(IPLAnalyser.BatOrBowl batOrBowl, String... csvFilePath) throws IPLAnalyserException {
        if (IPLAnalyser.BatOrBowl.BAT_BOWL.equals(batOrBowl)) {
            csvMap = new BattingAdapter().loadCSVRecord(csvFilePath);
            return csvMap;
        }
        else if (IPLAnalyser.BatOrBowl.BATTING.equals(batOrBowl)) {
            return new BattingAdapter().loadCSVRecord(csvFilePath[0]);
        }
        else if (IPLAnalyser.BatOrBowl.BOWLING.equals(batOrBowl)) {
            return new BowlingAdapter().loadCSVRecord(csvFilePath);
        }
        return null;
    }
}
