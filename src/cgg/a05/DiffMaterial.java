package cgg.a05;

import cgtools.*;

import static cgtools.Vector.color;

public record DiffMaterial(Color albedos) implements Material {
    /*
     * Zusammengefasst, dieser Code implementiert ein diffuses Material in einem
     * Raytracing-Programm. Der reflektierte Strahl wird durch zufällige Richtungen
     * innerhalb einer Einheitssphäre berechnet, was eine diffuse Reflexion
     * simuliert.
     */

    @Override
    public Color emission() {
        return color(0);
    }

    @Override
    public boolean gestreut() {
        return true;
    }

    @Override
    public Ray reflecRay(Ray ray, Hit hit) {
        // Erzeugt zufällige x, y und z Werte zwischen -1 und 1.
        double x = new Random().nextDouble(2) - 1;
        double y = new Random().nextDouble(2) - 1;
        double z = new Random().nextDouble(2) - 1;
        // Berechnet den euklidischen Abstand (die Länge) des Vektors (x, y, z).
        double xyz = Math.sqrt((x * x) + (y * y) + (z * z));
        // numerische Stabilität
        double t = 1 * Math.pow(10, -7);
        /*
         * Wenn der zufällige Punkt innerhalb der Einheitssphäre liegt (xyz <= 1):
         * 
         * Erstellt eine neue Richtung r aus den zufälligen Werten.
         * Normalisiert die Richtung d durch Addition der Treffer-Normale hit.n() und
         * der zufälligen Richtung r.
         * Erstellt einen neuen Strahl Ray von der Trefferposition x0 in Richtung d mit
         * Start t und unendlicher Reichweite.
         * Gibt diesen neuen Strahl zurück.
         * 
         * Wenn der Punkt außerhalb der Einheitssphäre liegt, wird die Methode rekursiv
         * aufgerufen, um einen neuen zufälligen Punkt zu erzeugen.
         */
        if (xyz <= 1) {
            Direction r = new Direction(x, y, z);
            Direction d = Vector.normalize(Vector.add(hit.n(), r));
            Point x0 = hit.x();
            return new Ray(x0, d, t, Double.POSITIVE_INFINITY);
        } else {
            return reflecRay(ray, hit);

        }
    }

}
