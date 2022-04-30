package codingame.spring.entities;

import codingame.spring.GameMap;
import codingame.spring.strategy.BasicStrategy;
import codingame.spring.strategy.ModifiablePatrolStrategy;
import codingame.spring.strategy.Role;
import codingame.spring.strategy.Strategy;
import codingame.spring.utils.Point2D;

public class Hero {
    public Entity entity;
    public Entity target;
    public Point2D targetPoint;
    public Point2D patrolPoint;
    public Role role;
    public Strategy strategy;

    public Hero(Entity entity, Role role, GameMap map) {
        this.entity = entity;
        this.role = role;
        this.strategy = new ModifiablePatrolStrategy(map, role);
    }
    public void printMove() {
        // In the first league: MOVE <x> <y> | WAIT; In later leagues: | SPELL <spellParams>;
        if (target != null ) {
            System.out.println("MOVE " + (int) targetPoint.x + " " + (int) targetPoint.y + " ATK");
        } else if (patrolPoint != null) {
            System.out.println("MOVE " + (int) targetPoint.x + " " + (int) targetPoint.y + " PAT");
        } else {
            System.out.println("WAIT");
        }
    }

    @Override
    public String toString() {
        return "Hero[x=" + entity.x + ",y=" + entity.y + ",vx=" + entity.vx + ",vy" + entity.vy + "]";
    }

}
