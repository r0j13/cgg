package cgg.a05;

import cgtools.*;

public class Disc implements Shape {

    Point center;
    double radius;
    Material material;
    Direction normal;

    public Disc(Point center, double radius, Direction normal, Material material) {
        this.center = center;
        this.radius = radius;
        this.normal = normal;
        this.material = material;
    }

    @Override
    public Hit intersect(Ray ray) {
        // Berechnung des Wertes von t für den Schnittpunkt mit der Ebene
        double t = Vector.dotProduct(Vector.subtract(center, ray.x0), normal) /
                Vector.dotProduct(ray.d, normal);

        if (t <= 0) return null; // Die Scheibe befindet sich hinter dem Anfang des Strahls

        Point hitPoint = ray.pointAt(t);

        // Prüfen, ob der Schnittpunkt innerhalb des Scheibenradius liegt
        if (Vector.length(Vector.subtract(hitPoint, center)) <= radius) {
            return new Hit(t, hitPoint, normal, material);
        }

        return null;
    }
}
