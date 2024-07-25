package cgg.a04;

import java.util.ArrayList;
import java.util.Objects;

public class Group implements Shape {

    ArrayList<Shape> objects;

    public Group() {
        objects = new ArrayList<Shape>();
    }

    public void add(Shape shape) {
        objects.add(shape);
    }

    public ArrayList<Shape> getObjects() {
        return objects;
    }

    @Override
    public Hit intersect(Ray ray) { // Ray ray: Der Strahl, der mit den Formen in der Sammlung geschnitten wird.
        Hit kleinsteHit = null;
        for (Shape s : objects) { // Schleifenvariable s: Jede Form in der Sammlung objects.

            /*
             * Hit H = s.intersect(ray): Für jede Form s in der Sammlung wird die Methode
             * intersect aufgerufen, um zu überprüfen,
             * ob der Strahl ray diese Form schneidet.
             * Das Ergebnis ist ein Hit-Objekt H, das Informationen über den Schnittpunkt
             * enthält. Wenn es keinen Schnittpunkt gibt, ist H null.
             */
            Hit H = s.intersect(ray);
            if (H != null) { // Wenn H nicht null ist, bedeutet dies, dass der Strahl die Form s getroffen
                             // hat.

                /*
                 * Wenn kleinsteHit null ist, bedeutet das, dass bisher kein Treffer gefunden
                 * wurde, also wird H der erste Treffer.
                 * Wenn H.getT() < kleinsteHit.getT() ist, bedeutet das,
                 * dass H näher am Strahlursprung liegt als der bisher nächstgelegene Treffer
                 * kleinsteHit.
                 * Daher wird H der neue nächstgelegene Treffer.
                 */
                if (kleinsteHit == null || H.getT() < kleinsteHit.getT()) {
                    kleinsteHit = H;
                }

            }
        }
        return kleinsteHit;
        /*
         * Am Ende der Methode wird das kleinsteHit-Objekt zurückgegeben, das den
         * nächstgelegenen Schnittpunkt repräsentiert.
         * Wenn kein Schnittpunkt gefunden wurde, bleibt kleinsteHit null.
         */
    }

}
