import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class IPLAnalyser {

    public ComparatorToSort.Sorting_Fields stat;

    List<BattingDataCSV> csvList = new ArrayList<>();
    static Comparator<BattingDataCSV> comp = null;

    public IPLAnalyser(ComparatorToSort.Sorting_Fields stat) {
        this.stat = stat;git
    }

    public IPLAnalyser() {
    }

    public int loadBattingData(String csvFilePath) throws IPLAnalyserException {
        csvList = new DataLoader().loadCSVRecord(BattingDataCSV.class, csvFilePath);
        return csvList.size();
    }

    public int loadBowlingData(String csvFilePath) throws IPLAnalyserException {
        csvList = new DataLoader().loadCSVRecord(BowlingDataCSV.class, csvFilePath);
        return csvList.size();
    }

    public List<BattingDataCSV> getSorted() throws IPLAnalyserException {
        if(csvList.size()==0 || csvList==null)
            throw new IPLAnalyserException("No List Found",IPLAnalyserException.ExceptionType.NULL_EXCEPTION);
        comp = new ComparatorToSort().getComparator(stat);
        csvList = csvList.stream()
                .sorted(comp)
                .collect(Collectors.toList());
        return csvList;
    }
}