package cgg.a10;

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
