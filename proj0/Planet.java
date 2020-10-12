import static java.lang.StrictMath.sqrt;

public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV , double yV
    , double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }


    /**
     * Return the distance between this Planet and b.
     * @param b
     * @return dis
     */
    public double calcDistance(Planet b){
        double dx = b.xxPos - this.xxPos;
        double dy = b.yyPos - this.yyPos;
        double dis = sqrt(dx * dx + dy * dy);
        return dis;
    }

    /**
     * Return the gravitational force of b.
     * @param b
     * @return F
     */
    public double calcForceExertedBy(Planet b){

        double r = calcDistance(b);
        double F = G * this.mass * b.mass
                / (r * r);
        return F;
    }

    /**
     * Return the gravitational force of b in direction x.
     * @param b
     * @return Fx
     */
    public double calcForceExertedByX(Planet b){
        double r = calcDistance(b);
        double dx = b.xxPos - this.xxPos;
        double Fx = calcForceExertedBy(b) * dx / r ;
        return Fx;
    }

    /**
     * Return the gravitational force of b in direction y.
     * @param b
     * @return Fy
     */
    public double calcForceExertedByY(Planet b){
        double r = calcDistance(b);
        double dy = b.yyPos - this.yyPos;
        double Fy = calcForceExertedBy(b) * dy / r ;
        return Fy;
    }

    /**
     * Return the gravitational force of all planets except itself.
     * @param planets
     * @return netFx
     */
    public double calcNetForceExertedByX(Planet[] planets){
        double netFx = 0;
        for (int i = 0; i < planets.length; i++) {
            if(!this.equals(planets[i])){
                netFx += calcForceExertedByX(planets[i]);
            }
        }
        return netFx;
    }

    /**
     * Return the gravitational force of all planets except itself.
     * @param planets
     * @return netFy
     */
    public double calcNetForceExertedByY(Planet[] planets){
        double netFy = 0;
        for (int i = 0; i < planets.length; i++) {
            if(!this.equals(planets[i])){
                netFy += calcForceExertedByY(planets[i]);
            }

        }
        return netFy;
    }
    public void update(double dt, double fX, double fY){
        double ax = fX / mass;
        double ay = fY / mass;
        xxVel += ax * dt;
        yyVel += ay * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw(){
        String filename = "./images/"+this.imgFileName;
        StdDraw.picture(this.xxPos,this.yyPos,filename);
    }
}
