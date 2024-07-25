package cgg.a04;

import cgtools.*;

public class Lochkamera {

    double Winkel;
    double W;
    double H;

    public Lochkamera(double winkel, double w, double h) {
        Winkel = winkel;
        W = w;
        H = h;
    }
    
    public Ray createRay(double x, double y) {
        Point x0 = new Point(0,0,0);
        double dx = x - W/2;
        double dy = -1 * (y-H/2);
        double dz = -1 * ((W/2)/Math.tan(Winkel/2));
        Direction d = Vector.normalize(new Direction(dx, dy, dz));
        Ray ray = new Ray(x0, d, 0, Double.POSITIVE_INFINITY);
        return ray;
    }
}

// Achtung: die 2D-y-Koordinate in der Bildebene läuft von oben nach unten