package cgg.a01;

import static cgtools.Vector.black;
import static cgtools.Vector.gray;
import static cgtools.Vector.gray70;

import cgtools.*;

public class PolkaDots {

  Color color;
  Color hintergrund = gray70;

  PolkaDots(Color color) {
    this.color = color;
  }

  // Returns the color for the given position.
  public Color getColor(double i, double j) {
    double centerW; // Kreismittelpunk x
    double centerH; // Kreismittelpunkt y
    double radius = 15;

    // Y - Koordinate für den mittelpunkt, 1. Position = 1 * 30 = 30, Position 2 = 2
    // * 30 = 60 usw.
    for (int index1 = 1; index1 < 13; index1++) {
      centerH = 30 * index1;

      for (int index2 = 1; index2 < 17; index2++) {
        centerW = 30 * index2;

        if (((i - centerW) * (i - centerW)) + ((j - centerH) * (j - centerH)) < radius * radius) {
          return color;
          // Pythagoras
        }
      }
    }

    return hintergrund;
  }
}
