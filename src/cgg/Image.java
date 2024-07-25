/** @author hartmut.schirmacher@bht-berlin.de && henrik.tramberend@bht-berlin.de */
package cgg;

import cgg.a02.ColoredDisc;
//import cgg.a03.RayTracer;
import cgg.a10.RayTracer;
import cgtools.*;

public class Image implements Sampler {
    // double[] data: Ein Array zur Speicherung der Farbwerte der Pixel. Jeder Pixel
    // wird durch drei Werte repräsentiert (Rot, Grün, Blau).
    double[] data;
    int width;
    int height;

    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        data = new double[width * height * 3];
    }

    public void setPixel(int i, int j, Color color) {

        data[(i + j * width) * 3 + 0] = color.r();
        data[(i + j * width) * 3 + 1] = color.g();
        data[(i + j * width) * 3 + 2] = color.b();

    }

    public void write(String filename) {
        // Use cggtools.ImageWriter.write() to implement this.
        cgtools.ImageWriter.write(filename, data, width, height);

    }

    private void notYetImplemented() {
        System.err.println("Please complete the implementation of class cgg.Image as part of assignment 1.");
        System.exit(1);
    }

    // Diese Methode sampelt die Farben eines ColoredDisc Objekts und setzt die
    // Pixel im Bild. Die Farbe wird durch multiple Samples pro Pixel bestimmt, um
    // eine glattere und genauere Farbdarstellung zu erreichen.
    public void sample(ColoredDisc Colored, double Abtastpunkte) {
        // Diese Schleifen durchlaufen alle Pixel im Bild. Die äußere Schleife geht
        // durch die Breite (x-Koordinate) und die innere Schleife durch die Höhe
        // (y-Koordinate) des Bildes.
        for (int i = 0; i != width; i++) {
            for (int j = 0; j != height; j++) {

                // Hier wird die anfängliche Farbe für den aktuellen Pixel (i, j) vom
                // ColoredDisc Objekt abgerufen. Anschließend werden die Farbwerte r, g und b
                // durch eine Gamma-Korrektur angepasst, um die Helligkeit anzupassen.
                double r = Colored.getColor(i, j).r();
                double g = Colored.getColor(i, j).g();
                double b = Colored.getColor(i, j).b();

                r = 255 * Math.pow(r / 255, 1 / 2.2);  //  𝙼𝚊𝚝𝚑.𝚙𝚘𝚠 ( 𝚡 , 𝚢 ) = x^y 
                g = 255 * Math.pow(g / 255, 1 / 2.2);  // kleinere Zahl
                b = 255 * Math.pow(b / 255, 1 / 2.2);
                /*
                 * Diese Schleifen unterteilen den aktuellen Pixel in kleinere Abtastpunkte. Für
                 * jeden dieser Unterpunkte wird ein zufälliger Wert ra generiert, um eine
                 * zufällige Verschiebung innerhalb des Pixels zu erreichen (jittering). Dadurch
                 * werden die x- und y-Koordinaten der Unterpunkte berechnet.
                 * 
                 * Für jede dieser Unterpunkte werden die Farbwerte vom ColoredDisc Objekt
                 * abgerufen und zu den initialisierten Farbwerten r, g und b addiert.
                 */
                for (int ii = 0; ii < Abtastpunkte; ii++) {
                    for (int jj = 0; jj < Abtastpunkte; jj++) {
                        double ran = cgtools.Random.random();
                        double x = i + (ii + ran) / Abtastpunkte;
                        double y = j + (jj + ran) / Abtastpunkte;
                        // Color c_sample = Colored.getColor(x,y);

                        // c = add(c,c_sample);

                        r = (r + Colored.getColor(x, y).r());
                        g = (g + Colored.getColor(x, y).g());
                        b = (b + Colored.getColor(x, y).b());

                    }
                }
                /*
                 * Nachdem alle Abtastpunkte für den aktuellen Pixel gesammelt wurden, wird der
                 * Mittelwert der Farbwerte berechnet. Dies wird durch das Teilen der summierten
                 * Farbwerte durch die Gesamtzahl der Abtastpunkte (abtastpunkte * abtastpunkte)
                 * erreicht.
                 * 
                 * Die resultierende Farbe wird dann mittels der setPixel Methode für den
                 * aktuellen Pixel (i, j) im Bild gesetzt.
                 */

                setPixel(i, j, new Color(r / (Abtastpunkte * Abtastpunkte), g / (Abtastpunkte * Abtastpunkte),
                        b / (Abtastpunkte * Abtastpunkte)));

            }
        }
    }

    public void sample(RayTracer rayTracer, double abtastpunkte) {

        Thread t1 = new Thread(() -> {
            try {
                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < height / 8; j++) {
                        Color c = Vector.black;
                        for (int ii = 0; ii < abtastpunkte; ii++) {
                            for (int jj = 0; jj < abtastpunkte; jj++) {
                                double ra = cgtools.Random.random();
                                double x = i + (ii + ra) / abtastpunkte; // Zufällige Verschiebung für x
                                double y = j + (jj + ra) / abtastpunkte; // Zufällige Verschiebung für y

                                // Farbe für aktuelle Abtastposition berechnen und aufaddieren
                                c = Vector.add(c, rayTracer.getColor(x, y));
                            }
                        }
                        // Mittelwert der Farben über alle Abtastpunkte berechnen
                        c = Vector.divide(c, abtastpunkte * abtastpunkte);
                        setPixel(i, j, c);
                    }
                    //System.out.println("Thread 1: Completed row " + i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                for (int i = 0; i < width; i++) {
                    for (int j = height / 8; j < height / 4; j++) {
                        Color c = Vector.black;
                        for (int ii = 0; ii < abtastpunkte; ii++) {
                            for (int jj = 0; jj < abtastpunkte; jj++) {
                                double ra = cgtools.Random.random();
                                double x = i + (ii + ra) / abtastpunkte; // Zufällige Verschiebung für x
                                double y = j + (jj + ra) / abtastpunkte; // Zufällige Verschiebung für y

                                // Farbe für aktuelle Abtastposition berechnen und aufaddieren
                                c = Vector.add(c, rayTracer.getColor(x, y));
                            }
                        }
                        // Mittelwert der Farben über alle Abtastpunkte berechnen
                        c = Vector.divide(c, abtastpunkte * abtastpunkte);
                        setPixel(i, j, c);
                    }
                    //System.out.println("Thread 2: Completed row " + i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                for (int i = 0; i < width; i++) {
                    for (int j = height / 4; j < height * 3 / 8; j++) {
                        Color c = Vector.black;
                        for (int ii = 0; ii < abtastpunkte; ii++) {
                            for (int jj = 0; jj < abtastpunkte; jj++) {
                                double ra = cgtools.Random.random();
                                double x = i + (ii + ra) / abtastpunkte; // Zufällige Verschiebung für x
                                double y = j + (jj + ra) / abtastpunkte; // Zufällige Verschiebung für y

                                // Farbe für aktuelle Abtastposition berechnen und aufaddieren
                                c = Vector.add(c, rayTracer.getColor(x, y));
                            }
                        }
                        // Mittelwert der Farben über alle Abtastpunkte berechnen
                        c = Vector.divide(c, abtastpunkte * abtastpunkte);
                        setPixel(i, j, c);
                    }
                    //System.out.println("Thread 2: Completed row " + i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread t4 = new Thread(() -> {
            try {
                for (int i = 0; i < width; i++) {
                    for (int j = height *3 / 8; j < height / 2; j++) {
                        Color c = Vector.black;
                        for (int ii = 0; ii < abtastpunkte; ii++) {
                            for (int jj = 0; jj < abtastpunkte; jj++) {
                                double ra = cgtools.Random.random();
                                double x = i + (ii + ra) / abtastpunkte; // Zufällige Verschiebung für x
                                double y = j + (jj + ra) / abtastpunkte; // Zufällige Verschiebung für y

                                // Farbe für aktuelle Abtastposition berechnen und aufaddieren
                                c = Vector.add(c, rayTracer.getColor(x, y));
                            }
                        }
                        // Mittelwert der Farben über alle Abtastpunkte berechnen
                        c = Vector.divide(c, abtastpunkte * abtastpunkte);
                        setPixel(i, j, c);
                    }
                    //System.out.println("Thread 2: Completed row " + i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread t5 = new Thread(() -> {
            try {
                for (int i = 0; i < width; i++) {
                    for (int j = height / 2; j < height * 5 / 8; j++) {
                        Color c = Vector.black;
                        for (int ii = 0; ii < abtastpunkte; ii++) {
                            for (int jj = 0; jj < abtastpunkte; jj++) {
                                double ra = cgtools.Random.random();
                                double x = i + (ii + ra) / abtastpunkte; // Zufällige Verschiebung für x
                                double y = j + (jj + ra) / abtastpunkte; // Zufällige Verschiebung für y

                                // Farbe für aktuelle Abtastposition berechnen und aufaddieren
                                c = Vector.add(c, rayTracer.getColor(x, y));
                            }
                        }
                        // Mittelwert der Farben über alle Abtastpunkte berechnen
                        c = Vector.divide(c, abtastpunkte * abtastpunkte);
                        setPixel(i, j, c);
                    }
                    //System.out.println("Thread 2: Completed row " + i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread t6 = new Thread(() -> {
            try {
                for (int i = 0; i < width; i++) {
                    for (int j = height * 5 / 8; j < height * 6 / 8; j++) {
                        Color c = Vector.black;
                        for (int ii = 0; ii < abtastpunkte; ii++) {
                            for (int jj = 0; jj < abtastpunkte; jj++) {
                                double ra = cgtools.Random.random();
                                double x = i + (ii + ra) / abtastpunkte; // Zufällige Verschiebung für x
                                double y = j + (jj + ra) / abtastpunkte; // Zufällige Verschiebung für y

                                // Farbe für aktuelle Abtastposition berechnen und aufaddieren
                                c = Vector.add(c, rayTracer.getColor(x, y));
                            }
                        }
                        // Mittelwert der Farben über alle Abtastpunkte berechnen
                        c = Vector.divide(c, abtastpunkte * abtastpunkte);
                        setPixel(i, j, c);
                    }
                    //System.out.println("Thread 2: Completed row " + i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread t7 = new Thread(() -> {
            try {
                for (int i = 0; i < width; i++) {
                    for (int j = height *  6 / 8; j < height * 7 / 8; j++) {
                        Color c = Vector.black;
                        for (int ii = 0; ii < abtastpunkte; ii++) {
                            for (int jj = 0; jj < abtastpunkte; jj++) {
                                double ra = cgtools.Random.random();
                                double x = i + (ii + ra) / abtastpunkte; // Zufällige Verschiebung für x
                                double y = j + (jj + ra) / abtastpunkte; // Zufällige Verschiebung für y

                                // Farbe für aktuelle Abtastposition berechnen und aufaddieren
                                c = Vector.add(c, rayTracer.getColor(x, y));
                            }
                        }
                        // Mittelwert der Farben über alle Abtastpunkte berechnen
                        c = Vector.divide(c, abtastpunkte * abtastpunkte);
                        setPixel(i, j, c);
                    }
                    //System.out.println("Thread 2: Completed row " + i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread t8 = new Thread(() -> {
            try {
                for (int i = 0; i < width; i++) {
                    for (int j = height * 7 / 8; j < height; j++) {
                        Color c = Vector.black;
                        for (int ii = 0; ii < abtastpunkte; ii++) {
                            for (int jj = 0; jj < abtastpunkte; jj++) {
                                double ra = cgtools.Random.random();
                                double x = i + (ii + ra) / abtastpunkte; // Zufällige Verschiebung für x
                                double y = j + (jj + ra) / abtastpunkte; // Zufällige Verschiebung für y

                                // Farbe für aktuelle Abtastposition berechnen und aufaddieren
                                c = Vector.add(c, rayTracer.getColor(x, y));
                            }
                        }
                        // Mittelwert der Farben über alle Abtastpunkte berechnen
                        c = Vector.divide(c, abtastpunkte * abtastpunkte);
                        setPixel(i, j, c);
                    }
                    //System.out.println("Thread 2: Completed row " + i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
            t7.join();
            t8.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Both threads have finished execution.");
    }
    @Override
    public Color getColor(double x, double y) {

        return null;
    }
}
