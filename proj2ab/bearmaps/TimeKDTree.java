package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TimeKDTree {
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
        final int ArrayLength = 7;
        final int ops = 1000000;
        int[] ArrayN = new int[ArrayLength];
        for (int i = 0; i < ArrayLength; i++) {
            ArrayN[i] = (int) (31250 * Math.pow(2, i));
        }
        List<Integer> Ns = new ArrayList<>();
        List<Double> times = new ArrayList<>();
        List<Integer> opCounts = new ArrayList<>();

        for (int k : ArrayN) {
            KDTree kdt = KDTreeConstruct(k);
            Stopwatch sw = new Stopwatch();
            // test insert
            double min = 0.0;
            double max = 5.0;
            Random random = new Random();
            for (int i = 0; i < ops; i++) {
                double x = min + (max - min) * random.nextDouble();
                double y = min + (max - min) * random.nextDouble();
                Point kdtNear = kdt.nearest(x, y);
            }
            double timeInSeconds = sw.elapsedTime();
            // add result to list
            Ns.add(k);
            times.add(timeInSeconds);
            opCounts.add(ops);
        }

        printTimingTable(Ns, times, opCounts);
    }

    private static KDTree KDTreeConstruct(int k) {
        // create the KDTree
        return new KDTree(GeneratePoints(k));
    }

    private static NaivePointSet PointSetConstruct(int k) {
        return new NaivePointSet(GeneratePoints(k));
    }

    private static List<Point> GeneratePoints(int k) {
        // init array
        List<Point> points = new ArrayList<>(k);
        // random number
        double min = 0.0;
        double max = 1000.0;
        Random random = new Random();
        // generate points
        for (int i = 0; i < k; i++) {
            double x = min + (max - min) * random.nextDouble();
            double y = min + (max - min) * random.nextDouble();
            Point point = new Point(x, y);
            points.add(i, point);
        }
        return points;
    }
}


