public class NBody {
    /**
     * Return the radius of the universe.
     * @param path
     * @return
     */
    public static double readRadius(String path){
        In in = new In(path);
        in.readDouble();
        double val = in.readDouble();
        in.close();
        return val;
    }

    /**
     * Return an array of planets which was reading from the file.
     * @param path
     * @return
     */
    public static Planet[] readPlanets(String path){
        In in = new In(path);
        int num = in.readInt();
        in.readDouble();
        Planet[] planets = new Planet[num];
        double xxPos;
        double yyPos;
        double xxVel;
        double yyVel;
        double mass;
        String imgFileName;
        for (int i = 0; i <num ; i++) {
            xxPos = in.readDouble();
            yyPos = in.readDouble();
            xxVel = in.readDouble();
            yyVel = in.readDouble();
            mass = in.readDouble();
            imgFileName = in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        in.close();
        return planets;
    }

    public static void main(String[] args) {
        In in = new In();
        double T = in.readDouble();
        double dt = in.readDouble();
        String filename = in.readString();
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        setStarField(radius);
        drawAllPlanets(planets);

        StdDraw.enableDoubleBuffering();

        double valOfTime = 0;
        while (valOfTime <= T){
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt,xForces[i],yForces[i]);
            }
            setStarField(radius);
            drawAllPlanets(planets);
            StdDraw.show();
            StdDraw.pause(10);
            valOfTime += dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
    public static void drawAllPlanets(Planet[] planets){
        for (int i = 0; i < planets.length; i++) {
            planets[i].draw();
        }
    }
    public static void setStarField(double radius){
        StdDraw.setScale(-radius,radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "./images/starfield.jpg");
        StdDraw.show();
    }

}
