package cgg.a02;

import cgtools.Color;
import static cgtools.Vector.black;

public class ColoredDisc {
    Disc[] Collection;

    public ColoredDisc(int Anzahl) {
        this.Collection = new Disc[Anzahl]; // Anzahl wird in Array Samlung initalisiert
        randomDisc(); // Methode welche Scheiben zufällig generiert
    }

    /*
     * Innerhalb der for-Schleife befindet sich eine Zuweisungsanweisung, die neue
     * Instanzen der Klasse Disc erzeugt und sie jedem Element im Array Samlung
     * zuweist.
     * Diese Instanzen werden mithilfe eines Konstruktors mit vier Parametern
     * erstellt: Breite, Höhe, Kapazität und Farbe.
     * Die Werte für diese Parameter werden mit der Funktion Math.random() zufällig
     * generiert, die einen Doppelwert zwischen 0 und 1 zurückgibt.
     * Durch Multiplikation dieser Werte mit verschiedenen Zahlen (200, 255 und 511)
     * können wir Zufallswerte innerhalb bestimmter Bereiche erhalten.
     * Der letzte Parameter, der an den Konstruktor übergeben wird, ist ein weiterer
     * Methodenaufruf - randomColor().
     * Diese Methode gibt ein Color-Objekt zurück, das eine zufällig erzeugte
     * RGB-Farbe darstellt.
     */
    public void randomDisc() {
        for (int i = 0; i < Collection.length; i++) { // for-Schleife, die durch ein Array namens Samlung iteriert.
            Collection[i] = new Disc(Math.random() * 200, Math.random() * 255,
                    Math.random() * 511, randomColor());
        }
        sorting(Collection);
    }

    /*
     * Methode, die eine zufällige Farbe erzeugt,
     * indem sie drei Zufallszahlen für die roten, grünen und blauen Komponenten
     * verwendet, und gibt sie als Color-Objekt zurück.
     */
    public Color randomColor() {
        double r = Math.random(); // Farbe zwischen 0 und 1 machen
        double g = Math.random();
        double b = Math.random();
        return new Color(r, g, b);
    }

    public void sorting(Disc[] UnsortSamlung) {
        // zwei Variablen deklariert, eine für die Speicherung des kleinsten Wertes und
        // eine weitere für die vorübergehende Speicherung eines Scheibenobjekts
        Disc temp;
        for (int i = 0; i < UnsortSamlung.length; i++) { // verschachtelte for-Schleife, die zweimal durch das gesamte
                                                         // Array iteriert
            for (int j = 0; j < UnsortSamlung.length; j++) {
                if (UnsortSamlung[j].Grosse > UnsortSamlung[i].Grosse) { /*
                                                                          * Innerhalb der inneren Schleife befindet sich
                                                                          * eine if-Anweisung, die prüft, ob die
                                                                          * Grosse-Eigenschaft (Grösse) der Scheibe bei
                                                                          * Index j grösser ist als die
                                                                          * Grosse-Eigenschaft der Scheibe bei Index i.
                                                                          * Wenn diese Bedingung erfüllt ist, dann
                                                                          * bedeutet das, dass j in Bezug auf die Größe
                                                                          * vor i liegt und die beiden vertauscht werden
                                                                          * müssen.
                                                                          * Um sie zu vertauschen, verwenden wir unsere
                                                                          * temporäre Variable, um eine von ihnen zu
                                                                          * speichern, während wir die eine zuweisen, um
                                                                          * die andere zu ersetzen.
                                                                          * Dadurch werden ihre Positionen im Array
                                                                          * effektiv vertauscht.
                                                                          */
                    temp = UnsortSamlung[j];
                    UnsortSamlung[j] = UnsortSamlung[i];
                    UnsortSamlung[i] = temp;
                }
                // Nach Abschluss der beiden Schleifen sind alle Scheiben miteinander verglichen
                // worden, und alle notwendigen Vertauschungen sind vorgenommen worden.
                // Das Ergebnis ist eine sortierte Sammlung, in der die kleineren Scheiben vor
                // den größeren platziert sind.
            }
        }
    } /*
       * Es beginnt mit einer Schleife durch ein Array namens "Collection", das
       * Objekte
       * vom Typ "Disc" enthält.
       * Diese Disc-Objekte haben Eigenschaften wie ihre Mittelpunktskoordinaten und
       * ihre Farbe.
       * Die Schleife prüft jedes Disc-Objekt im Array, um zu sehen, ob die
       * angegebenen x- und y-Koordinaten innerhalb seiner Grenzen liegen, indem die
       * Methode "isPointInDisc" verwendet wird.
       */

    public Color getColor(double x, double y) {
        for (int i = 0; i < Collection.length; i++) {
            if (Collection[i].isPointInDisc(x, y)) { // Für jede Disc wird überprüft, ob der Punkt (x, y) innerhalb der
                                                     // Scheibe liegt (isPointInDisc).
                return Collection[i].Farbe; // Ist dies der Fall, wird die Farbeigenschaft des betreffenden Disc-Objekts
                                            // zurückgegeben.

            }
        }
        return black; // Wenn keines der Disc-Objekte den angegebenen Punkt enthält, wird als
                      // Standardwert Schwarz zurückgegeben.
    }
}
