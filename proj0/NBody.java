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

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]), dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] bodies = readBodies(filename);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);

        int waitTime = 10;
        double t = 0;
        while (t < T) {
            double[] xForces = new double[5], yForces = new double[5];
            // calculate joint-forces
            for (int i = 0; i < bodies.length; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            // update
            for (int i = 0; i < bodies.length; i++) {
                bodies[i].update(dt, xForces[i], yForces[i]);
            }
            // draw
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Body body : bodies) {
                body.draw();
            }
            StdDraw.show();
            StdDraw.pause(waitTime);
            t += dt;
        }
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                        bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
        }
    }
 }
