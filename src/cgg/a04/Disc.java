package cgg.a04;

import cgtools.*;

public class Disc implements Shape {

    Point Ankerpunkt;
    double radius;
    Color color;
    Direction normal;

    public Disc(Point ankerpunkt, double radius, Direction normal, Color color) {
        this.Ankerpunkt = ankerpunkt; // Ein Point-Objekt, das den Mittelpunkt der Scheibe darstellt.
        this.radius = radius; // Wert, der den Radius der Scheibe angibt.
        this.normal = normal; // Ein Direction-Objekt, das die Normalenrichtung der Scheibe angibt.
        this.color = color;

    }

    @Override // Diese Methode überschreibt die intersect-Methode des Shape-Interfaces und
              // berechnet den Schnittpunkt eines Strahls mit der Scheibe.
    public Hit intersect(Ray ray) {
        // t=((p-x0)*norm)/
        // (d*norm)

        /*
         * Berechnet das Skalarprodukt zwischen dem Vektor vom Strahlursprung
         * ray.x0 zum Ankerpunkt der Scheibe und der Normalenrichtung normal.
         */
        double t = (Vector.dotProduct(Vector.subtract(Ankerpunkt, ray.x0), normal)) /

                (Vector.dotProduct(ray.d, normal)); // Berechnet das Skalarprodukt zwischen der Richtung des Strahls
                                                    // ray.d und der Normalenrichtung normal.

        // Der Parameter t gibt an, wie weit entlang des Strahls der Schnittpunkt liegt.
        // Er wird durch Division des Numerators durch den Denominator berechnet.
        Point DiscPoint = ray.pointAt(t);

        // Wenn t kleiner oder gleich null ist, bedeutet das, dass der Schnittpunkt
        // hinter dem Strahlursprung liegt, und somit wird null zurückgegeben.
        if (t <= 0)
            return null;

        /*
         * Diese Formel berechnet das Quadrat des Abstands zwischen dem Punkt DiscPoint
         * und dem Ankerpunkt der Scheibe.
         */
        if (Math.pow(DiscPoint.x() - Ankerpunkt.x(), 2) + Math.pow(DiscPoint.y() - Ankerpunkt.y(), 2)
                + Math.pow(DiscPoint.z() - Ankerpunkt.z(), 2) <= radius) {
            return new Hit(t, DiscPoint, normal, color);
        }
        return null; // Wenn der Punkt außerhalb des Radius liegt, wird null zurückgegeben.
    }

}
