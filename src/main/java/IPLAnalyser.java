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
}
