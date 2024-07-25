package cgg.a05;
import cgg.Image;
import cgtools.*;

public class Main {

    public static void main(String[] args) {
        final int width = 512 * 2;
        final int height = 256 * 2;

        

        // EmitterMaterial ersMaterial=new EmitterMaterial(Vector.red);
        // // Создание сфер с различными материалами
        Sphere redSphere = new Sphere(1, -2, 0, -3, new DiffMaterial((Vector.red)));
         Sphere purpleSphere = new Sphere(1, 2, 0.5, -6, new DiffMaterial(new Color(0.6, 0.1, 0.9)));
        // Sphere blueSphere = new Sphere(0.4, 3, -1, -5, new EmitterMaterial(Vector.blue));

       
        Group spheresGroup = new Group();
        Background background = new Background(new EmitterMaterial(Vector.gray));
        Disc disc = new Disc(new Point(2, 0.5, -6), 3, Vector.direction(0, 1.5, 0), new DiffMaterial(Vector.cyan));
        spheresGroup.add(disc);
        spheresGroup.add(background);
         spheresGroup.add(redSphere);
         spheresGroup.add(purpleSphere);
        // spheresGroup.add(blueSphere);
        Lochkamera camera=new Lochkamera(Math.PI / 2, width, height);
        
        RayTracer content = new RayTracer(camera, spheresGroup);
        Image image = new Image(width, height);

        
       // image.sample(content, 10);

        
        final String filename = "doc/a05-own-scene.png";
        image.write(filename);
        System.out.println("Wrote image: " + filename);



        Group scene1 = new Group();

        scene1.add(new Background(new EmitterMaterial(new Color(0.8,0.8, 1))));
        scene1.add(new Disc(new Point(0.0, -0.5, 0.0),100,  Vector.direction(0,1.0,0) ,new DiffMaterial(Vector.gray)));
        scene1.add(new Sphere(.5, 0, -0.02, -3.5 , new DiffMaterial(Vector.red)));

        Image image1 = new Image(width, height);
      //  image1.sample(new RayTracer( camera,scene1), 2);
        final String filename1 = "doc/a05-test-scene.png";
        image1.write(filename1);
        System.out.println("Wrote image: " + filename1);

    }


}
