package cgg.a07;

import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public record Sphere(double radius, Point point, Material material) implements Shape {



    @Override
    public Hit intersect(Ray ray) {
        Direction oc = Vector.subtract(ray.x0, point);
        double a = Vector.dotProduct(ray.d, ray.d);
        double b = 2.0 * Vector.dotProduct(oc, ray.d);
        double c = Vector.dotProduct(oc, oc) - radius * radius;
        double discriminant = b * b - 4 * a * c;

        if (discriminant > 0) {
            double t1 = (-b - Math.sqrt(discriminant)) / (2.0 * a);
            double t2 = (-b + Math.sqrt(discriminant)) / (2.0 * a);
            double t = (t1 < t2) ? t1 : t2;

            if (ray.isValid(t)) {
                Point hitPoint = ray.pointAt(t);
                Direction normal = Vector.divide(Vector.subtract(hitPoint, point), radius);
                return new Hit(t, hitPoint, normal, material);
            }
        }

        return null;
    }

    public Material getMaterial() {
        return material;
    }
}
