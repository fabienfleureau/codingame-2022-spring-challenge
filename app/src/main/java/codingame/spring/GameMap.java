package codingame.spring;

import codingame.spring.strategy.Patrol;
import codingame.spring.utils.Point2D;

public class GameMap {
    int maxX=17630;
    int maxY=9000; 

    // The corner of the map representing your base
    public int baseX;
    public int baseY;
    public Patrol farmPatrol;
    public Patrol basePatrol;

    public GameMap(int x, int y) {
        this.baseX = x;
        this.baseY = y;
        farmPatrol = new Patrol();
        basePatrol = new Patrol();
        if (baseX == 0) {
            farmPatrol.point1 = new Point2D(2000, 6000);
            farmPatrol.point2 = new Point2D(6000, 2000);
            basePatrol.point1 = new Point2D(1500, 3000);
            basePatrol.point2 = new Point2D(3000, 1500);
        } else {
            farmPatrol.point1 = new Point2D(baseX - 2000 , baseY - 6000);
            farmPatrol.point2 = new Point2D(baseX - 6000 , baseY - 2000);
            basePatrol.point1 = new Point2D(baseX - 1500 , baseY - 3000);
            basePatrol.point2 = new Point2D(baseX - 3000 , baseY - 1500);
        }

    }

    @Override
    public String toString() {
        return "GameMap[baseX=" + baseX + ", baseY=" + baseY + "]";
    }   
}
