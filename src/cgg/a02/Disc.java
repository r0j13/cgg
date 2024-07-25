package cgg.a02;

import cgtools.Color;

public class Disc {

    double Grosse;
    double PositionW;
    double PositionH;
    Color Farbe;

    /*
     * Die Klasse hat vier Attribute: Größe (size), PositionW (Position auf der
     * Breitenachse),
     * PositionH (Position auf der Höhenachse) und Farbe (color).
     * 
     * Die Konstruktormethode für diese Klasse nimmt diese vier Parameter auf und
     * ordnet sie den jeweiligen Attributen zu.
     * So können wir verschiedene Instanzen der Klasse Disc mit unterschiedlichen
     * Größen, Positionen und Farben erstellen.
     */
    public Disc(double Grosse, double PositionW, double PositionH, Color Farbe) {
        this.Grosse = Grosse;
        this.PositionW = PositionW; // Breitenachse
        this.PositionH = PositionH; // Höhenachse
        this.Farbe = Farbe;
    }

    /*
     * Diese Methode prüft, ob diese Koordinaten innerhalb der Grenzen der Scheibe
     * liegen, indem sie eine mathematische Formel verwendet, die den Abstand
     * zwischen diesen Koordinaten und dem Mittelpunkt der Scheibe (dargestellt
     * durch PositionW und PositionH) berechnet.
     * Ist dieser Abstand kleiner als Grosse (der Radius), dann bedeutet dies, dass
     * diese Punkte innerhalb des Kreises liegen, und es wird true zurückgegeben.
     * Andernfalls gibt er false zurück.
     */
    public boolean isPointInDisc(double i, double j) {
        if (((i - this.PositionW) * (i - this.PositionW))
                + ((j - this.PositionH) * (j - this.PositionH)) < (this.Grosse * this.Grosse)) {
            return true;
        }
        return false;
    }
}
