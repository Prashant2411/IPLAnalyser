import com.opencsv.bean.CsvBindByName;
import jdk.internal.jline.internal.Nullable;

public class BowlingDataCSV {

        @CsvBindByName(column = "POS", required = true)
        public int pos;
        @CsvBindByName(column = "PLAYER", required = true)
        public String player;
        @CsvBindByName(column = "Mat", required = true)
        public int matches;
        @CsvBindByName(column = "Inns", required = true)
        public int inns;
        @CsvBindByName(column = "Ov", required = true)
        public double over;
        @CsvBindByName(column = "Runs", required = true)
        public int runs;
        @CsvBindByName(column = "Wkts", required = true)
        public int wickets;
        @CsvBindByName(column = "BBI", required = true)
        public int bestBowlingIndex;
        @CsvBindByName(column = "Avg", required = true)
        public double avg;
        @CsvBindByName(column = "Econ", required = true)
        public double economyRate;
        @CsvBindByName(column = "SR", required = true)
        public double strikeRate;
        @CsvBindByName(column = "4w", required = true)
        public int fourWicket;
        @CsvBindByName(column = "5w", required = true)
        public int fiveWicket;

        @Override
        public String toString() {
                return "BowlingDataCSV{" +
                        "pos=" + pos +
                        ", player='" + player +
                        ", matches=" + matches +
                        ", inns=" + inns +
                        ", over=" + over +
                        ", runs=" + runs +
                        ", wickets=" + wickets +
                        ", bestBowlingIndex=" + bestBowlingIndex +
                        ", avg=" + avg +
                        ", economyRate=" + economyRate +
                        ", strikeRate=" + strikeRate +
                        ", fourWicket=" + fourWicket +
                        ", fiveWicket=" + fiveWicket +
                        '}';
        }
}
