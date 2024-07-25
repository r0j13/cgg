package cgg.a07;

import cgtools.Point;

public class Robot implements Shape {
    private final Cylinder leftArm;
    private final Cylinder rightArm;
    private final Cylinder leftLeg;
    private final Cylinder rightLeg;
    private final Cylinder torso;
    private final Sphere head;

    /*
     * Der Konstruktor erstellt einen Roboter mit der angegebenen Basisposition
     * (basePosition), Radien und Höhen für die Arme, Beine und den Torso sowie
     * einem Material (mat).
     * Die Positionen der Arme, Beine, des Torsos und des Kopfes werden basierend
     * auf der Basisposition berechnet.
     * Dann werden die Körperteile als Zylinder (Cylinder) und Kugel (Sphere)
     * instanziiert.
     */
    public Robot(Point basePosition, double armRadius, double legRadius, double torsoRadius, double armHeight,
            double legHeight, double torsoHeight, Material mat) {
        // Define the positions for the arms, legs, and torso based on the base position
        Point leftArmPos = new Point(basePosition.x() - armRadius - torsoRadius, basePosition.y() + 0.1 + torsoHeight,
                basePosition.z());
        Point rightArmPos = new Point(basePosition.x() + armRadius + torsoRadius, basePosition.y() + 0.1 + torsoHeight,
                basePosition.z());
        Point leftLegPos = new Point(basePosition.x() - legRadius, basePosition.y(), basePosition.z());
        Point rightLegPos = new Point(basePosition.x() + legRadius, basePosition.y(), basePosition.z());
        Point torsoPos = new Point(basePosition.x(), basePosition.y() + legHeight, basePosition.z());
        Point headPos = new Point(basePosition.x(), basePosition.y() + torsoHeight + legHeight + 0.05,
                basePosition.z());
        // Create the cylinders
        this.leftArm = new Cylinder(leftArmPos, armRadius, armHeight, mat);
        this.rightArm = new Cylinder(rightArmPos, armRadius, armHeight, mat);
        this.leftLeg = new Cylinder(leftLegPos, legRadius, legHeight, mat);
        this.rightLeg = new Cylinder(rightLegPos, legRadius, legHeight, mat);
        this.torso = new Cylinder(torsoPos, torsoRadius, torsoHeight, mat);
        this.head = new Sphere(torsoRadius + 0.05, headPos, mat);
    }

    // Getters for the body parts
    public Cylinder getLeftArm() {
        return leftArm;
    }

    public Cylinder getRightArm() {
        return rightArm;
    }

    public Cylinder getLeftLeg() {
        return leftLeg;
    }

    public Cylinder getRightLeg() {
        return rightLeg;
    }

    public Cylinder getTorso() {
        return torso;
    }

    public Sphere getHead() {
        return head;
    }

    /*
     * Diese Methode berechnet die Schnittpunkte eines Strahls (Ray) mit allen
     * Körperteilen des Roboters.
     * Sie ruft die intersect-Methode jedes Körperteils auf, um herauszufinden, ob
     * und wo der Strahl auf das jeweilige Körperteil trifft.
     * Die Methode gibt den nächstgelegenen Trefferpunkt (den mit dem kleinsten
     * t-Wert) zurück.
     */
    @Override
    public Hit intersect(Ray ray) {
        Hit leftArmHit = leftArm.intersect(ray);
        Hit rightArmHit = rightArm.intersect(ray);
        Hit leftLegHit = leftLeg.intersect(ray);
        Hit rightLegHit = rightLeg.intersect(ray);
        Hit torsoHit = torso.intersect(ray);
        Hit headHit = head.intersect(ray);

        return closestHit(leftArmHit, rightArmHit, leftLegHit, rightLegHit, torsoHit, headHit);
    }

    /*
     * Diese Methode findet den nächsten Trefferpunkt aus einer Liste von
     * Trefferpunkten (Hit-Objekten).
     * Sie iteriert durch die Liste der Trefferpunkte und vergleicht die t-Werte, um
     * den nächsten Trefferpunkt zu finden und zurückzugeben.
     */
    private Hit closestHit(Hit... hits) {
        Hit closest = null;
        for (Hit hit : hits) {
            if (hit != null && (closest == null || hit.t() < closest.t())) {
                closest = hit;
            }
        }
        return closest;
    }
}
