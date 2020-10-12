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
