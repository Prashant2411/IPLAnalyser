public class IPLAnalyserException extends Exception {
    enum ExceptionType {
        FILE_PATH_PROBLEM, UNABLE_TO_PARSE, INVALID_FILE_DATA_FORMAT, NULL_EXCEPTION
    }

    ExceptionType type;

    public IPLAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public IPLAnalyserException(String message, String name) {
        super(message);
        this.type = ExceptionType.valueOf(name);
    }
}
