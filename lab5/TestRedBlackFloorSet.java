import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by hug.
 */
public class TestRedBlackFloorSet {
    @Test
    public void randomizedTest() {
       final int sizes = 1000000;
       AListFloorSet ATest = new AListFloorSet();
       RedBlackFloorSet RBTest = new RedBlackFloorSet();
       for (int i = 0; i < sizes; i++) {
           double CurrValue = StdRandom.uniform(-5000, 5000);
           ATest.add(CurrValue);
           RBTest.add(CurrValue);
       }
       double TestValue = 5.;
       double AFloor = ATest.floor(TestValue);
       double RBFloor = RBTest.floor(TestValue);
       assertEquals(AFloor, RBFloor, 0.000001);
    }
}
