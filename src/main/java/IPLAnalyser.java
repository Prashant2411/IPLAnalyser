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

import static java.nio.file.Files.newBufferedReader;

public class IPLAnalyser {

    List<MostRunsCSV> csvList = new ArrayList<>();

    public int loadBattingAverages(String csvFilePath) throws IPLAnalyserException {
        try (Reader reader = newBufferedReader(Paths.get(String.valueOf(csvFilePath)))) {
            ICSVBuilder csvBuilder = new CSVBuilderFactory().createCSVBuilder();
            csvList = csvBuilder.getCSVFileInList(reader, MostRunsCSV.class);
            return csvList.size();
        } catch (IOException e) {
            throw new IPLAnalyserException("Invalid Path",IPLAnalyserException.ExceptionType.FILE_PATH_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new IPLAnalyserException(e.getMessage(),e.type.name());
        } catch (RuntimeException e) {
            throw new IPLAnalyserException("Invalid File Format",IPLAnalyserException.ExceptionType.INVALID_FILE_DATA_FORMAT);
        }
    }

    public void getSorted(Comparator<MostRunsCSV> comp) {
        csvList = csvList.stream()
                .sorted(comp)
                .collect(Collectors.toList());
    }

    public List getSortedBattingAverages() {
        Comparator<MostRunsCSV> comp = (obj1, obj2) -> (obj1.avg - obj2.avg) > 0 ? -1 : 1;
        getSorted(comp);
        return csvList;
    }

    public List getSortedBattingStrikeRate() {
        Comparator<MostRunsCSV> comp = (obj1, obj2) -> (obj1.strikeRate - obj2.strikeRate) > 0 ? -1 : 1;
        getSorted(comp);
        return csvList;
    }

    public List getSortedMost6sAnd4s() {
        Comparator<MostRunsCSV> comp = (obj1, obj2) -> ((obj2.sixes * 6 + obj2.fours * 4) - (obj1.sixes * 6 + obj1.fours * 4));
        getSorted(comp);
        return csvList;
    }

    public List getSortedMost6sAnd4sWithStrikeRate() {
        Comparator<MostRunsCSV> comp = (obj1, obj2) -> ((obj2.sixes * 6 + obj2.fours * 4) - (obj1.sixes * 6 + obj1.fours * 4));
        comp = comp.thenComparing((obj1, obj2) -> (obj1.strikeRate - obj2.strikeRate) > 0 ? -1 : 1);
        getSorted(comp);
        return csvList;
    }

    public List getSortedMostStrikeRateWithAverage() {
        Comparator<MostRunsCSV> comp = (obj1, obj2) -> (obj1.avg - obj2.avg) > 0 ? -1 : (obj1.avg - obj2.avg) < 0 ? 1 : 0;
        comp = comp.thenComparing((obj1, obj2) -> (obj1.strikeRate - obj2.strikeRate) > 0 ? -1 : 1);
        getSorted(comp);
        return csvList;
    }
}
