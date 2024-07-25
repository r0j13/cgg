package cgg.a04;

import cgtools.*;
import static cgtools.Vector.gray30;

public class Background implements Shape { // einen Hintergrund, der eine konstante Farbe hat.

    Color constColor;

    public Background(Color constColor) {
        this.constColor = constColor;
    }

    @Override // Diese Methode überschreibt die intersect-Methode des Shape-Interfaces und
              // berechnet den Schnittpunkt eines Strahls mit dem Hintergrund.
    public Hit intersect(Ray ray) {
        double t = Double.POSITIVE_INFINITY; /*
                                              * t wird auf Double.POSITIVE_INFINITY gesetzt. Das bedeutet,
                                              * dass der Hintergrund immer als getroffen betrachtet wird,
                                              * da der Strahl theoretisch unendlich weit reisen kann und immer den
                                              * Hintergrund trifft.
                                              */

        Direction normal = Vector.direction(0, 0, 1); /*
                                                       * normal wird auf den Vektor (0, 0, 1) gesetzt, was bedeutet,
                                                       * dass die Normale des Hintergrunds immer nach oben zeigt.
                                                       * 
                                                       * Hintergrund ist immer z = 1
                                                       */

        Point point = ray.pointAt(t); /*
                                       * point wird auf den Punkt gesetzt, den der Strahl bei t =
                                       * Double.POSITIVE_INFINITY erreicht.
                                       * Dies ist ein theoretischer Punkt, der weit entfernt ist, da der Hintergrund
                                       * als unendlich entfernt betrachtet wird.
                                       */

        /*
         * Ein neues Hit-Objekt wird mit den berechneten Werten t, point, normal und
         * constColor zurückgegeben.
         * Dieses Hit-Objekt enthält Informationen darüber, wo und wie der Strahl den
         * Hintergrund trifft, einschließlich der Farbe des Hintergrunds.
         */
        return new Hit(t, point, normal, constColor);
    }
}
