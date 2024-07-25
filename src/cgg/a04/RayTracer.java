package cgg.a04;

import cgtools.*;
import java.util.ArrayList;

//implementeirt Sampler
//getColor
public class RayTracer implements Sampler {
    private Lochkamera camera;
    private Group group;

    public RayTracer(Lochkamera camera, Group group) {
        this.camera = camera;
        this.group = group;
    }

    @Override
    public Color getColor(double x, double y) {
        Color sphereColor = Vector.add(Vector.black, Vector.gray);
        double tmax = Double.POSITIVE_INFINITY;
        Ray ray = camera.createRay(x, y);
        Hit H = group.intersect(ray);
        sphereColor = shade(H.getN(), H.getC());

        return sphereColor;

    }

    static Color shade(Direction normal, Color color) {
        Direction lightDir = Vector.normalize(Vector.direction(1, 1, 0.5));
        Color ambient = Vector.multiply(0.1, color);
        Color diffuse = Vector.multiply(0.9 * Math.max(0, Vector.dotProduct(lightDir, normal)), color);
        return Vector.add(ambient, diffuse);
    }

    public void test(Sphere sphere) {
        if (sphere.getFarbe() != Vector.red) {
            System.out.println(sphere.getFarbe().toString());
        }
    }

}
