package cgg.a08;

import cgg.Image;
import cgtools.*;

public class Main {

  public static void main(String[] args) {
    final int width = 512 * 2;
    final int height = 256 * 2;

    // EmitterMaterial ersMaterial=new EmitterMaterial(Vector.red);
    // // Создание сфер с различными материалами
    // Sphere redSphere = new Sphere(1, -2, 0, -3, new DiffMaterial((Vector.red)));
    // Sphere purpleSphere = new Sphere(1, 2, 0.5, -6, new DiffMaterial(new
    // Color(0.6, 0.1, 0.9)));
    // Sphere blueSphere = new Sphere(0.4, 3, -1, -5, new
    // EmitterMaterial(Vector.blue));

    /*
     * Group spheresGroup = new Group();
     * Background background = new Background(new EmitterMaterial(Vector.gray));
     * Disc disc = new Disc(new Point(2, 0.5, -6), 3, Vector.direction(0, 1.5, 0),
     * new DiffMaterial(Vector.cyan));
     * spheresGroup.add(disc);
     * spheresGroup.add(background);
     * spheresGroup.add(redSphere);
     * spheresGroup.add(purpleSphere);
     * // spheresGroup.add(blueSphere);
     */

    // matrix = Matrix.multiply(Matrix.rotation(Vector.zAxis, 0), matrix);
    // Lochkamera camera = new Lochkamera(Math.PI / 1.5, width, height, matrix);

    // RayTracer content = new RayTracer(camera, spheresGroup);
    Image image = new Image(width, height);

    // image.sample(content, 10);
    /*
     * Matrix.identity steht für die Identitätsmatrix, die keine Transformationen
     * (Translation, Rotation oder Skalierung) anwendet. Sie ist die Ausgangsmatrix,
     * die später durch weitere Matrizenoperationen verändert wird.
     */
    Matrix matrix = Matrix.identity;
    /*
     * Diese Methode erstellt eine Translationsmatrix, die das Koordinatensystem um
     * den Punkt (−0.5,3,−1)(−0.5,3,−1) verschiebt. Das bedeutet, dass das Objekt
     * oder die Kamera in diesem Fall um −0.5−0.5 Einheiten in der x-Richtung, 3
     * Einheiten in der y-Richtung und −1−1 Einheiten in der z-Richtung verschoben
     * wird.
     */
    matrix = Matrix.multiply(Matrix.translation(new Point(-0.5, 3, -1)), 
    // Diese Methode erstellt eine Rotationsmatrix, die das Koordinatensystem um die x-Achse um −30−30 Grad dreht.
    Matrix.rotation(Vector.xAxis, -30));
    
    Lochkamera camera = new Lochkamera(Math.PI / 1.5, width, height, matrix);

    Group scene1 = new Group();

    scene1.add(new Background(new EmitterMaterial(Vector.cyan)));
    scene1.add(new Disc(new Point(0.0, -0.5, 0.0), 100, Vector.direction(0, 1.0, 0), new DiffMaterial(Vector.green)));

    for (int x = -6; x < 6; x++) {
      for (int z = -10; z < -2; z++) {
        scene1.add(new Robot(new Point(x, -0.5, z), 0.05, 0.07, 0.1, 0.4, 0.6, 0.4, new DiffMaterial(Vector.gray)));
      }
    }

    Image image1 = new Image(width, height);
    //image1.sample(new RayTracer(camera, scene1), 5);
    final String filename1 = "doc/a08-scene.png";
    image1.write(filename1);
    System.out.println("Wrote image: " + filename1);

  }

}
