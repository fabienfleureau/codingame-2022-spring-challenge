package codingame.spring.entities;

import codingame.spring.GameMap;
import codingame.spring.utils.Point2D;

public class Entity {
    public int id;
    public int type; // 0=monster, 1=your hero, 2=opponent hero
    public int x; // Position of this entity
    public int y;
    public int shieldLife; // Ignore for this league; Count down until shield spell fades
    public int isControlled; // Ignore for this league; Equals 1 when this entity is under a control spell
    public int health; // Remaining health of this monster
    public int vx; // Trajectory of this monster
    public int vy;
    public int nearBase; // 0=monster with no target yet, 1=monster targeting a base
    public int threatFor; // Given this monster's trajectory, is it a threat to 1=your base, 2=your opponent's base, 0=neither
    public int lastSeenTurn;
    public boolean isTargeted = false;

    public Entity(int id) {
        this.id = id;
    }

    public boolean isMonster() {
        return type == 0;
    }

    public boolean isMyHero() {
        return type == 1;
    }

    public boolean isOpponentHero() {
        return type == 2;
    }


    public boolean isAlive(int currentTurn) {
        return lastSeenTurn == currentTurn;
    }

    public boolean isGoingToBase() {
        return nearBase == 1 && threatFor == 1;
    }

    public double getSpeed() {
        return 800;
    }

    public Point2D getPosition() {
        return new Point2D(x, y);
    }

    public Point2D getSpeedVector() {
        return new Point2D(vx, vy);
    }

    public boolean isBaseThreat(GameMap map) {
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