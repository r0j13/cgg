package cgg.a05;

import cgtools.*;
import static cgtools.Vector.color;

public record EmitterMaterial(Color emission) implements Material {

    /*
     * @Override
     * public Color emittedRadiance(Ray ray, Hit hit) {
     * return this.emitColor;
     * }
     * 
     * @Override
     * public Ray scatteredRay(Ray ray, Hit hit) {
     * double x = Random.random(); // *2-1
     * double y = Random.random(); //
     * double z = Random.random(); //
     * 
     * while(x*x + y*y + z*z > 1) {
     * x = Random.random(); //
     * y = Random.random(); //
     * z = Random.random(); //
     * }
     * 
     * Direction direction = Vector.normalize(Vector.add(hit.getN(),
     * Vector.direction(x, y, z)));
     * return new Ray(hit.x, direction, 0.01, Double.POSITIVE_INFINITY);
     * }
     * 
     * @Override
     * public Color attenuation(Ray ray, Hit hit) {
     * return Vector.black; // Нет ослабления
     * }
     */

    @Override
    public Color albedos() {
        return null;
    }

    @Override
    public boolean gestreut() {
        return false;
    }

    @Override
    public Ray reflecRay(Ray ray, Hit hit) {
        return null;
    }

}

/*
 * Die albedos-Methode gibt null zurück, da ein Emitter-Material keine
 * Reflexionsfarbe hat. Albedo ist nicht relevant für emittierende Materialien.
 * Die gestreut-Methode gibt false zurück, da ein Emitter-Material kein Licht streut.
 * 
 */