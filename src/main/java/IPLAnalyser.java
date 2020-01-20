import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

public class IPLAnalyser {

    public ComparatorToSort.Sorting_Fields stat;
    public BatOrBowl batOrBowl;

    List csvList = new ArrayList<>();
    static Comparator comp = null;

    public enum BatOrBowl{
        BATTING, BOWLING
    }

    public IPLAnalyser() {
    }

    public IPLAnalyser(ComparatorToSort.Sorting_Fields stat, BatOrBowl batOrBowl) {
        this.stat = stat;
        this.batOrBowl = batOrBowl;
    }

    public int loadBattingData(String csvFilePath) throws IPLAnalyserException {
        csvList = new DataLoader().loadCSVRecord(BattingDataCSV.class, csvFilePath);
        return csvList.size();
    }

    public int loadBowlingData(String csvFilePath) throws IPLAnalyserException {
        csvList = new DataLoader().loadCSVRecord(BowlingDataCSV.class, csvFilePath);
        return csvList.size();
    }

    public List getSorted() throws IPLAnalyserException {
        if(csvList.size()==0 || csvList==null)
            throw new IPLAnalyserException("No List Found",IPLAnalyserException.ExceptionType.NULL_EXCEPTION);
        comp = new ComparatorToSort().getComparator(stat, batOrBowl);
        csvList = (List) csvList.stream()
                .sorted(comp)
                .collect(toCollection(ArrayList::new));
        return csvList;
    }
}