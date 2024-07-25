package cgg.a04;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;
import static cgtools.Vector.direction;

public class Sphere implements Shape {

    double Radius;
    double PositionX;
    double PositionY;
    double PositionZ;
    Color Farbe;


    public Sphere(double radius, double positionX,
                  double positionY, double positionZ, Color farbe) {
        Radius = radius;
        PositionY = positionY;
        PositionX = positionX;
        PositionZ = positionZ;
        Farbe = farbe;
    }

    public Hit intersect(Ray ray){
        Direction minC=direction(this.PositionX, this.PositionY, this.PositionZ);
        Point newX0=Vector.subtract(ray.x0, minC);
        double a= Vector.dotProduct(ray.d,ray.d);
        double b= 2*Vector.dotProduct(newX0,ray.d);
        double c= Vector.dotProduct(newX0,newX0)
        -this.Radius*this.Radius;

        Direction norm;
        
        double Wurz=b*b-4*a*c;
        
        if (Wurz<0){
            return null;
        }
        double tmin=0;
        double tmax=0;
     /*   double t = Math.min(tmin, tmax);
        if (t < 0) {
            t = Math.max(tmin, tmax);
        }

        // Berechne den Schnittpunkt und die Normale
        Point intersectionPoint = ray.pointAt(t);
        Direction normal = Vector.divide(Vector.subtract(intersectionPoint,
                new Point(this.PositionX,this.PositionY,this.PositionZ)), Radius);

        return new Hit(t, intersectionPoint, normal, this.Farbe);
*/

        if (cgtools.Util.isZero(Wurz)) {
            tmin=-b/(2*a);
            norm=Vector.divide(Vector.subtract(ray.pointAt(tmin),
                            new Point(this.PositionX,this.PositionY,this.PositionZ)), Radius);
            return new Hit(tmin,ray.pointAt(tmin),norm,this.Farbe);
        }
        tmax=(-b+Math.sqrt(Wurz))/(2*a);
        tmin=(-b-Math.sqrt(Wurz))/(2*a);
        if (ray.isValid(tmin)&&ray.isValid(tmin)) {
            if (tmin<tmax){
                norm=Vector.divide(Vector.subtract(ray.pointAt(tmin),
                                new Point(this.PositionX,this.PositionY,this.PositionZ)), Radius);

                return new Hit(tmin,ray.pointAt(tmin),norm,this.Farbe);
            } else{
                norm=Vector.divide(Vector.subtract(ray.pointAt(tmax),
                                new Point(this.PositionX,this.PositionY,this.PositionZ)), Radius);
                return new Hit(tmax,ray.pointAt(tmax),norm,this.Farbe);
            }
        } else if(ray.isValid(tmin)||ray.isValid(tmax)){
            if (ray.isValid(tmin)) {
                norm=Vector.divide(Vector.subtract(ray.pointAt(tmin),
                        new Point(this.PositionX,this.PositionY,this.PositionZ)), Radius);
                return new Hit(tmin,ray.pointAt(tmin),norm,this.Farbe);
            }  else{
                norm=Vector.divide(Vector.subtract(ray.pointAt(tmax),
                        new Point(this.PositionX,this.PositionY,this.PositionZ)), Radius);
                return new Hit(tmax,ray.pointAt(tmax),norm,this.Farbe);
            }

        } else{
            return  null;
        }

    }

    public Color getFarbe() {
        return Farbe;
    }
    
}
