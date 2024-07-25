package cgg.a06;

import cgtools.Direction;
import cgtools.Point;
import static cgtools.Vector.normalize;

public class Ray {

    Point x0;
    Direction d;
    double tmin;
    double tmax;

    public Ray(Point x0, Direction d, double tmin, double tmax) {
        this.x0 = x0;
        this.d = normalize(d);
        this.tmin = tmin;
        this.tmax = tmax;
    }

    public Point pointAt(double t) {
        return new Point(this.x0.x() + this.d.x() * t,
                this.x0.y() + this.d.y() * t,
                this.x0.z() + this.d.z() * t);
    }

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
