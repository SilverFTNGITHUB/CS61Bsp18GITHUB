
public class NBody {

	/**
	 * 
	 * @param strInput
	 */
	public static void main(String[] strInput) {
		double T = Double.parseDouble(strInput[0]);
		double dt = Double.parseDouble(strInput[1]);
		String fileName = strInput[2];
		double dRadius = readRadius(fileName);
		int nPlanetsNum = readPlanets(fileName).length;
		Planet[] planets = new Planet[nPlanetsNum];
		planets = readPlanets(fileName);
		StdDraw.setScale(-dRadius, dRadius);
		StdDraw.picture(0, 0, "./images/starfield.jpg", 2 * dRadius, 2 * dRadius);
		StdDraw.enableDoubleBuffering();// When double buffering is enabled by calling enableDoubleBuffering(), all
										// drawing takes place on the offscreen canvas. The offscreen canvas is not
										// displayed. Only when you call show() does your drawing get copied from the
										// offscreen canvas to the onscreen canvas, where it is displayed in the
										// standard drawing window.
		double dTime = 0;
		while (dTime < T) {
			double[] xForces = new double[nPlanetsNum];
			double[] yForces = new double[nPlanetsNum];
			for (int i = 0; i < nPlanetsNum; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for (int i = 0; i < nPlanetsNum; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.clear();
			StdDraw.picture(0, 0, "./images/starfield.jpg", 2 * dRadius, 2 * dRadius);
			for (int i = 0; i < nPlanetsNum; i++) {
				StdDraw.picture(planets[i].xxPos, planets[i].yyPos, "./images/" + planets[i].imgFileName);
			}
			StdDraw.show();
			StdDraw.pause(1);
			dTime += dt;
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", dRadius);
		for (int i = 0; i < planets.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
		                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}

	/**
	 * 
	 * @param fileName
	 * @return (double)Radius in the file
	 */
	public static double readRadius(String fileName) {
		double dRadiusRet = 0;
		In in = new In(fileName);
		String dump = in.readString();
		dRadiusRet = in.readDouble();
		return dRadiusRet;
	}

	/**
	 * 
	 * @param fileName
	 * @return array of Planets corresponding to the planets in fileName
	 */
	public static Planet[] readPlanets(String fileName) {
		In in = new In(fileName);
		int N = in.readInt();
		Planet[] planets = new Planet[N];
		String dump = in.readString();
		for (int i = 0; i < N; i++) {
			Planet onePlanet = new Planet(0, 0, 0, 0, 0, "");
			onePlanet.xxPos = in.readDouble();
			onePlanet.yyPos = in.readDouble();
			onePlanet.xxVel = in.readDouble();
			onePlanet.yyVel = in.readDouble();
			onePlanet.mass = in.readDouble();
			onePlanet.imgFileName = in.readString();
			// Planet onePlanet = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
			// onePlanet.xxPos = 1.2e11;
			planets[i] = onePlanet;
		}
		return planets;
	}
}
