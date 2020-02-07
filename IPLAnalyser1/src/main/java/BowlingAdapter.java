import csvBuilder.CsvBuilderException;

import java.util.Map;

public class BowlingAdapter extends DataLoader{
    @Override
    public Map<String, CricketDataDAO> loadCSVRecord(String... csvFilePath) throws IPLAnalyserException, CsvBuilderException {
        Map<String, CricketDataDAO> csvDataMap = super.loadCSVRecord(BowlingDataCSV.class, csvFilePath[0]);
        return csvDataMap;
    }
}
