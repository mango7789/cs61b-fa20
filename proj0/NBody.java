public class NBody {
    public static double readRadius(String file_name) {
        In in = new In(file_name);
        int N = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Body[] readBodies(String file_name) {
        In in = new In(file_name);
        int N = in.readInt(), size = 0;
        double radius = in.readDouble();
        Body[] output = new Body[5];
        while (!in.isEmpty() && size != 5) {
            double xp = in.readDouble();
            double yp = in.readDouble();
            double xv = in.readDouble();
            double yv = in.readDouble();
            double mass = in.readDouble();
            String path = in.readString();
            output[size++] = new Body(xp, yp, xv, yv, mass, path);
        }
        return output;
    }
 }
