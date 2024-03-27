package bearmaps;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class KDTree {
    private Node head;
    private final int DIM = 2;
    private class Node{
        private Point point;
        private Node Left;
        private Node Right;
        private int depth; // if true, this is red, else it's blue

        public Node(Point NewPoint, int depth) {
            this.point = NewPoint;
            this.depth = depth;
        }

        public Node left() { return Left; }
        public Node right() { return Right; }
        public Point point() { return point; }
        public int depth() { return depth; }
        public void setPoint(Point newpoint) {
            point = newpoint;
        }
        public void setLeft(Node left) {
            Left = left;
        }
        public void setRight(Node right) {
            Right = right;
        }
        public void setState(int depth) {
            this.depth = depth;
        }
    }

    public KDTree(List<Point> points) {
        if (points.isEmpty()) {
            throw new IllegalStateException("The points list can't be null!");
        }
        this.head = new Node(points.getFirst(), 0);
        // insert other points
        for (int i = 1; i < points.size(); i++) {
            Point CurrPoint = points.get(i);
            insert(CurrPoint);
        }
//        this.head = buildBalancedKDTree(points, 0, points.size() - 1, true);
    }

    private Node buildBalancedKDTree(List<Point> points, int start, int end, int depth) {
        if (start > end) {
            return null;
        }
        // Choose the axis based on state
        int axis = depth % DIM == 0 ? 0 : 1;
        int medianIndex = selectMedian(points, start, end, axis);
        Point medianPoint = points.get(medianIndex);

        Node node = new Node(medianPoint, depth);

        node.setLeft(buildBalancedKDTree(points, start, medianIndex - 1, depth + 1));
        node.setRight(buildBalancedKDTree(points, medianIndex + 1, end, depth + 1));

        return node;
    }
    private int selectMedian(List<Point> points, int start, int end, int axis) {
        if (start == end) {
            return start;
        }
        // Use median-of-medians algorithm to select pivot
        int groupSize = 5;
        for (int i = start; i <= end; i += groupSize) {
            int groupEnd = Math.min(i + groupSize - 1, end);
            int medianIndex = partition(points, i, groupEnd, axis);
            swap(points, medianIndex, start + (i - start) / groupSize);
        }

        int numMedians = (end - start + groupSize) / groupSize;
        int medianIndex = start + (numMedians - 1) / 2 * groupSize;
        return partition(points, start, start + numMedians - 1, axis);
    }

    private int partition(List<Point> points, int start, int end, int axis) {
        int pivotIndex = start + (end - start) / 2;
        Point pivot = points.get(pivotIndex);
        swap(points, pivotIndex, end);
        int i = start - 1;
        for (int j = start; j < end; j++) {
            if (compare(points.get(j), pivot, axis) < 0) {
                i++;
                swap(points, i, j);
            }
        }
        swap(points, i + 1, end);
        return i + 1;
    }

    private double compare(Point curr, Point pivot, int axis) {
        if (axis == 0) {
            return curr.getX() - pivot.getX();
        }
        else {
            return curr.getY() - pivot.getY();
        }
    }

    private void swap(List<Point> points, int i, int j) {
        Point temp = points.get(i);
        points.set(i, points.get(j));
        points.set(j, temp);
    }

    private void insert(Point point) {
        Node current = this.head;
        Node parent = null;
        int depth = 0;
        boolean direction = true;

        while (current != null) {
            parent = current;
            depth = current.depth();
            double CurrVal; double CmpVal;

            // if the depth is even
            if (depth % DIM == 0) {
                CurrVal = current.point().getX();
                CmpVal = point.getX();
            }
            // if the depth is odd
            else {
                CurrVal = current.point().getY();
                CmpVal = point.getY();
            }

            if (CmpVal >= CurrVal) {
                current = current.right();
                direction = true;
            } else {
                current = current.left();
                direction = false;
            }
        }
        // insert into the k-d tree
        Node NewNode = new Node(point, depth + 1);
        if (direction) {
            parent.setRight(NewNode);
        } else {
            parent.setLeft(NewNode);
        }

    }

    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);
        return nearest(head, goal, head).point();
    }

    private Node nearest(Node n, Point goal, Node best) {
        if (n == null) {
            return best;
        }
        if (Point.distance(n.point(), goal) < Point.distance(best.point(), goal)) {
            best = n;
        }
        // find the goodside with a smaller vertical/horizontal distance
        Node goodSide = null;
        Node badSide = null;
        if (compare(goal, n)) {
            goodSide = n.left();
            badSide = n.right();
        }
        else {
            goodSide = n.right();
            badSide = n.left();
        }
        // recursively update the best node
        best = nearest(goodSide, goal, best);
        // compare the distance of best dist and absoult dist to spliting plane
        double distToSplitPlane = distPlane(goal, n);
        double bestDist = Point.distance(best.point(), goal);
        if (distToSplitPlane < bestDist) {
            best = nearest(badSide, goal, best);
        }
        return best;
    }

    private boolean compare(Point goal, Node n) {
        Point CurrPoint = n.point();
        if (n.depth() % DIM == 0) {
            return goal.getX() < CurrPoint.getX();
        }
        else {
            return goal.getY() < CurrPoint.getY();
        }
    }

    private double distPlane(Point goal, Node n) {
        if (n.depth() % DIM == 0) {
            return Math.abs(goal.getX() - n.point().getX());
        }
        return Math.abs(goal.getY() - n.point().getY());
    }
}
