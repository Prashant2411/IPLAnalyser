import csvBuilder.CSVBuilderException;
import csvBuilder.CSVBuilderFactory;
import csvBuilder.ICSVBuilder;

import java.awt.image.CropImageFilter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.nio.file.Files.newBufferedReader;

public class DataLoader {

    List<CricketDataDAO> csvList = new ArrayList<>();

    public <E> List loadCSVRecord(Class<E> className, String csvFilePath) throws IPLAnalyserException {
        try (Reader reader = newBufferedReader(Paths.get(String.valueOf(csvFilePath)))) {
            ICSVBuilder csvBuilder = new CSVBuilderFactory().createCSVBuilder();
            List<E> csvList1 = csvBuilder.getCSVFileInList(reader, className);
            if(className.equals(BattingDataCSV.class)) {
                StreamSupport.stream(csvList1.spliterator(), false)
                        .map(BattingDataCSV.class::cast)
                          .forEach(cricketData -> csvList.add(new CricketDataDAO(cricketData)));
            }
            else if(className.equals(BowlingDataCSV.class)){
                StreamSupport.stream(csvList1.spliterator(), false)
                        .map(BowlingDataCSV.class::cast)
                        .forEach(cricketData -> csvList.add(new CricketDataDAO(cricketData)));
            }
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
