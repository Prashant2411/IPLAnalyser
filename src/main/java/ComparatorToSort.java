import java.util.ArrayList;
import java.util.Comparator;

public class ComparatorToSort {

    public enum Sorting_Fields {
        BAT_AVERAGE, STRIKE_RATE, COMPARE_6s_4s, STRIKE_RATE_4S_6S, STRIKE_RATE_AVERAGE, MAX_RUNS, MAX_RUNS_AVERAGES,
        BOWL_AVERAGE, BOWL_STRIKE_RATE
    }

    public Comparator getComparator(Sorting_Fields stat, IPLAnalyser.BatOrBowl batOrBowl) {
        if (batOrBowl.equals(IPLAnalyser.BatOrBowl.BATTING)) {
            ArrayList<Comparator<BattingDataCSV>> arrayList = new ArrayList<>();
            arrayList.add((obj1, obj2) -> obj1.avg - obj2.avg < 0 ? 1 : obj1.avg - obj2.avg > 0 ? -1 : 0);
            arrayList.add((obj1, obj2) -> (obj1.strikeRate - obj2.strikeRate) > 0 ? -1 : 1);
            arrayList.add((obj1, obj2) -> ((obj2.sixes * 6 + obj2.fours * 4) - (obj1.sixes * 6 + obj1.fours * 4)));
            arrayList.add(arrayList.get(Sorting_Fields.COMPARE_6s_4s.ordinal()).thenComparing(arrayList.get(Sorting_Fields.STRIKE_RATE.ordinal())));
            arrayList.add(arrayList.get(Sorting_Fields.BAT_AVERAGE.ordinal()).thenComparing(arrayList.get(Sorting_Fields.STRIKE_RATE.ordinal())));
            arrayList.add((obj1, obj2) -> obj2.runs - obj1.runs);
            arrayList.add(arrayList.get(Sorting_Fields.MAX_RUNS.ordinal()).thenComparing(arrayList.get(Sorting_Fields.BAT_AVERAGE.ordinal())));
            return arrayList.get(stat.ordinal());
        }
        ArrayList<Comparator<BowlingDataCSV>> arrayList = new ArrayList<>();
        arrayList.add((obj1, obj2) -> (obj1.avg - obj2.avg) < 0 ? -1 : (obj1.avg - obj2.avg) > 0 ? 1 : 0);
        arrayList.add((obj1, obj2) -> (obj1.strikeRate - obj2.strikeRate) < 0 ? -1 : 1);
        return arrayList.get(stat.ordinal()-Sorting_Fields.BOWL_AVERAGE.ordinal());
    }
}
