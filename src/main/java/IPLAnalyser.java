import csvBuilder.CSVBuilderException;
import csvBuilder.CSVBuilderFactory;
import csvBuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class IPLAnalyser {

    List<BattingDataCSV> csvList = new ArrayList<>();

    public int loadBattingAverages(String csvFilePath) throws IPLAnalyserException {
        csvList = new DataLoader().loadBattingAverages(csvFilePath);
        return csvList.size();
    }

    public void getSorted(Comparator<BattingDataCSV> comp) {
        csvList = csvList.stream()
                .sorted(comp)
                .collect(Collectors.toList());
    }

    public List getSortedBattingAverages() {
        Comparator<BattingDataCSV> comp = (obj1, obj2) -> (obj1.avg - obj2.avg) > 0 ? -1 : 1;
        getSorted(comp);
        return csvList;
    }

    public List getSortedBattingStrikeRate() {
        Comparator<BattingDataCSV> comp = (obj1, obj2) -> (obj1.strikeRate - obj2.strikeRate) > 0 ? -1 : 1;
        getSorted(comp);
        return csvList;
    }

    public List getSortedMost6sAnd4s() {
        Comparator<BattingDataCSV> comp = (obj1, obj2) -> ((obj2.sixes * 6 + obj2.fours * 4) - (obj1.sixes * 6 + obj1.fours * 4));
        getSorted(comp);
        return csvList;
    }

    public List getSortedMost6sAnd4sWithStrikeRate() {
        Comparator<BattingDataCSV> comp = (obj1, obj2) -> ((obj2.sixes * 6 + obj2.fours * 4) - (obj1.sixes * 6 + obj1.fours * 4));
        comp = comp.thenComparing((obj1, obj2) -> (obj1.strikeRate - obj2.strikeRate) > 0 ? -1 : 1);
        getSorted(comp);
        return csvList;
    }

    public List getSortedMostStrikeRateWithAverage() {
        Comparator<BattingDataCSV> comp = (obj1, obj2) -> (obj1.avg - obj2.avg) > 0 ? -1 : (obj1.avg - obj2.avg) < 0 ? 1 : 0;
        comp = comp.thenComparing((obj1, obj2) -> (obj1.strikeRate - obj2.strikeRate) > 0 ? -1 : 1);
        getSorted(comp);
        return csvList;
    }
}
