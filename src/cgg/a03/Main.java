package cgg.a03;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgg.Image;

import java.util.ArrayList;

import static cgtools.Vector.*;

public class Main {

        /*
         * Die main Methode ist der Startpunkt des Programms. Sie definiert die Breite
         * (width) und Höhe (height) des zu rendernden Bildes.
         */
        public static void main(String[] args) {
                final int width = 512 * 2;
                final int height = 256 * 2;

                /*
                 * Eine Kugel (Sphere) wird mit einem Radius von 1 und einer Position (0, 0, -2)
                 * und der Farbe red erstellt.
                 * Ein Ray (Strahl) wird mit Ursprungspunkt (0, 0, 0) und Richtung (0, 0, -1)
                 * erstellt.
                 * Die intersect Methode wird aufgerufen, um den Schnittpunkt des Strahls mit
                 * der Kugel zu berechnen, und die Ergebnisse (der Schnittpunkt und die Normale
                 * an der Schnittstelle) werden ausgegeben.
                 */
                Sphere Sphere = new Sphere(1, 0, 0, -2, red);

                Ray ray = new Ray(new Point(0, 0, 0),
                                new Direction(0, 0, -1),
                                0, Double.POSITIVE_INFINITY);

                System.out.println(Sphere.intersect(ray).getX().toString());
                System.out.println(Sphere.intersect(ray).getN());

                // Hier werden mehrere Kugeln (Spheres) mit unterschiedlichen Positionen, Radien
                // und Farben erstellt.
                Sphere redSphere = new Sphere(1, 0, 0, -3, red);
                Sphere graySphere = new Sphere(1, -1.5, 1.5, -4, gray);
                Sphere orangeSphere = new Sphere(1, -3, -3, -6, new Color(1, 0.5, 0));
                Sphere lightblueSphere = new Sphere(2, 0, 3, -9, new Color(0.3, 0.3, 1));
                Sphere purpleSphere = new Sphere(1, 2, 0.5, -6, new Color(0.6, 0.1, 0.9));
                Sphere blueSphere = new Sphere(0.4, 3, -1, -5, blue);

                // Die erstellten Kugeln werden zu einer ArrayList hinzugefügt, um später in der
                // Szene verwendet zu werden.
                ArrayList<Sphere> spheres = new ArrayList<Sphere>();

                spheres.add(redSphere);
                spheres.add(graySphere);
                spheres.add(orangeSphere);
                spheres.add(lightblueSphere);
                spheres.add(purpleSphere);
                spheres.add(blueSphere);

                /*
                 * Ein RayTracer Objekt wird erstellt, das eine Lochkamera (Lochkamera) mit
                 * einem Sichtfeld von 90 Grad (Math.PI / 2) verwendet und eine zufällige Liste
                 * von 3 Kugeln (randSphereList(3)).
                 * Ein Image Objekt wird mit der angegebenen Breite und Höhe erstellt.
                 * Das Bild wird unter dem angegebenen Dateinamen gespeichert und eine
                 * Erfolgsmeldung ausgegeben.
                 */
                RayTracer content = new RayTracer(new Lochkamera(Math.PI / 2, width, height), randSphereList(3));
                Image image = new Image(width, height);

                // image.sample(content, 10);
                final String filename = "doc/a03-spheres.png";
                image.write(filename);
                System.out.println("Wrote image: " + filename);

        }

        // Diese Methode erstellt eine Liste von zufällig positionierten und gefärbten
        // Kugeln. Die Anzahl der Kugeln wird durch den Parameter anzahl bestimmt.
        public static ArrayList<Sphere> randSphereList(int anzahl) {
                ArrayList<Sphere> spheres = new ArrayList<Sphere>();
                for (int i = 0; i < anzahl; i++) {
                        Sphere Planet = new Sphere(Math.random() * 2,
                                        // Sie begrenzen die x- und y-Koordinaten der Kugelposition auf einen Bereich
                                        // von -1.5 bis 1.5. Dies stellt sicher, dass die Kugeln im Sichtfeld der Kamera
                                        // liegen und gleichmäßig verteilt sind.
                                        Math.random() * 3 - 1.5,
                                        Math.random() * 3 - 1.5,
                                        // Dies stellt sicher, dass die z-Koordinate der Kugelposition im Bereich von -3
                                        // bis -4 liegt. Dadurch wird gewährleistet, dass die Kugeln ausreichend weit
                                        // von der Kamera entfernt sind, um sichtbar zu sein, aber nicht so weit, dass
                                        // sie zu klein erscheinen oder außerhalb des Sichtfelds liegen.
                                        Math.random() - 4,
                                        randomColor());
                        spheres.add(Planet);
                }
                return spheres;
        }

        // Diese Methode erstellt eine zufällige Farbe, indem sie zufällige Werte für
        // die Rot-, Grün- und Blaukomponenten generiert und diese zu einem Color Objekt
        // kombiniert.
        public static Color randomColor() {
                double r = Math.random();
                double g = Math.random();
                double b = Math.random();
                return new Color(r, g, b);
        }
}