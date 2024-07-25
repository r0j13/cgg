package cgg.a07;

import cgtools.*;

public record Background(Material material) implements Shape {

    @Override
    public Hit intersect(Ray ray) {
        double t = Double.POSITIVE_INFINITY;
        Direction normal = Vector.normalize(ray.d); // Нормаль направлена в сторону луча
        Point point = ray.pointAt(t);
        return new Hit(t, point, normal, material);
    }
}
