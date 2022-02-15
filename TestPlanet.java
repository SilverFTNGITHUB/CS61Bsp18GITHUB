
public class TestPlanet {
	public static void main(String[] args) {
		Planet sun = new Planet(1.0e12, 2.0e11, 0, 0, 2.0e30, "");
		Planet saturn = new Planet(2.3e12, 9.5e11, 0, 0, 6.0e26, "");
		double distance = sun.calcDistance(saturn);
		System.out.println(distance);
		double force = sun.calcForceExertedBy(saturn);
		System.out.println(force);
		double f1x = sun.calcForceExertedByX(saturn);
		double f1y = sun.calcForceExertedBy(saturn);
		System.out.println(f1x + ", " + f1y);
		double f2x = saturn.calcForceExertedByX(sun);
		double f2y = saturn.calcForceExertedBy(sun);
		System.out.println(f2x + ", " + f2y);
		Planet samh = new Planet(1, 0, 0, 0, 10, "");
		Planet aegir = new Planet(3, 3, 0, 0, 5, "");
		Planet rocinante = new Planet(5, -3, 0, 0, 50, "");
		Planet[] allPlanets = { samh, aegir, rocinante };
		double dFnet_x = samh.calcNetForceExertedByX(allPlanets);
		double dFnet_y = samh.calcNetForceExertedByY(allPlanets);
		System.out.println(dFnet_x + ", " + dFnet_y);
		Planet squirrel = new Planet(0, 0, 3, 5, 1, "");
		squirrel.update(1, -5, -2);
		System.out.println(squirrel.xxPos + ", " + squirrel.yyPos);
		System.out.println(squirrel.xxVel + ", " + squirrel.yyVel);
	}
}
