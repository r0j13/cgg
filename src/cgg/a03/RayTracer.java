package cgg.a03;

import cgtools.*;
import java.util.ArrayList;

//implementeirt Sampler
//getColor
public class RayTracer implements Sampler {
    private Lochkamera camera;
    private ArrayList<Sphere> spheres;

    public RayTracer(Lochkamera camera, ArrayList<Sphere> spheres) {
        this.camera = camera; // Die Kamera wird verwendet, um den Blickwinkel zu bestimmen, aus dem das Bild
                              // gerendert wird.
        this.spheres = spheres; // Die ArrayList der Sphere-Objekte repräsentiert die 3D-Szene, die gerendert
                                // wird.
    }

    @Override
    // die Farbe eines bestimmten Pixels in einem Bild zu bestimmen.
    public Color getColor(double x, double y) { // x und y, die die Koordinaten des Pixels darstellen
        Color background = Vector.black; // Zunächst wird eine Variable für die Hintergrundfarbe erstellt und auf
                                         // Schwarz gesetzt.
        Color sphereColor = Vector.add(Vector.black, Vector.gray); // Dann wird eine weitere Variable für die Farbe der
                                                                   // Kugel erstellt und auf grau gesetzt.
        double tmax = Double.POSITIVE_INFINITY; // Als Nächstes wird ein Maximalwert für t (der die Entfernung
                                                // darstellt) auf unendlich gesetzt.
        Ray ray = camera.createRay(x, y); // Dann wird mit dem Kameraobjekt ein Strahl erstellt, der später verwendet
                                          // wird
        Hit smallHit = null;
        for (Sphere sphere : spheres) { // berechnet, ob es einen Schnittpunkt zwischen dem Strahl und dieser bestimmten
                                        // Sphäre gibt)
            Hit H = sphere.intersect(ray);
            if (H != null) { /*
                              * Wenn es einen Schnittpunkt gibt (was bedeutet, dass H nicht gleich Null ist),
                              * prüfen wir,
                              * ob dieser neue Trefferpunkt näher als alle vorherigen liegt, indem wir seinen
                              * t-Wert mit dem t-Wert von smallHit vergleichen.
                              */
                if (smallHit == null) {
                    smallHit = H;
                } /*
                   * Wenn ja, aktualisiere smallHit mit diesem neuen Trefferpunkt.
                   * Wenn innerhalb der Schleife tatsächlich ein Schnittpunkt gefunden wurde
                   * und smallHit nun Informationen
                   * über diesen nächstgelegenen Trefferpunkt enthält, verwenden wir die Funktion
                   * shade(), um zu berechnen,
                   * welche Farbe für diesen Pixel auf der Grundlage seines Normalenvektors
                   * (dargestellt durch N)
                   * und der Oberflächenfarbe (dargestellt durch C) angezeigt werden soll.
                   */
                if (smallHit.getT() > H.getT()) {  // Wenn neue schnittpunkt ist vergleiche ob näher liegt
                    smallHit = H;
                }
                if (smallHit.getT() < tmax) {
                    sphereColor = shade(smallHit.getN(), smallHit.getC());
                }
            }

        }
        return sphereColor;

    }
    // Farbe des Trefferpunkts, drei-dimensional
    static Color shade(Direction normal, Color color) {
        /*
         * Erzeugt einen Lichtvektor lightDir mit den Komponenten (1, 1, 0.5).
         * Vector.direction(1, 1, 0.5) erstellt einen Vektor mit den angegebenen
         * Komponenten.
         * Vector.normalize(...) normalisiert den Vektor, sodass seine Länge 1 beträgt.
         */
        Direction lightDir = Vector.normalize(Vector.direction(1, 1, 0.5));
        /*
         * Berechnet den Anteil der Umgebungsbeleuchtung.
         * Multipliziert die Farbe color mit 0.1, um den Umgebungslichtanteil zu
         * berechnen.
         */
        Color ambient = Vector.multiply(0.1, color); // 0.1 = 10%
        /*
         * Berechnet den Anteil der diffusen Beleuchtung.
         * Vector.dotProduct(lightDir, normal) berechnet das Skalarprodukt der Licht-
         * und Normalenrichtungen.
         * Math.max(0, ...) stellt sicher, dass negative Werte auf 0 gesetzt werden, da
         * diffuse Beleuchtung nicht negativ sein kann.
         * Das Ergebnis wird mit 0.9 multipliziert und dann mit der Farbe color.
         */
        Color diffuse = Vector.multiply(0.9 * Math.max(0, Vector.dotProduct(lightDir, normal)), color); // 0.9 = 90%
        return Vector.add(ambient, diffuse);
    }

    public void test(Sphere sphere) {
        /*
         * Überprüft, ob die Farbe der Kugel nicht rot ist (Vector.red).
         * sphere.getFarbe() ruft die Farbe der Kugel ab.
         * Vector.red ist eine vordefinierte Farbe (vermutlich eine Konstante für Rot).
         */
        if (sphere.getFarbe() != Vector.red) {
            // Wenn die Farbe der Kugel nicht rot ist, wird die Farbe der Kugel auf der
            // Konsole ausgegeben.
            System.out.println(sphere.getFarbe().toString());
        }
    }

}

/*
 * Die Klasse Ray Tracer ist eine Java-Implementierung eines
 * Raytracing-Algorithmus.
 * Ray Tracing ist eine Rendering-Technik, die in der Computergrafik verwendet
 * wird, um realistische Bilder zu erzeugen, indem das Verhalten von
 * Lichtstrahlen simuliert wird.
 * Dieser Algorithmus verfolgt den Weg der Lichtstrahlen aus der Perspektive der
 * Kamera und berechnet die Farbe jedes Pixels im Bild.
 */