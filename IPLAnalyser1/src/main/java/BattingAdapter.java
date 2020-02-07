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

public class BattingAdapter extends DataLoader{

    Map<String, CricketDataDAO> csvDataMap = new HashMap<>();

    @Override
    public Map<String, CricketDataDAO> loadCSVRecord(String... csvFilePath) throws IPLAnalyserException, CsvBuilderException {
        csvDataMap = super.loadCSVRecord(BattingDataCSV.class, csvFilePath[0]);
        if(csvFilePath.length==2){
            csvDataMap = this.loadCSVRecord(BowlingDataCSV.class, csvFilePath[1]);
        }
        return csvDataMap;
    }

    public <E> Map<String, CricketDataDAO> loadCSVRecord(Class<E> className, String csvFilePath) throws IPLAnalyserException, CsvBuilderException {
        try (Reader reader = newBufferedReader(Paths.get(String.valueOf(csvFilePath)))) {
            ICSVBuilder csvBuilder = new CSVBuilderFactory().createCSVBuilder();
            List<BowlingDataCSV> csvList = csvBuilder.getCSVFileList(reader, className);
                StreamSupport.stream(csvList.spliterator(), false)
                        .filter(cricketData -> csvDataMap.get(cricketData.player) != null)
                        .forEach(cricketData -> {
                            csvDataMap.get(cricketData.player).bowlingAvg = cricketData.bowlingAvg;
                            csvDataMap.get(cricketData.player).wickets = cricketData.wickets;
                        });
            return csvDataMap;
        } catch (IOException e) {
            throw new IPLAnalyserException("Invalid Path", IPLAnalyserException.ExceptionType.FILE_PATH_PROBLEM);
        } catch (CsvBuilderException e) {
            throw new IPLAnalyserException(e.getMessage(),e.type.name());
        } catch (RuntimeException e) {
            throw new IPLAnalyserException("Invalid File Format", IPLAnalyserException.ExceptionType.INVALID_FILE_DATA_FORMAT);
        }
    }
}