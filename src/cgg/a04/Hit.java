package cgg.a04;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;

public class Hit {

double t;
Point x;
Direction n;
Color c;

    public Hit(double t, Point x, Direction n, Color c) {
        this.t = t;
        this.x = x;
        this.n = n;
        this.c = c;
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
