import csvBuilder.CSVBuilderFactory;
import csvBuilder.CsvBuilderException;
import csvBuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

import static java.nio.file.Files.newBufferedReader;

public abstract class DataLoader {
    public abstract Map<String, CricketDataDAO> loadCSVRecord(String... csvFilePath) throws IPLAnalyserException, CsvBuilderException;

    Map<String, CricketDataDAO> csvMap = new HashMap<>();

    public <E> Map<String, CricketDataDAO> loadCSVRecord(Class<E> className, String csvFilePath) throws IPLAnalyserException, CsvBuilderException {
        try (Reader reader = newBufferedReader(Paths.get(String.valueOf(csvFilePath)))) {
            ICSVBuilder csvBuilder = new CSVBuilderFactory().createCSVBuilder();
            List<E> csvList1 = csvBuilder.getCSVFileList(reader, className);
            if (className.equals(BattingDataCSV.class)) {
                StreamSupport.stream(csvList1.spliterator(), false)
                        .map(BattingDataCSV.class::cast)
                        .forEach(cricketData -> csvMap.put(cricketData.player, new CricketDataDAO(cricketData)));
            } else if (className.equals(BowlingDataCSV.class)) {
                StreamSupport.stream(csvList1.spliterator(), false)
                        .map(BowlingDataCSV.class::cast)
                        .forEach(cricketData -> csvMap.put(cricketData.player, new CricketDataDAO(cricketData)));
            }
            return csvMap;
        } catch (IOException e) {
            throw new IPLAnalyserException("Invalid Path", IPLAnalyserException.ExceptionType.FILE_PATH_PROBLEM);
        } catch (CsvBuilderException e) {
            throw new IPLAnalyserException(e.getMessage(), e.type.name());
        } catch (RuntimeException e) {
            throw new IPLAnalyserException("Invalid File Format", IPLAnalyserException.ExceptionType.INVALID_FILE_DATA_FORMAT);
        }
    }
}