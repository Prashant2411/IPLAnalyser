import java.util.Map;

public class BattingAdapter extends DataLoader{
    @Override
    public Map<String, CricketDataDAO> loadCSVRecord(String csvFilePath) throws IPLAnalyserException {
        Map<String, CricketDataDAO> csvDataMap = super.loadCSVRecord(BattingDataCSV.class, csvFilePath);
        return csvDataMap;
    }
}
