package cgg.a05;

import cgtools.Color;

public interface Material {

    // public Color emittedRadiance(Ray ray, Hit hit);
    // public Ray scatteredRay(Ray ray, Hit hit);
    // public Color attenuation(Ray ray, Hit hit);
    public Color emission();

    public Color albedos();

    public boolean gestreut();

    public Ray reflecRay(Ray ray, Hit hit);
}

/*
 * Die emission-Methode gibt immer Schwarz zurück, da diffuse Materialien kein
 * Licht selbst emittieren. 
 * Die albedos-Methode gibt die gespeicherte
 * Albedo-Farbe zurück. Die gestreut-Methode gibt immer true zurück, da diffuse
 * Materialien das Licht streuen. 
 * Die reflecRay-Methode enthält die Logik zur
 * Berechnung des reflektierten Strahls (die spezifische Implementierung ist
 * hier nicht gezeigt).
 */