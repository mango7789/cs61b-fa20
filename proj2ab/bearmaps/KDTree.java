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
        Collections.shuffle(points);
        this.head = new Node(points.getFirst(), 0);
        // insert other points
        for (int i = 1; i < points.size(); i++) {
            Point CurrPoint = points.get(i);
            insert(CurrPoint);
        }
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
