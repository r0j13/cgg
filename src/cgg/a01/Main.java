/** @author hartmut.schirmacher@bht-berlin.de && henrik.tramberend@bht-berlin.de */
package cgg.a01;

import static cgtools.Vector.*;
import cgg.*;

public class Main {

  public static void main(String[] args) {
    final int width = 512;
    final int height = 256;

    // This class instance defines the contents of the image.
    //ConstantColor content = new ConstantColor(red);
    //Kreisscheibe content=new Kreisscheibe(red);
    PolkaDots content=new PolkaDots(red);
    // Creates an image and iterates over all pixel positions inside the image.
   /* Image image = new Image(width, height);
    for (int i = 0; i != width; i++) {
      for (int j = 0; j != height; j++) {
        // Sets the color for one particular pixel.
        image.setPixel(i, j, content.getColor(i, j));
      }
    }
    // Write the image to disk.
    final String filename = "doc/a01-polka-dots.png";
    image.write(filename);
    System.out.println("Wrote image: " + filename);
    */
  }
}
