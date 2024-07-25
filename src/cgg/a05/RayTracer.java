package cgg.a05;

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
        return calculateRadiance(group, ray, 5); // Rekursionstiefe 5
    }

    /*
     * Shape scene: Die Szene oder Gruppe von Objekten, durch die der Strahl
     * verfolgt wird.
     * Ray ray: Der Strahl, dessen Radiance berechnet werden soll.
     * int depth: Die maximale Rekursionstiefe, die die Methode noch verfolgen darf.
     * Color: Die berechnete Farbe, die entlang des Strahls gesehen wird.
     */
    private Color calculateRadiance(Shape scene, Ray ray, int depth) {
        /*
         * Rekursionstiefe: Zuerst wird überprüft, ob die maximale Rekursionstiefe
         * erreicht wurde (depth == 0). Wenn ja, wird new Color(0, 0, 0) zurückgegeben,
         * was schwarz entspricht. Dies bedeutet, dass der Strahl keine weiteren
         * Berechnungen durchführt, weil er seine maximale Tiefe erreicht hat.
         */
        if (depth == 0) {
            return new Color(0, 0, 0);
        }

        // Der Schnittpunkt zwischen dem Strahl und der Szene wird berechnet. Dies
        // liefert ein Hit-Objekt, das Informationen über den Schnittpunkt enthält, wie
        // z.B. die Position des Treffers, das Material des getroffenen Objekts und die
        // Normalenrichtung am Trefferpunkt.
        Hit intersect = scene.intersect(ray);

        // Das Material des getroffenen Objekts wird abgerufen.
        // Ein neuer Strahl (newRay) wird durch das Material erzeugt, der möglicherweise
        // reflektiert oder gestreut wird.
        Material mat = intersect.material();
        Ray newRay = mat.reflecRay(ray, intersect);

        /*
         * Wenn newRay nicht null ist, wird die calculateRadiance-Methode rekursiv
         * aufgerufen, um die Farbe entlang des neuen Strahls zu berechnen. Das Ergebnis
         * wird mit der Farbe des Materials (shade(intersect.n(), mat)) multipliziert,
         * um die endgültige Farbe zu erhalten.
         * Wenn newRay null ist, gibt die Methode die emittierte Farbe des Materials
         * (mat.emission()) zurück.
         */
        if (newRay != null) {
            
            return Vector.multiply(shade(intersect.n(), mat), calculateRadiance(scene, newRay, depth - 1));
        } else {
            
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
