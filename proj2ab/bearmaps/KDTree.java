package bearmaps;

import java.util.List;

public class KDTree {
    private Node head;

    private class Node{
        private Point point;
        private Node Left;
        private Node Right;
        private boolean state; // if true, this is red, else it's blue

        public Node(Point NewPoint, boolean state) {
            point = NewPoint;
            this.state = state;
        }

        public Node left() { return Left; }
        public Node right() { return Right; }
        public Point point() { return point; }
        public boolean state() { return state; }
        public void setPoint(Point newpoint) {
            point = newpoint;
        }
        public void setLeft(Node left) {
            Left = left;
        }
        public void setRight(Node right) {
            Right = right;
        }
        public void setState(boolean state) {
            this.state = state;
        }
    }

    public KDTree(List<Point> points) {
        if (points.isEmpty()) {
            throw new IllegalStateException("The points list can't be null!");
        }
        this.head = new Node(points.getFirst(), true);
        for (int i = 1; i < points.size(); i++) {
            Point CurrPoint = points.get(i);
            insert(CurrPoint);
        }
    }
    private void insert(Point point) {
        Node current = this.head;
        Node parent = null;
        boolean ParentState = true;
        boolean direction = true; // 0=left, 1=right

        while (current != null) {
            parent = current;
            ParentState = current.state();
            double CurrVal; double CmpVal;

            // if the depth is odd
            if (ParentState) {
                CurrVal = current.point().getX();
                CmpVal = point.getX();
            }
            // if the depth is even
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
        Node NewNode = new Node(point, !ParentState);
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
        best = nearest(goodSide, goal, best);
        best = nearest(badSide, goal, best);
        return best;
    }

    private boolean compare(Point goal, Node n) {
        Point CurrPoint = n.point();
        boolean state = n.state();
        if (state) {
            return goal.getX() < CurrPoint.getX();
        }
        else {
            return goal.getY() < CurrPoint.getY();
        }
    }
}
