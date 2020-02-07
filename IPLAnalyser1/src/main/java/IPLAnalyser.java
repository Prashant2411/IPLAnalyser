import csvBuilder.CsvBuilderException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IPLAnalyser {

    public LoadDataFactory loadDataFactory = new LoadDataFactory();
    public ComparatorToSort.Sorting_Fields stat;
    public BatOrBowl batOrBowl;

    Map<String, CricketDataDAO> csvMap = new HashMap<>();
    static Comparator<CricketDataDAO> sortComparator = null;

    public enum BatOrBowl{
        BATTING, BOWLING
    }

    public IPLAnalyser(LoadDataFactory loadDataFactory,ComparatorToSort.Sorting_Fields stat, BatOrBowl batOrBowl) {
        this.loadDataFactory = loadDataFactory;
        this.stat = stat;
        this.batOrBowl = batOrBowl;
    }

    public IPLAnalyser(ComparatorToSort.Sorting_Fields stat, BatOrBowl batOrBowl) {
        this.stat = stat;
        this.batOrBowl = batOrBowl;
    }

    public int loadData(String... csvFilePath) throws IPLAnalyserException, CsvBuilderException {
        csvMap = loadDataFactory.getLoadData(batOrBowl, csvFilePath);
        System.out.println(csvMap);
        return csvMap.size();
    }

    public List getSorted() throws IPLAnalyserException {
        if(csvMap.size()==0 || csvMap ==null)
            throw new IPLAnalyserException("No List Found", IPLAnalyserException.ExceptionType.NULL_EXCEPTION);
        sortComparator = new ComparatorToSort().getComparator(stat);
        if (batOrBowl.equals(BatOrBowl.BOWLING)){sortComparator=sortComparator.reversed();}
        List csvList1 = csvMap.values().stream()
                .sorted(sortComparator)
                .map(cricketDataDAO -> cricketDataDAO.getCricketDataDTO(batOrBowl))
                .collect(Collectors.toList());
        return csvList1;
    }
}