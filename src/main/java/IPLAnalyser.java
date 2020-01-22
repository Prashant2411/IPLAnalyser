import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

public class IPLAnalyser {

    public ComparatorToSort.Sorting_Fields stat;
    public BatOrBowl batOrBowl;

    Map<String, CricketDataDAO> csvMap = new HashMap<>();
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

    public int loadData(String csvFilePath) throws IPLAnalyserException {
        csvMap = new LoadDataFactory().getLoadData(batOrBowl, csvFilePath);
        return csvMap.size();
    }

    public List getSorted() throws IPLAnalyserException {
        if(csvMap.size()==0 || csvMap ==null)
            throw new IPLAnalyserException("No List Found",IPLAnalyserException.ExceptionType.NULL_EXCEPTION);
        sortComparator = new ComparatorToSort().getComparator(stat);
        if (batOrBowl.equals(BatOrBowl.BOWLING)){sortComparator=sortComparator.reversed();}
        List csvList1 = csvMap.values().stream()
                .sorted(sortComparator)
                .map(cricketDataDAO -> cricketDataDAO.getCricketDataDTO(batOrBowl))
                .collect(Collectors.toList());
        return csvList1;
    }
}