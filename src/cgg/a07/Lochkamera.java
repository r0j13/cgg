package cgg.a07;

import cgtools.*;

public class Lochkamera {

    double Winkel;
    double W;
    double H;
    Matrix M;

    public Lochkamera(double winkel, double w, double h, Matrix matrix) {
        Winkel = winkel;
        W = w;
        H = h;
        M = matrix;
    }
    
    public Ray createRay(double x, double y) {
        Point x0 = new Point(0,0,0);
        double dx = x - W/2;
        double dy = -1 * (y-H/2);
        double dz = -1 * ((W/2)/Math.tan(Winkel/2));
        Direction d = Vector.normalize(new Direction(dx, dy, dz));
        //Ray ray = new Ray(x0, d, 0, Double.POSITIVE_INFINITY);
        Ray ray = new Ray(Matrix.multiply(M,x0), Matrix.multiply(M, d), 0, Double.POSITIVE_INFINITY);
        return ray;
    }
}
