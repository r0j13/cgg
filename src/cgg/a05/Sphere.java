package cgg.a05;

import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public record Sphere(double radius, double positionX, double positionY, double positionZ, Material material)
        implements Shape {

    @Override
    public Hit intersect(Ray ray) {
        Direction oc = Vector.subtract(ray.x0, new Point(positionX, positionY, positionZ));
        double a = Vector.dotProduct(ray.d, ray.d);
        double b = 2.0 * Vector.dotProduct(oc, ray.d);
        double c = Vector.dotProduct(oc, oc) - radius * radius;
        // Der Diskriminant bestimmt, ob der Strahl die Kugel schneidet. Ein positiver
        // Diskriminant bedeutet, dass es zwei Schnittpunkte gibt.
        double discriminant = b * b - 4 * a * c;
        /*Wenn der Diskriminant positiv ist, werden die beiden Schnittpunkte t1 und t2 berechnet.
        t ist der kleinere der beiden Werte, was bedeutet, dass der vordere Schnittpunkt gewählt wird. */
        if (discriminant > 0) {
            double t1 = (-b - Math.sqrt(discriminant)) / (2.0 * a);
            double t2 = (-b + Math.sqrt(discriminant)) / (2.0 * a);
            double t = (t1 < t2) ? t1 : t2;
            /*ray.isValid(t) prüft, ob t im gültigen Bereich des Strahls liegt.
    hitPoint ist der Punkt, an dem der Strahl die Kugel trifft.
    normal ist der normale Vektor an der Trefferstelle.
    Ein Hit-Objekt wird mit den Informationen über den Treffer zurückgegeben. */
            if (ray.isValid(t)) {
                Point hitPoint = ray.pointAt(t);
                Direction normal = Vector.divide(Vector.subtract(hitPoint, new Point(positionX, positionY, positionZ)),
                        radius);
                return new Hit(t, hitPoint, normal, material);
            }
        }

        return null;
    }

    public Material getMaterial() {
        return material;
    }
}
