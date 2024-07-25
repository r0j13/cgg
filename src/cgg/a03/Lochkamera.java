package cgg.a03;

import cgtools.*;

public class Lochkamera {

    double Winkel; // Blickwinkel
    double W;
    double H;

    public Lochkamera(double winkel, double w, double h) {
        Winkel = winkel;
        W = w;
        H = h;
    }

    // *Die Methode "createRay" nimmt zwei Parameter entgegen, x- und y-Koordinaten,
    // die einen Punkt auf der Bildebene darstellen.
    public Ray createRay(double x, double y) {
        Point x0 = new Point(0, 0, 0); // Innerhalb dieser Methode wird ein neues Punktobjekt mit den Koordinaten (0,
                                       // 0, 0) erstellt.
        /*
         * Dann wird mithilfe einiger mathematischer Berechnungen, die Trigonometrie und
         * Vektornormalisierung beinhalten, ein Richtungsobjekt erstellt, das die
         * Richtung des Lichts darstellt,
         * das von diesem Punkt auf der Bildebene zu unserer virtuellen Kamera kommt.
         * Schließlich werden alle diese Objekte verwendet, um ein Ray-Objekt zu
         * erstellen, das für die Strahlenverfolgung verwendet wird.
         * Der Strahl hat seinen Ursprung im Punkt x0 und erstreckt sich unendlich weit
         * in die angegebene Richtung d.
         */
        double dx = x - W / 2;
        double dy = -1 * (y - H / 2);
        double dz = -1 * ((W / 2) / Math.tan(Winkel / 2));
        Direction d = Vector.normalize(new Direction(dx, dy, dz));
        Ray ray = new Ray(x0, d, 0, Double.POSITIVE_INFINITY);
        return ray;
    }
}
