import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

public class IPLAnalyser {

    public ComparatorToSort.Sorting_Fields stat;
    public BatOrBowl batOrBowl;

    List<CricketDataDAO> csvList = new ArrayList<>();
    static Comparator<CricketDataDAO> sortComparator = null;

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
        sortComparator = new ComparatorToSort().getComparator(stat);
        if (batOrBowl.equals(BatOrBowl.BOWLING)){sortComparator=sortComparator.reversed();}
        else if (batOrBowl.equals(BatOrBowl.BATTING)){ sortComparator=sortComparator;}
        csvList = csvList.stream()
                .sorted(sortComparator)
                .collect(Collectors.toList());
        return csvList;
    }
}