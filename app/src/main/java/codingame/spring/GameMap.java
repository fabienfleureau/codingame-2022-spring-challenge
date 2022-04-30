package codingame.spring;

import codingame.spring.utils.Point2D;

public class GameMap {
    int maxX=17630;
    int maxY=9000; 

    // The corner of the map representing your base
    public int baseX;
    public int baseY;
    public Point2D patrolPoint1;
    public Point2D patrolPoint2;

    public GameMap(int x, int y) {
        this.baseX = x;
        this.baseY = y;
        if (baseX == 0) {
            patrolPoint1 = new Point2D(2000, 6000);
        } else {
            patrolPoint1 = new Point2D(baseX - 2000 , baseY - 6000);
        }
        if (baseX == 0) {
            patrolPoint2 = new Point2D(6000, 2000);
        } else {
            patrolPoint2 = new Point2D(baseX - 6000 , baseY - 2000);
        }
    }

    @Override
    public String toString() {
        return "GameMap[baseX=" + baseX + ", baseY=" + baseY + "]";
    }   
}
