package codingame.spring;

class Utils {

    static Double fastSqrt(int d) {
        return Double.longBitsToDouble( ( ( Double.doubleToLongBits( Double.valueOf(d) )-(1l<<52) )>>1 ) + ( 1l<<61 ) );
    }

    static Double fastSqrt(double d) {
        return Double.longBitsToDouble( ( ( Double.doubleToLongBits( d )-(1l<<52) )>>1 ) + ( 1l<<61 ) );
    }

    
    static int sqpow(int val) {
        return val * val;
    }

    static double sqpow(double val) {
        return val * val;
    }


    static Double distance(Entity entity1, Entity entity2) {
        return Utils.fastSqrt(sqpow(entity2.x - entity1.x) + sqpow(entity2.y - entity1.y));
    }

    static Double distance(Point2D entity1, Point2D entity2) {
        return Utils.fastSqrt(sqpow(entity2.x - entity1.x) + sqpow(entity2.y - entity1.y));
    }

    public static Point2D calculateInterceptionPoint(Entity hero, Entity monster) {
        return calculateInterceptionPoint(monster.getPosition(), monster.getSpeedVector(), hero.getPosition(), hero.getSpeed());
    }

    public static Point2D calculateInterceptionPoint(final Point2D a, final Point2D v, final Point2D b, final double s) {
		final double ox = a.getX() - b.getX();
		final double oy = a.getY() - b.getY();
 
		final double h1 = v.getX() * v.getX() + v.getY() * v.getY() - s * s;
		final double h2 = ox * v.getX() + oy * v.getY();
		double t;
		if (h1 == 0) { // problem collapses into a simple linear equation 
			t = -(ox * ox + oy * oy) / (2*h2);
		} else { // solve the quadratic equation
			final double minusPHalf = -h2 / h1;
 
			final double discriminant = minusPHalf * minusPHalf - (ox * ox + oy * oy) / h1; // term in brackets is h3
			if (discriminant < 0) { // no (real) solution then...
				return null;
			}
 
			final double root = Math.sqrt(discriminant);
 
			final double t1 = minusPHalf + root;
			final double t2 = minusPHalf - root;
 
			final double tMin = Math.min(t1, t2);
			final double tMax = Math.max(t1, t2);
 
			t = tMin > 0 ? tMin : tMax; // get the smaller of the two times, unless it's negative
			if (t < 0) { // we don't want a solution in the past
				return null;
			}
		}
 
		// calculate the point of interception using the found intercept time and return it
		return new Point2D(a.getX() + t * v.getX(), a.getY() + t * v.getY());
	}

}