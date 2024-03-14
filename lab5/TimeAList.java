import java.util.ArrayList;
import java.util.List;

/**
 * Class that collects timing information about AList construction.
 */
public class TimeAList {
    private static void printTimingTable(List<Integer> Ns, List<Double> times, List<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.print("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        int[] ArrayN = new int[]{1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};
        List<Integer> Ns = new ArrayList<>();
        List<Double> times = new ArrayList<>();
        List<Integer> opCounts = new ArrayList<>();

        for (int k : ArrayN) {
            AList<Double> TestAlist = new AList<Double>();
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < k; j++) {
                TestAlist.addLast(5.4);
            }
            double timeInSeconds = sw.elapsedTime();
            Ns.add(k);
            times.add(timeInSeconds);
            opCounts.add(k);
        }

        printTimingTable(Ns, times, opCounts);
    }


}
