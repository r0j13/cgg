package cgg.a06;

import cgtools.*;

public record Background(Material material) implements Shape {

    @Override
    public Hit intersect(Ray ray) {
        double t = Double.POSITIVE_INFINITY;
        Direction normal = Vector.normalize(ray.d); // Die Normale ist auf den Strahl gerichtet
        Point point = ray.pointAt(t);
        return new Hit(t, point, normal, material);
    }
}
