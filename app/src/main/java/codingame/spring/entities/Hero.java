package codingame.spring.entities;

import codingame.spring.strategy.Strategy;
import codingame.spring.utils.Point2D;

public class Hero {
    public Entity entity;
    public Entity target;
    public Point2D targetPoint;
    public Point2D patrolPoint;
    public Strategy strategy;
    public Hero(Entity entity) {
        this.entity = entity;
    }

}
