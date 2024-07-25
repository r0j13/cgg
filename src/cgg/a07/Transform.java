package cgg.a07;

import cgtools.Matrix;

public class Transform {
    Matrix takelocalMatrix;
    Matrix localMatrix;
    Matrix realMatrix;
    

    public Transform(Matrix takelocalMatrix) {
        this.takelocalMatrix = takelocalMatrix;
        this.localMatrix = Matrix.invert(takelocalMatrix);
        

    }
}
