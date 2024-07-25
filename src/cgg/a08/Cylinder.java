package cgg.a08;

import cgtools.*;

public record Cylinder(Point pos, double rad, double height, Material mat) implements Shape {

    public Hit intersect(Ray ray) {

        Direction dir = Vector.subtract(ray.x0, pos);

        double a = ray.d.x()* ray.d.x() + ray.d.z() * ray.d.z();
        double b = 2 * (ray.d.x() * dir.x() + ray.d.z() * dir.z());
        double c = dir.x() * dir.x() + dir.z() * dir.z() - rad * rad;

        double discriminant = b * b - 4 * a * c;

        if (discriminant < 0) return null;

        double t1 = (-b - Math.sqrt(discriminant)) / (2 * a);
        double t2 = (-b + Math.sqrt(discriminant)) / (2 * a);

        double t = (t1 >= ray.tmin && t1 <= ray.tmax) ? t1 : (t2 >= ray.tmin && t2 <= ray.tmax) ? t2 : -1;
        if (!ray.isValid(t)) return null;

        if (ray.pointAt(t).y() < pos.y() || ray.pointAt(t).y() > pos.y() + height){

            return null;
        }
        // return new Hit(t, ray.pointAt(t), Vector.normalize(Vector.subtract(ray.pointAt(t), pos)), mat);
        double x = Vector.normalize(Vector.divide(Vector.subtract(ray.pointAt(t), pos), rad)).x();
        double z = Vector.normalize(Vector.divide(Vector.subtract(ray.pointAt(t), pos), rad)).z();
        Direction n = new Direction(x, 0, z);
        return new Hit(t, ray.pointAt(t), n, mat);

    }
}
