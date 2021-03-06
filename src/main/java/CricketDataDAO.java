public class CricketDataDAO {
    public int pos;
    public String player;
    public int matches;
    public int inns;
    public int notOut;
    public int runs;
    public int highScore;
    public double battingAvg;
    public double bowlingAvg;
    public int ballFaced;
    public double strikeRate;
    public int centuries;
    public int halfCenturies;
    public int fours;
    public int sixes;
    public double over;
    public int wickets;
    public int bestBowlingIndex;
    public double economyRate;
    public int fourWicket;
    public int fiveWicket;

    public CricketDataDAO(BattingDataCSV batDataCSV) {
        pos = batDataCSV.pos;
        player = batDataCSV.player;
        matches = batDataCSV.matches;
        inns = batDataCSV.inns;
        notOut = batDataCSV.notOut;
        runs = batDataCSV.runs;
        highScore = batDataCSV.highScore;
        ballFaced = batDataCSV.ballFaced;
        strikeRate = batDataCSV.strikeRate;
        centuries = batDataCSV.centuries;
        halfCenturies = batDataCSV.halfCenturies;
        fours = batDataCSV.fours;
        sixes = batDataCSV.sixes;
        battingAvg = batDataCSV.battingAvg;
    }

    public CricketDataDAO(BowlingDataCSV bowlDataCSV) {
        pos = bowlDataCSV.pos;
        player = bowlDataCSV.player;
        matches = bowlDataCSV.matches;
        inns = bowlDataCSV.inns;
        over = bowlDataCSV.over;
        runs = bowlDataCSV.runs;
        wickets = bowlDataCSV.wickets;
        bestBowlingIndex = bowlDataCSV.bestBowlingIndex;
        economyRate = bowlDataCSV.economyRate;
        strikeRate = bowlDataCSV.strikeRate;
        fourWicket = bowlDataCSV.fourWicket;
        fiveWicket = bowlDataCSV.fiveWicket;
        bowlingAvg = bowlDataCSV.bowlingAvg;
    }

    public CricketDataDAO() {
    }

    @Override
    public String toString() {
        return "CricketDataDAO{" +
                "pos=" + pos +
                ", player='" + player + '\'' +
                ", matches=" + matches +
                ", inns=" + inns +
                ", notOut=" + notOut +
                ", runs=" + runs +
                ", highScore=" + highScore +
                ", battingAvg=" + battingAvg +
                ", bowlingAvg=" + bowlingAvg +
                ", ballFaced=" + ballFaced +
                ", strikeRate=" + strikeRate +
                ", centuries=" + centuries +
                ", halfCenturies=" + halfCenturies +
                ", fours=" + fours +
                ", sixes=" + sixes +
                ", over=" + over +
                ", wickets=" + wickets +
                ", bestBowlingIndex=" + bestBowlingIndex +
                ", economyRate=" + economyRate +
                ", fourWicket=" + fourWicket +
                ", fiveWicket=" + fiveWicket +
                '}';
    }

    public Object getCricketDataDTO(IPLAnalyser.BatOrBowl batOrBowl) {
        if(IPLAnalyser.BatOrBowl.BATTING.equals(batOrBowl))
            return new BattingDataCSV(pos, player, matches, inns, notOut, runs, highScore, battingAvg, ballFaced, strikeRate, centuries, halfCenturies, fours, sixes);
        return new BowlingDataCSV(pos, player, matches, inns, over, runs, wickets, bestBowlingIndex, bowlingAvg, economyRate, strikeRate, fourWicket, fiveWicket);
    }

    public String getPlayer() {
        return player;
    }
}
