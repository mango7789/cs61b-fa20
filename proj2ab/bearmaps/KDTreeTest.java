package bearmaps;

import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    Point p1 = new Point(1.1, 2.2);
    Point p2 = new Point(3.3, 4.4);
    Point p3 = new Point(-2.9, 4.2);

    NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
    KDTree kdt = new KDTree(List.of(p1, p2, p3));

    @Test
    public void testNearest() {
        double min = 0.0;
        double max = 5.0;
        Random random = new Random();
        // generated one hundred tuples of random numbers to test
        for (int i = 0; i < 100; i++) {
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
