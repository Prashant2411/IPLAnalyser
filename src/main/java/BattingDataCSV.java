import com.opencsv.bean.CsvBindByName;

public class BattingDataCSV {
    @CsvBindByName(column = "Pos", required = true)
    public int pos;
    @CsvBindByName(column = "PLAYER", required = true)
    public String player;
    @CsvBindByName(column = "Mat", required = true)
    public int matches;
    @CsvBindByName(column = "Inns", required = true)
    public int inns;
    @CsvBindByName(column = "NO", required = true)
    public int notOut;
    @CsvBindByName(column = "Runs", required = true)
    public int runs;
    @CsvBindByName(column = "HS", required = true)
    public int highScore;
    @CsvBindByName(column = "Avg", required = true)
    public double avg;
    @CsvBindByName(column = "BF", required = true)
    public int ballFaced;
    @CsvBindByName(column = "SR", required = true)
    public double strikeRate;
    @CsvBindByName(column = "100", required = true)
    public int centuries;
    @CsvBindByName(column = "50", required = true)
    public int halfCenturies;
    @CsvBindByName(column = "4s", required = true)
    public int fours;
    @CsvBindByName(column = "6s", required = true)
    public int sixes;

    @Override
    public String toString() {
        return "BattingDataCSV{" +
                "pos=" + pos +
                ", player='" + player +
                ", matches=" + matches +
                ", inns=" + inns +
                ", notOut=" + notOut +
                ", runs=" + runs +
                ", highScore=" + highScore +
                ", avg=" + avg +
                ", ballFaced=" + ballFaced +
                ", strikeRate=" + strikeRate +
                ", centuries=" + centuries +
                ", halfCenturies=" + halfCenturies +
                ", fours=" + fours +
                ", sixes=" + sixes +
                '}';
    }
}


