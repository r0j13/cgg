package cgg.a03;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;

public class Hit {
//Attribute
double t; // Strahlparameter
Point x; // der Position des Trefferpunkts 𝑥,
Direction n; // Normalenvektor
Color c;

    // Methode die das Ergebnis einer Schnittuntersuchung repräsentiert
    public Hit(double t, Point x, Direction n, Color c) {
        this.t = t; // Das Attribut "t" steht für den Abstand zwischen dem Ursprung des Strahls und dem Schnittpunkt.
        this.x = x; // Das Attribut "x" steht für die Koordinaten des Punktes, an dem sich der Strahl mit einem Objekt schneidet.
        this.n = n; // Das Attribut "n" steht für den Normalenvektor an diesem Punkt auf der Oberfläche des Objekts.
        this.c = c; // Das Attribut "c" steht für die Farbe an diesem Punkt der Oberfläche.
    }

    public double getT() {
        return t;
    }

    public Point getX() {
        return x;
    }

    public Direction getN() {
        return n;
    }

    public Color getC() {
        return c;
    }
}
