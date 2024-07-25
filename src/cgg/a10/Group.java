package cgg.a10;

import java.util.ArrayList;

import javax.xml.crypto.dsig.Transform;

public class Group implements Shape {

    ArrayList<Shape> objects;
    Transform transform;

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
    public Hit intersect(Ray ray) {
        Hit kleinsteHit = null;
        
        for (Shape s : objects) {
            Hit H = s.intersect(ray);
            if (H != null) {
                if (kleinsteHit == null || H.t() < kleinsteHit.t()) {
                    kleinsteHit = H;
                }

            }
        }
        
        return kleinsteHit;
    }

}
