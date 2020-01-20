import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ComparatorToSort<B> {

    public enum Stat{
        AVERAGE, STRIKE_RATE, COMPARE_6s_4s, STRIKE_RATE_4S_6S, STRIKE_RATE_AVERAGE, MAX_RUNS, MAX_RUNS_AVERAGES
    }

    Comparator<BattingDataCSV> comp = null;
    ArrayList<Comparator<BattingDataCSV>> arrayList = new ArrayList<>();

    public Comparator<BattingDataCSV> getComparator(Stat stat) {
        arrayList.add((obj1, obj2) -> obj1.avg - obj2.avg < 0 ? 1 :obj1.avg - obj2.avg > 0 ? -1:0);
        arrayList.add((obj1, obj2) -> (obj1.strikeRate - obj2.strikeRate) > 0 ? -1 : 1);
        arrayList.add((obj1, obj2) -> ((obj2.sixes * 6 + obj2.fours * 4) - (obj1.sixes * 6 + obj1.fours * 4)));
        arrayList.add(arrayList.get(Stat.COMPARE_6s_4s.ordinal()).thenComparing(arrayList.get(Stat.STRIKE_RATE.ordinal())));
        arrayList.add(arrayList.get(Stat.AVERAGE.ordinal()).thenComparing(arrayList.get(Stat.STRIKE_RATE.ordinal())));
        arrayList.add((obj1, obj2) -> obj2.runs-obj1.runs);
        arrayList.add(arrayList.get(Stat.MAX_RUNS.ordinal()).thenComparing(arrayList.get(Stat.AVERAGE.ordinal())));
        return arrayList.get(stat.ordinal());
    }
}
