import java.util.ArrayList;
import java.util.List;

/**
 * Class that collects timing information about SLList getLast method.
 */
public class TimeSLList {
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
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        final int rows = 8;
        final int ops = 10000;
        ArrayList<Integer> Ns = new ArrayList<>();
        ArrayList<Double> times = new ArrayList<>();
        ArrayList<Integer> opCounts = new ArrayList<>();

        for (int row = 0; row < rows; row++) {
            int N = (int) Math.pow(2, (row + 1)) * 1000;
            Ns.add(N);
            SLList<Double> TestSLLlist = new SLList<>();

            for (int n = 0; n < N; n++) {
                TestSLLlist.addLast(3.14);
            }

            Stopwatch sw = new Stopwatch();
            for (int time = 0; time < ops; time++) {
                TestSLLlist.getLast();
            }
            double timeElapse = sw.elapsedTime();
            times.add(timeElapse);
            opCounts.add(ops);
        }

        printTimingTable(Ns, times, opCounts);
    }

}
