package cgg.a10;

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
        // Вычисляем значение t для пересечения с плоскостью
        double t = Vector.dotProduct(Vector.subtract(center, ray.x0), normal) /
                Vector.dotProduct(ray.d, normal);

        if (t <= 0) return null; // Диск находится позади начала луча

        Point hitPoint = ray.pointAt(t);

        // Проверяем, находится ли точка пересечения внутри радиуса диска
        if (Vector.length(Vector.subtract(hitPoint, center)) <= radius) {
            return new Hit(t, hitPoint, normal, material);
        }

        return null;
    }
}
