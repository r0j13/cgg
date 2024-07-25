package cgg.a06;

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
        /* Die aktive Zeile erstellt einen Strahl, der die Transformationsmatrix M verwendet:

    Matrix.multiply(M, x0): Transformiert den Ursprungspunkt des Strahls.
    Matrix.multiply(M, d): Transformiert die Richtung des Strahls.
    Der Strahl beginnt bei 0 und geht bis Double.POSITIVE_INFINITY, was bedeutet, dass er unbegrenzt weit reicht. */
        Ray ray = new Ray(Matrix.multiply(M, x0), Matrix.multiply(M, d), 0, Double.POSITIVE_INFINITY);
        return ray;
    }
}
