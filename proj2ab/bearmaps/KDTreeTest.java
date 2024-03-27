package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    private final int k = 1000;

    private List<Point> generatePoints() {
        List<Point> points = new ArrayList<>();
        double min = -1000.0;
        double max = 1000.0;
        Random random = new Random();
        for (int i = 0; i < k; i++) {
            double x = min + (max - min) * random.nextDouble();
            double y = min + (max - min) * random.nextDouble();
            points.add(new Point(x, y));
        }
        return points;
    }

    @Test
    public void testNearest() {
        // generate points
        List<Point> points = generatePoints();
        NaivePointSet nn = new NaivePointSet(points);
        KDTree kdt = new KDTree(points);
        // set random range for the test cases
        double min = -1000.0;
        double max = 1000.0;
        Random random = new Random();
        // generated one hundred tuples of random numbers to test
        for (int i = 0; i < 1000; i++) {
            double x = min + (max - min) * random.nextDouble();
            double y = min + (max - min) * random.nextDouble();
            Point testPoint = new Point(x, y);
            Point nnNear = nn.nearest(x, y);
            Point kdtNear = kdt.nearest(x, y);
            double nnDist = Point.distance(testPoint, nnNear);
            double kdtDist = Point.distance(testPoint, kdtNear);
            assertEquals(nnDist, kdtDist, 0.0001);
        }
    }

}
