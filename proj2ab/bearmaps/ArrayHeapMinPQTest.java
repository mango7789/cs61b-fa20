package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArrayHeapMinPQTest {
    ArrayHeapMinPQ<String> arrPQ = new ArrayHeapMinPQ<>();
    NaiveMinPQ<String> nvPQ = new NaiveMinPQ<>();

    @Test
    public void testPQ() {
        double min = 0.0, max = 200.0;
        Random random = new Random(328);
        for (int i = 0; i < 1000; i++) {
            double priority = min + (max - min) * random.nextDouble();
            arrPQ.add("hi" + i, priority);
            nvPQ.add("hi" + i, priority);
            if ((i + 1) % 50 == 0) {
                arrPQ.removeSmallest();
                nvPQ.removeSmallest();
                continue;
            }
            if ((i + 1) % 33 == 0) {
                String changeItem = "hi" + 27;
                double nextPriority = min + (max - min) * random.nextDouble();
                arrPQ.changePriority(changeItem, nextPriority);
                nvPQ.changePriority(changeItem, nextPriority);
            }
            assertEquals(arrPQ.size(), nvPQ.size());
            assertEquals(arrPQ.getSmallest(), nvPQ.getSmallest());
            assertTrue(arrPQ.contains("hi" + i));
        }
    }

}
