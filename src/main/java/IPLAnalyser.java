import csvBuilder.CSVBuilderException;
import csvBuilder.CSVBuilderFactory;
import csvBuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
}
