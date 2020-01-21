import java.util.List;

public class LoadDataFactory {
    public List<CricketDataDAO> getLoadData(IPLAnalyser.BatOrBowl batOrBowl, String csvFilePath) throws IPLAnalyserException {
        if (IPLAnalyser.BatOrBowl.BATTING.equals(batOrBowl))
            return new DataLoader().loadCSVRecord(BattingDataCSV.class, csvFilePath);
        return new DataLoader().loadCSVRecord(BowlingDataCSV.class, csvFilePath);
    }
}
