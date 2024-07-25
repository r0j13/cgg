package cgg.a03;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;
import static cgtools.Vector.direction;

public class Sphere {

    double Radius;
    double PositionX; // Position für Kugel im Raum
    double PositionY;
    double PositionZ;
    Color Farbe;

    // Initialisiert die Kugel mit den angegebenen Parametern radius, positionX,
    // positionY, positionZ und farbe.
    public Sphere(double radius, double positionX,
            double positionY, double positionZ, Color farbe) {
        Radius = radius;
        PositionY = positionY;
        PositionX = positionX;
        PositionZ = positionZ;
        Farbe = farbe;
    }

    // ray: Ein Ray-Objekt, das den Strahl repräsentiert, der mit der Kugel
    // geschnitten wird
    // intersect :  Bestimmung des Schnittpunkts einer Kugel mit einem Strahl vor.
    public Hit intersect(Ray ray) {
        // minC: Der Vektor, der den Mittelpunkt der Kugel repräsentiert.

        Direction minC = direction(this.PositionX, this.PositionY, this.PositionZ);

        // newX0: Der Vektor vom Anfang des Strahls (ray.x0) zum Mittelpunkt der Kugel.
        Point newX0 = Vector.subtract(ray.x0, minC);
        double a = Vector.dotProduct(ray.d, ray.d);
        double b = 2 * Vector.dotProduct(newX0, ray.d); // Schnittpunktberechnung
        double c = Vector.dotProduct(newX0, newX0)
                - this.Radius * this.Radius;

        Direction norm;

        double Wurz = b * b - 4 * a * c;

        if (Wurz < 0) {
            return null;
        }

        // tmin und tmax: Die beiden Lösungen der quadratischen Gleichung, die die
        // Abstände vom Strahlanfang zu den Schnittpunkten darstellen.
        double tmin = 0;
        double tmax = 0;

        /*
         * Hier wird geprüft, ob die Diskriminante (Wurz) der quadratischen Gleichung
         * gleich null ist. cgtools.Util.isZero(Wurz) ist eine Hilfsfunktion, die
         * überprüft, ob Wurz praktisch null ist. Wenn die Diskriminante null ist,
         * bedeutet dies, dass die quadratische Gleichung genau eine Lösung hat, was
         * darauf hinweist, dass der Strahl die Kugel tangential berührt (es gibt nur
         * einen Schnittpunkt).
         */
        if (cgtools.Util.isZero(Wurz)) {
            /*
             * Da es nur einen Schnittpunkt gibt, kann dieser mit der Formel −b/2a
             * berechnet werden. Dies ist eine vereinfachte Version der allgemeinen Lösung
             * der quadratischen Gleichung, die gilt, wenn die Diskriminante null ist.
             */
            tmin = -b / (2 * a);
            norm = Vector.divide(Vector.subtract(ray.pointAt(tmin), // ray.pointAt(tmin) berechnet den Punkt im Raum, an
                                                                    // dem der Strahl den Schnittpunkt tmin erreicht.
                    new Point(this.PositionX, this.PositionY, this.PositionZ)), Radius);
            return new Hit(tmin, ray.pointAt(tmin), norm, this.Farbe);
        }

        /*
         * tmin und tmax sind die beiden Lösungen der quadratischen Gleichung und
         * repräsentieren die Schnittpunkte des Strahls mit der Kugel.
         * tmin entspricht dem weiter entfernten Schnittpunkt, da der Vorzeichenwechsel
         * positiv ist.
         * tmax entspricht dem näheren Schnittpunkt, da der Vorzeichenwechsel negativ
         * ist.
         */
        tmin = (-b + Math.sqrt(Wurz)) / (2 * a);
        tmax = (-b - Math.sqrt(Wurz)) / (2 * a);

        if (ray.isValid(tmin) && ray.isValid(tmax)) { // Hier wird überprüft, ob tmin gültig ist, das heißt, ob tmin
                                                      // innerhalb der gültigen Reichweite des Strahls liegt.
            if (tmin < tmax) { // Wenn tmin kleiner ist als tmax, bedeutet das, dass der erste Schnittpunkt
                               // (tmin) näher am Strahlanfang liegt als der zweite Schnittpunkt (tmax).

                /*
                 * Die Normale am Schnittpunkt wird berechnet, indem der Punkt des Strahls bei
                 * tmin vom Mittelpunkt der Kugel subtrahiert
                 * und durch den Radius der Kugel geteilt wird.
                 * Diese Normale wird zusammen mit dem Schnittpunkt (ray.pointAt(tmin)) und der
                 * Farbe der Kugel in einem Hit-Objekt zurückgegeben.
                 */
                norm = Vector.divide(Vector.subtract(ray.pointAt(tmin),
                        new Point(this.PositionX, this.PositionY, this.PositionZ)), Radius);

                return new Hit(tmin, ray.pointAt(tmin), norm, this.Farbe);
            } else {
                norm = Vector.divide(Vector.subtract(ray.pointAt(tmax),
                        new Point(this.PositionX, this.PositionY, this.PositionZ)), Radius);
                return new Hit(tmax, ray.pointAt(tmax), norm, this.Farbe);
            }
        } else if (ray.isValid(tmin) || ray.isValid(tmax)) { // Hier wird überprüft, ob entweder tmin oder tmax gültig
                                                             // ist. Dies bedeutet, dass zumindest einer der beiden
                                                             // Schnittpunkte innerhalb der gültigen Reichweite des
                                                             // Strahls liegt.
            if (ray.isValid(tmin)) {
                norm = Vector.divide(Vector.subtract(ray.pointAt(tmin),
                        new Point(this.PositionX, this.PositionY, this.PositionZ)), Radius);
                return new Hit(tmin, ray.pointAt(tmin), norm, this.Farbe);
            } else {
                norm = Vector.divide(Vector.subtract(ray.pointAt(tmax),
                        new Point(this.PositionX, this.PositionY, this.PositionZ)), Radius);
                return new Hit(tmax, ray.pointAt(tmax), norm, this.Farbe);
            }

        } else { // Wenn weder tmin noch tmax gültig sind, wird null zurückgegeben, was bedeutet,
                 // dass der Strahl die Kugel nicht schneidet.
            return null;
        }

    }

    public Color getFarbe() {
        return Farbe;
    }

}
