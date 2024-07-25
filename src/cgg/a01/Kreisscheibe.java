package cgg.a01;

import static cgtools.Vector.black;

import cgtools.*;

public class Kreisscheibe {

  Color color;
  Color hintergrund = black;

  Kreisscheibe(Color color) {
    this.color = color;
  }

  // Returns the color for the given position.
  public Color getColor(double i, double j) {
    double centerW = 256; // Kreismittelpunk der x Koordinate
    double centerH = 128; // Kreismittelpunk der y Koordinate
    double radius = 120; // Radiusgröße

    // Es wird kontrolliert ob der Punkt innerhalb des Radius liegt, bei i und j
    if (((i - centerW) * (i - centerW)) + ((j - centerH) * (j - centerH)) < radius * radius) {
      return color;
    }
    // schwarz
    return hintergrund;
  }

}