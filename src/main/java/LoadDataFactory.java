import java.util.Map;

public class LoadDataFactory {
    public Map<String, CricketDataDAO> getLoadData(IPLAnalyser.BatOrBowl batOrBowl, String csvFilePath) throws IPLAnalyserException {
        if (IPLAnalyser.BatOrBowl.BATTING.equals(batOrBowl))
            return new DataLoader().loadCSVRecord(BattingDataCSV.class, csvFilePath);
        return new DataLoader().loadCSVRecord(BowlingDataCSV.class, csvFilePath);
    }
}
