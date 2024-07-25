package cgg.a05;

import java.util.ArrayList;

public class Group implements Shape {

    ArrayList<Shape> objects;

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
