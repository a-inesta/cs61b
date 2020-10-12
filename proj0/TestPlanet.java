public class TestPlanet {
    public static void main(String[] args) {
        checkPairwiseForce();
    }
    private static void checkEquals(double actual, double expected, String label, double eps) {
        if (Math.abs(expected - actual) <= eps * Math.max(expected, actual)) {
            System.out.println("PASS: " + label + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label + ": Expected " + expected + " and you gave " + actual);
        }
    }
    private static void checkPairwiseForce(){
        System.out.println("Checking calcForceExertedBy...");

        Planet p1 = new Planet(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Planet p2 = new Planet(2.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");

        checkEquals(p1.calcForceExertedBy(p2), 1.6675e-9, "calcForceExertedBy()", 0.01);
        checkEquals(p2.calcForceExertedBy(p1), 1.6675e-9, "calcForceExertedBy()", 0.01);
    }
}
