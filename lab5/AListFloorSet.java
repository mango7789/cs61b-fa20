/**
 * TODO: Fill in the add and floor methods.
 */
public class AListFloorSet implements Lab5FloorSet {
    AList<Double> items;

    public AListFloorSet() {
        items = new AList<>();
    }

    public void add(double x) {
        items.addLast(x);
    }

    public double floor(double x) {
        double FloorX = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < items.size(); i++) {
            double Diff = x - items.get(i);
            if (Diff <= x && Diff > FloorX) {
                FloorX = Diff;
            }
        }
        return FloorX;
    }
}
