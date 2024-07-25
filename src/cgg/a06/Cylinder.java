package cgg.a06;

import cgtools.*;

public record Cylinder(Point pos, double rad, double height, Material mat) implements Shape {
    /*
     * Cylinder ist eine record-Klasse, die das Shape-Interface implementiert.
     * pos: Die Position des Zylinders im Raum (Mittelpunkt der Basis des
     * Zylinders).
     * rad: Der Radius des Zylinders.
     * height: Die Höhe des Zylinders.
     * mat: Das Material des Zylinders.
     */
    public Hit intersect(Ray ray) {
        // Berechnet den Vektor dir vom Ursprung des Strahls (ray.x0) zur Basis des
        // Zylinders (pos).
        Direction dir = Vector.subtract(ray.x0, pos);
        /*
         * a, b und c sind die Koeffizienten der quadratischen Gleichung, die sich aus
         * der Ray-Cylinder-Schnittformel ergeben.
         * Diese Koeffizienten berücksichtigen nur die x- und z-Komponenten, da der
         * Zylinder entlang der y-Achse unendlich lang wäre, wenn nur diese Berechnungen
         * berücksichtigt würden.
         */
        double a = ray.d.x() * ray.d.x() + ray.d.z() * ray.d.z();
        double b = 2 * (ray.d.x() * dir.x() + ray.d.z() * dir.z());
        double c = dir.x() * dir.x() + dir.z() * dir.z() - rad * rad;

        // Der Diskriminant bestimmt, ob der Strahl den Zylinder schneidet. Ein
        // negativer Diskriminant bedeutet, dass es keinen Schnittpunkt gibt.
        double discriminant = b * b - 4 * a * c;
        /*Wenn der Diskriminant negativ ist, gibt die Methode null zurück, was bedeutet, dass kein Schnittpunkt vorliegt.
t1 und t2 sind die beiden möglichen Werte für t, die die Schnittpunkte darstellen.
t ist der kleinere der beiden Werte, der innerhalb des gültigen Bereichs des Strahls liegt (ray.tmin und ray.tmax).
Wenn t nicht gültig ist, gibt die Methode null zurück. */
        if (discriminant < 0)
            return null;

        double t1 = (-b - Math.sqrt(discriminant)) / (2 * a);
        double t2 = (-b + Math.sqrt(discriminant)) / (2 * a);

        double t = (t1 >= ray.tmin && t1 <= ray.tmax) ? t1 : (t2 >= ray.tmin && t2 <= ray.tmax) ? t2 : -1;
        if (!ray.isValid(t))
            return null;
        /* Diese Prüfung stellt sicher, dass der Trefferpunkt innerhalb der Höhenbegrenzung des Zylinders liegt.
Wenn der y-Wert des Trefferpunkts außerhalb der Höhe des Zylinders liegt, gibt die Methode null zurück. */
        if (ray.pointAt(t).y() < pos.y() || ray.pointAt(t).y() > pos.y() + height) {

            return null;
        }
        // return new Hit(t, ray.pointAt(t),
        // Vector.normalize(Vector.subtract(ray.pointAt(t), pos)), mat);
        double x = Vector.normalize(Vector.divide(Vector.subtract(ray.pointAt(t), pos), rad)).x();
        double z = Vector.normalize(Vector.divide(Vector.subtract(ray.pointAt(t), pos), rad)).z();
        Direction n = new Direction(x, 0, z);
        return new Hit(t, ray.pointAt(t), n, mat);

    }
}
