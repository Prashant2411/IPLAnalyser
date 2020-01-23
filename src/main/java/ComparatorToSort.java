import java.util.ArrayList;
import java.util.Comparator;

public class ComparatorToSort {

    ArrayList<Comparator<CricketDataDAO>> arrayList = new ArrayList<>();

    public enum Sorting_Fields {
        BATTING_AVERAGE, STRIKE_RATE, COMPARE_6s_4s, STRIKE_RATE_4S_6S, STRIKE_RATE_AVERAGE, MAX_RUNS, MAX_RUNS_AVERAGES,
        BOWLING_AVERAGE, ECONOMY_RATE, COMPARE_5W_AND_4W, STRIKE_5W_4W, WICKET, WICKET_AVERAGE, BATTING_BOWLING_AVERAGE,
        BEST_ALLROUNDER
    }

    public Comparator<CricketDataDAO> getComparator(Sorting_Fields stat) {
        arrayList.add((obj1, obj2) -> obj1.battingAvg - obj2.battingAvg < 0 ? 1 : obj1.battingAvg - obj2.battingAvg > 0 ? -1 : 0);
        arrayList.add((obj1, obj2) -> (obj1.strikeRate - obj2.strikeRate) > 0 ? -1 : 1);
        arrayList.add((obj1, obj2) -> ((obj2.sixes * 6 + obj2.fours * 4) - (obj1.sixes * 6 + obj1.fours * 4)));
        arrayList.add(arrayList.get(Sorting_Fields.COMPARE_6s_4s.ordinal()).thenComparing(arrayList.get(Sorting_Fields.STRIKE_RATE.ordinal())));
        arrayList.add(arrayList.get(Sorting_Fields.BATTING_AVERAGE.ordinal()).thenComparing(arrayList.get(Sorting_Fields.STRIKE_RATE.ordinal())));
        arrayList.add((obj1, obj2) -> obj2.runs - obj1.runs);
        arrayList.add(arrayList.get(Sorting_Fields.MAX_RUNS.ordinal()).thenComparing(arrayList.get(Sorting_Fields.BATTING_AVERAGE.ordinal())));

        arrayList.add((obj1, obj2) -> obj1.bowlingAvg - obj2.bowlingAvg < 0 ? 1 : obj1.bowlingAvg - obj2.bowlingAvg > 0 ? -1 : 0);
        arrayList.add((obj1, obj2) -> obj1.economyRate - obj2.economyRate < 0 ? 1 : obj1.economyRate - obj2.economyRate > 0 ? -1 : 0);
        arrayList.add(Comparator.comparingInt(obj -> (obj.fiveWicket * 5 + obj.fourWicket * 4)));
        arrayList.add(arrayList.get(Sorting_Fields.COMPARE_5W_AND_4W.ordinal()).thenComparing(arrayList.get(Sorting_Fields.STRIKE_RATE.ordinal())));
        arrayList.add(Comparator.comparingInt(obj -> obj.wickets));
        arrayList.add(arrayList.get(Sorting_Fields.WICKET.ordinal()).thenComparing(arrayList.get(Sorting_Fields.BOWLING_AVERAGE.ordinal())));

        arrayList.add(arrayList.get(Sorting_Fields.BATTING_AVERAGE.ordinal()).thenComparing(arrayList.get(Sorting_Fields.BOWLING_AVERAGE.ordinal()).reversed()));
        Comparator<CricketDataDAO> comp =Comparator.comparing(allRounder -> {
            if (allRounder.wickets > 7 && allRounder.runs > 150)
                return allRounder.runs + allRounder.wickets*20;
            return 0;
        });
        arrayList.add(comp.reversed());
        return arrayList.get(stat.ordinal());
    }
}
