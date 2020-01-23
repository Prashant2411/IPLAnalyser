import com.opencsv.bean.CsvBindByName;

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
        public double bowlingAvg;
        @CsvBindByName(column = "Econ", required = true)
        public double economyRate;
        @CsvBindByName(column = "SR", required = true)
        public double strikeRate;
        @CsvBindByName(column = "4w", required = true)
        public int fourWicket;
        @CsvBindByName(column = "5w", required = true)
        public int fiveWicket;

        public BowlingDataCSV() {
        }

        public BowlingDataCSV(int pos, String player, int matches, int inns, double over, int runs, int wickets, int bestBowlingIndex, double avg, double economyRate, double strikeRate, int fourWicket, int fiveWicket) {
                this.pos = pos;
                this.player = player;
                this.matches = matches;
                this.inns = inns;
                this.over = over;
                this.runs = runs;
                this.wickets = wickets;
                this.bestBowlingIndex = bestBowlingIndex;
                this.bowlingAvg = avg;
                this.economyRate = economyRate;
                this.strikeRate = strikeRate;
                this.fourWicket = fourWicket;
                this.fiveWicket = fiveWicket;
        }

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
                        ", bowlinAvg=" + bowlingAvg +
                        ", economyRate=" + economyRate +
                        ", strikeRate=" + strikeRate +
                        ", fourWicket=" + fourWicket +
                        ", fiveWicket=" + fiveWicket +
                        '}';
        }
}
