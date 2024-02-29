public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Body(Body b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        double dist = Math.sqrt(Math.pow(this.xxPos - b.xxPos, 2) + Math.pow(this.yyPos - b.yyPos, 2)); 
        return dist;
    }

    public double calcForceExertedBy(Body b) {
        if (this.equals(b)) {
            return 0;
        } else {
            double dist = calcDistance(b);
            double G = 6.67 * 1e-11;
            double f = G * this.mass * b.mass / Math.pow(dist, 2);
            return f;
        }
    }

    public double calcForceExertedByX(Body b) {
        double total_f = this.calcForceExertedBy(b);
        double dx = b.xxPos - this.xxPos;
        double dist = this.calcDistance(b);
        double f_x = total_f * dx / dist;
        return f_x;
    }

    public double calcForceExertedByY(Body b) {
        double total_f = this.calcForceExertedBy(b);
        double dy = b.yyPos - this.yyPos;
        double dist = this.calcDistance(b);
        double f_y = total_f * dy / dist;
        return f_y;
    }

    public double calcNetForceExertedByX(Body[] b) {
        double forces_x = 0;
        for(int i = 0; i < b.length; i++) {
            Body curr_body = b[i];
            if (this.equals(curr_body)) {
                continue;
            } else {
                forces_x += this.calcForceExertedByX(curr_body);
            }
        }
        return forces_x;
    }

    public double calcNetForceExertedByY(Body[] b) {
        double forces_y = 0;
        for(int i = 0; i < b.length; i++) {
            Body curr_body = b[i];
            if (this.equals(curr_body)) {
                continue;
            } else {
                forces_y += this.calcForceExertedByY(curr_body);
            }
        }
        return forces_y;
    }

    public void update(double t, double force_x, double force_y) {
        double a_x = force_x / this.mass, a_y = force_y / this.mass;
        this.xxVel += t * a_x;
        this.yyVel += t * a_y;
        this.xxPos += t * this.xxVel;
        this.yyPos += t * this.yyVel;
    }
    
    public void draw() {
        String image_path = "images/" + this.imgFileName;
        StdDraw.picture(this.xxPos, this.yyPos, image_path);
    }
}
