
public class Planet {
	public static final double G = 6.67e-11;
	public double xxPos = 0, yyPos = 0, xxVel = 0, yyVel = 0, mass = 0;
	public String imgFileName = "";

	/**
	 * Constructor
	 * 
	 * @param xP
	 * @param yP
	 * @param xV
	 * @param yV
	 * @param m
	 * @param img
	 */
	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	/**
	 * Constructor (clone)
	 * 
	 * @param p
	 */
	public Planet(Planet p) {
		// this = p;
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
	}

	/**
	 * 
	 * @param otherOne
	 * @return (double) distance between this planet and otherOne
	 */
	public double calcDistance(Planet otherOne) {
		double dDisRet = 0;
		dDisRet = Math.sqrt((this.xxPos - otherOne.xxPos) * (this.xxPos - otherOne.xxPos)
				+ (this.yyPos - otherOne.yyPos) * (this.yyPos - otherOne.yyPos));
		return dDisRet;
	}

	/**
	 * 
	 * @param givenPlanet
	 * @return (double) force exerted by givenPlanet on this planet
	 */
	public double calcForceExertedBy(Planet givenPlanet) {
		if (this.equals(givenPlanet)) {
			return 0;
		}
		double dForceRet = 0;
		double distance = this.calcDistance(givenPlanet);
		if (distance != 0) {
			dForceRet = (G * this.mass * givenPlanet.mass) / (distance * distance);
		}
		return dForceRet;
	}

	/**
	 * 
	 * @param givenPlanet
	 * @return (double) force exerted on this planet in the Y direction by
	 *         givenPlanet
	 */
	public double calcForceExertedByX(Planet givenPlanet) {
		if (this.equals(givenPlanet)) {
			return 0;
		}
		double dForceXRet = 0;
		double distance = this.calcDistance(givenPlanet);
		double Force = this.calcForceExertedBy(givenPlanet);
		if (distance != 0) {
			dForceXRet = Force * (givenPlanet.xxPos - this.xxPos) / distance;
		}
		return dForceXRet;
	}

	/**
	 * 
	 * @param givenPlanet
	 * @return (double) force exerted on this planet in the X direction by
	 *         givenPlanet
	 */
	public double calcForceExertedByY(Planet givenPlanet) {
		if (this.equals(givenPlanet)) {
			return 0;
		}
		double dForceYRet = 0;
		double distance = this.calcDistance(givenPlanet);
		double Force = this.calcForceExertedBy(givenPlanet);
		if (distance != 0) {
			dForceYRet = Force * (givenPlanet.yyPos - this.yyPos) / distance;
		}
		return dForceYRet;
	}

	/**
	 * 
	 * @param planets an array of Planets
	 * @return calculate the net X force exerted by all planets in that array upon
	 *         the current Planet
	 */
	public double calcNetForceExertedByX(Planet[] planets) {
		double dSumForceXRet = 0;
		for (int i = 0; i < planets.length; i++) {
			dSumForceXRet += this.calcForceExertedByX(planets[i]);
		}
		return dSumForceXRet;
	}

	/**
	 * 
	 * @param planets an array of Planets
	 * @return calculate the net Y force exerted by all planets in that array upon
	 *         the current Planet
	 */
	public double calcNetForceExertedByY(Planet[] planets) {
		double dSumForceYRet = 0;
		for (int i = 0; i < planets.length; i++) {
			dSumForceYRet += this.calcForceExertedByY(planets[i]);
		}
		return dSumForceYRet;
	}

	/**
	 * update status of this planet (after certain force and time)
	 * @param dt
	 * @param forceX
	 * @param forceY
	 * @return true-normal false-error (mass == 0)
	 */
	public boolean update(double dt, double forceX, double forceY) {
		if (this.mass == 0)
			return false;
		this.xxVel += dt * (forceX / this.mass);
		this.yyVel += dt * (forceY / this.mass);
		this.xxPos += dt * xxVel;
		this.yyPos += dt * yyVel;
		return true;
	}
	

}
