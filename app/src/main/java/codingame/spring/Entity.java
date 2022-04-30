package codingame.spring;

class Entity {
    int type; // 0=monster, 1=your hero, 2=opponent hero
    int x; // Position of this entity
    int y;
    int shieldLife; // Ignore for this league; Count down until shield spell fades
    int isControlled; // Ignore for this league; Equals 1 when this entity is under a control spell
    int health; // Remaining health of this monster
    int vx; // Trajectory of this monster
    int vy;
    int nearBase; // 0=monster with no target yet, 1=monster targeting a base
    int threatFor; // Given this monster's trajectory, is it a threat to 1=your base, 2=your opponent's base, 0=neither
    int lastSeenTurn;
    boolean isTargeted = false;



    boolean isMyHero() {
        return type == 1;
    }

    boolean isMonster() {
        return type == 0;
    }

    boolean isAlive(int currentTurn) {
        return lastSeenTurn == currentTurn;
    }

    boolean isGoingToBase() {
        return nearBase == 1 && threatFor == 1;
    }

    double getSpeed() {
        return 800;
    }

    Point2D getPosition() {
        return new Point2D(x, y);
    }

    Point2D getSpeedVector() {
        return new Point2D(vx, vy);
    }

    boolean isBaseThreat(GameMap map) {
        double a = vy/vx;
        double b = y - a * x;
        if (map.baseX == 0) {
            double y0 = b;
            double x0 = -b / a;
            return (y0 > 0 && y0 < 5000) || (x0 > 0 && x0 < 5000);
        } else {
            double yMax = a * map.baseX + b;
            double xMax = (map.baseY - b) / a;
            return (map.baseX - xMax > 0 && map.baseX - xMax <  5000) || (map.baseY - yMax > 0 && map.baseY - yMax <  5000);
        }
    }

    @Override
    public String toString() {
        return "Entity[type=" + type + ",x=" + x + ",y=" + y + ",vx=" + vx + ",vy" + vy + "]";
    }
}