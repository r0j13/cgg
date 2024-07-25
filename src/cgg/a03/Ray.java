package cgg.a03;

import cgtools.Direction;
import cgtools.Point;
import static cgtools.Vector.normalize;

public class Ray { // Strahl im 3D-Raum

   Point x0; // Ursprungspunkt
   Direction d;
   double tmin; // minimaler Abstand entlang des Strahls
   double tmax; // maximaler Abstand

   public Ray(Point x0, Direction d, double tmin, double tmax) {
      this.x0 = x0;
      this.d = normalize(d); /*
                              * Wichtig ist, dass der Richtungsvektor (d) normalisiert werden muss, bevor er
                              * im Objekt gespeichert wird.
                              * Das bedeutet, dass seine Länge gleich 1 Einheit ist.
                              * Dadurch wird sichergestellt, dass alle Strahlen unabhängig von ihrer Richtung
                              * eine einheitliche Länge haben.
                              */
      this.tmin = tmin;
      this.tmax = tmax;
   }

   /*
    * Die Methode pointAt berechnet den Punkt entlang eines Strahls zu einem
    * gegebenen Parameter t durch die Addition des Ursprungs des Strahls und des
    * Richtungsvektors, skaliert mit t.
    */
   public Point pointAt(double t) { // double t – Dies ist ein Parameter, der den Abstand entlang des Strahls
                                    // darstellt.
      // Halbgerade Parametrische Form
      return new Point(this.x0.x() + this.d.x() * t, // Zu diesem Zweck wird jede Komponente von d mit t multipliziert
                                                     // und zu jeder Komponente von x0 addiert.
            this.x0.y() + this.d.y() * t,
            this.x0.z() + this.d.z() * t);
   }

   /*
    * Die Methode isValid() prüft, ob ein gegebener Wert für t in den durch tmin
    * und tmax definierten gültigen Bereich fällt.
    * Wenn er nicht in diesen Bereich fällt, bedeutet dies, dass es keinen
    * Schnittpunkt zwischen diesem Strahl und den Objekten in seinem Pfad gibt.
    */
   public boolean isValid(double t) {
      return t < tmax && t > tmin;
   }

   public Point getX0() {
      return x0;
   }

   public Direction getD() {
      return d;
   }

}