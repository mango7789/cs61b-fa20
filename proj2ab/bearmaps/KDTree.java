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
    }

    public Point nearest(double x, double y) {

    }
}
