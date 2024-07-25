package cgg.a07;

import cgtools.Color;

public interface Material {

   // public Color emittedRadiance(Ray ray, Hit hit);
    //public Ray scatteredRay(Ray ray, Hit hit);
    //public Color attenuation(Ray ray, Hit hit);
   public Color emission();
    public Color albedos();
    public boolean gestreut();
    public Ray reflecRay(Ray ray, Hit hit);
}
