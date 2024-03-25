package bearmaps;

import java.util.ArrayList;
import java.util.List;

public class NaivePointSet implements PointSet{

    private List<Point> Points;

    public NaivePointSet(List<Point> points) {
        Points = new ArrayList<>(points);
    }

    @Override
    public Point nearest(double x, double y) {
        Point CurrPoint = new Point(x, y);
        double Dist = Double.POSITIVE_INFINITY; Point NearestNode = null;
        for (Point point : Points) {
            double CurrDist = Point.distance(point, CurrPoint);
            if (CurrDist <= Dist) {
                Dist = CurrDist;
                NearestNode = point;
            }
        }
        return NearestNode;
    }
}
