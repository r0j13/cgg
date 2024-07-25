package cgg.a04;

import cgtools.*;
import cgg.Image;

import java.util.ArrayList;

import static cgtools.Vector.*;

public class Main {

        public static void main(String[] args) {
                final int width = 512 * 2;
                final int height = 256 * 2;

                Sphere Sphere = new Sphere(1, 0, 0, -2, red);

                Ray ray = new Ray(new Point(0, 0, 0),
                                new Direction(0, 0, -1),
                                0, Double.POSITIVE_INFINITY);

                System.out.println(Sphere.intersect(ray).getX().toString());
                System.out.println(Sphere.intersect(ray).getN());

                Sphere redSphere = new Sphere(1, -2, 0, -3, red);
                Sphere purpleSphere = new Sphere(1, 2, 0.5, -6, new Color(0.6, 0.1, 0.9));
                Sphere blueSphere = new Sphere(0.4, 3, -1, -5, green);
                // objects Group
                Group spheresGroup = new Group();
                Background background = new Background(gray);
                Disc disc = new Disc(new Point(2, 0.5, -6), 15, Vector.direction(0, 1, -1), cyan);
                spheresGroup.add(disc);
                spheresGroup.add(background);
                spheresGroup.add(redSphere);
                spheresGroup.add(purpleSphere);
                spheresGroup.add(blueSphere);

                RayTracer content = new RayTracer(new Lochkamera(Math.PI / 2, width, height), spheresGroup);
                Image image = new Image(width, height);

                //image.sample(content, 10);
                final String filename = "doc/a04-own-scene.png";
                image.write(filename);
                System.out.println("Wrote image: " + filename);

                Lochkamera camera = new Lochkamera(70.0, 1920, 1080);
                Group scene = new Group();
                scene.add(new Background(gray70));
                scene.add(new Disc(point(0.0, -0.5, 0.0), 100.0, direction(0, 1, 0), yellow));
                scene.add(new Sphere(0.7, -1.0, -0.25, -2.5, red));
                scene.add(new Sphere(0.5, 0.0, -0.25, -2.5, green));
                scene.add(new Sphere(0.7, 1.0, -0.25, -2.5, blue));

                RayTracer content2 = new RayTracer(camera, scene);
                Image image2 = new Image(1920, 1080);

                //image2.sample(content2, 10);
                final String filename2 = "doc/a04-image.png";
                image2.write(filename2);
                System.out.println("Wrote image: " + filename2);
        }

        public static ArrayList<Sphere> randSphereList(int anzahl) {
                ArrayList<Sphere> spheres = new ArrayList<Sphere>();
                for (int i = 0; i < anzahl; i++) {
                        Sphere Planet = new Sphere(Math.random() * 2,
                                        Math.random() * 3 - 1.5,
                                        Math.random() * 3 - 1.5,
                                        Math.random() * -8 + 4,
                                        randomColor());
                        spheres.add(Planet);
                }
                return spheres;
        }

        public static Color randomColor() {
                double r = Math.random();
                double g = Math.random();
                double b = Math.random();
                return new Color(r, g, b);
        }
}