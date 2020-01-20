import csvBuilder.CSVBuilderException;
import csvBuilder.CSVBuilderFactory;
import csvBuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.newBufferedReader;

public class DataLoader {

    List<BattingDataCSV> csvList = new ArrayList<>();

    public List<BattingDataCSV> loadCSVRecord(Class className, String csvFilePath) throws IPLAnalyserException {
        try (Reader reader = newBufferedReader(Paths.get(String.valueOf(csvFilePath)))) {
            ICSVBuilder csvBuilder = new CSVBuilderFactory().createCSVBuilder();
            csvList = csvBuilder.getCSVFileInList(reader, className);
            return csvList;
        } catch (IOException e) {
            throw new IPLAnalyserException("Invalid Path",IPLAnalyserException.ExceptionType.FILE_PATH_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new IPLAnalyserException(e.getMessage(),e.type.name());
        } catch (RuntimeException e) {
            throw new IPLAnalyserException("Invalid File Format",IPLAnalyserException.ExceptionType.INVALID_FILE_DATA_FORMAT);
        }
    }
}
