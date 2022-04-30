package codingame.spring.entities;

import codingame.spring.GameMap;
import codingame.spring.strategy.BasicStrategy;
import codingame.spring.strategy.ModifiablePatrolStrategy;
import codingame.spring.strategy.Patrol;
import codingame.spring.strategy.Role;
import codingame.spring.strategy.Strategy;
import codingame.spring.utils.Point2D;
import codingame.spring.utils.Utils;

public class Hero {
    public Entity entity;
    public Entity target;
    public Point2D targetPoint;
    public Point2D patrolPoint;
    public Role role;
    public Strategy strategy;
    public Patrol patrol;
    public Action action;
    public int damage = 2;

    public Hero(Entity entity, Role role, GameMap map) {
        this.entity = entity;
        this.role = role;
        if (role == Role.DEFENDER) {
            this.patrol = map.basePatrol;
        } else {
            this.patrol = map.farmPatrol;
        }
        this.strategy = new ModifiablePatrolStrategy(map, role);
    }
    public void printMove() {
        // In the first league: MOVE <x> <y> | WAIT; In later leagues: | SPELL <spellParams>;
        if (target != null || patrolPoint != null) {
            System.out.println("MOVE " + (int) targetPoint.x + " " + (int) targetPoint.y + " " + role + "_" + entity.id + " " + action);
        } else {
            System.out.println("WAIT " + entity.id);
        }
    }

    @Override
    public String toString() {
        return "Hero[id=" + entity.id + ",x=" + entity.x + ",y=" + entity.y +  "]";
    }


    public void target(Entity monster) {
        action = Action.ATK;
        target = monster;
        monster.targetedBy(this);
    }

    public void patrol() {
        action = Action.PAT;
        target = null;

    }
    public void reset() {
        targetPoint = null;
        target = null;
    }

    public boolean isAssigned() {
        return target != null;
    }
    public void updateTargetPoint() {
        if (action == Action.ATK) {
            targetPoint = Utils.calculateInterceptionPoint(this.entity, target);
        } else if (action == Action.PAT) {
            if (patrolPoint == null || Utils.distance(entity.getPosition(), patrol.point1) < 1600) {
                patrolPoint = patrol.point2;
            } else if (Utils.distance(entity.getPosition(), patrol.point2) < 1600) {
                patrolPoint = patrol.point1;
            }
            targetPoint = patrolPoint;
        }
    }

}
