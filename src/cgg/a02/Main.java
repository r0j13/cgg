package cgg.a02;

import static cgtools.Vector.*;
import cgg.*;
import cgtools.Sampler;

public class Main {

  public static void main(String[] args) {
    final int width = 512;
    final int height = 256;

    // This class instance defines the contents of the image.
    // ConstantColor content = new ConstantColor(red);
    ColoredDisc Colored = new ColoredDisc(15);

    // Creates an image and iterates over all pixel positions inside the image.

    Image image = new Image(width, height);
    for (int i = 0; i != width; i++) {
      for (int j = 0; j != height; j++) {
        // Sets the color for one particular pixel.
        image.setPixel(i, j, Colored.getColor(i, j));
      }
    }
    final String filename = "doc/a02-stratified-presampled.png";
    image.write(filename);
    System.out.println("Wrote image: " + filename);

    image.sample(Colored, 10);
    // Write the image to disk.
    final String filename1 = "doc/a02-stratified.png";
    image.write(filename1);
    System.out.println("Wrote image: " + filename1);

  }
}
