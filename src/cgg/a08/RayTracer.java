package cgg.a08;

import cgtools.*;
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
        Ray ray = camera.createRay(x, y);
        return calculateRadiance(group, ray, 5);
    }

    private Color calculateRadiance(Shape scene, Ray ray, int depth) {
        if (depth == 0) {
            return new Color(0, 0, 0);
        }

        //Berechnung des Schnittpunkts zwischen Strahl und Szene.
        Hit intersect = scene.intersect(ray);
        //Berechnung der emittierten Strahldichte.
        Material mat = intersect.material();
        Ray newRay = mat.reflecRay(ray, intersect);
        if(newRay != null){
            // return Vector.asColor(hit.n());
            return Vector.multiply(shade(intersect.n(),mat),calculateRadiance(scene, newRay, depth - 1));
        }
        else{
            // return Vector.asColor(hit.n());
            return mat.emission();
        }
    }

     static Color shade(Direction normal, Material material) {
        Direction lightDir = Vector.normalize(Vector.direction(1, 1, 0.5));
         Color ambient = Vector.multiply(0.1, material.albedos());
         Color diffuse = Vector.multiply(0.9 * Math.max(0, Vector.dotProduct(lightDir, normal)), material.albedos());
         return Vector.add(ambient, diffuse);
     }

}
