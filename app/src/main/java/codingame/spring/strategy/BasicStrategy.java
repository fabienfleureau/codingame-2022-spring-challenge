package codingame.spring.strategy;

import codingame.spring.GameState;
import codingame.spring.entities.Entity;
import codingame.spring.entities.Hero;
import codingame.spring.utils.Point2D;
import codingame.spring.utils.Utils;

public class BasicStrategy implements Strategy {

    public void computeMove(Hero currentHero, GameState gameState) {
        double selectedDistance = 999999999;
        Entity selectedMonster = null;
        Point2D selectedInterceptionPoint = null;
        double selectedDistanceBaseThreat = 999999999;
        Entity selectedMonsterBaseThreat = null;
        Point2D selectedInterceptionPointBaseThreat = null;
        
        // hastarget
        if (currentHero.target != null
        && currentHero.target.isAlive(gameState.turn)
        && Utils.distance(currentHero.entity, currentHero.target) < 1600) {

            currentHero.targetPoint = Utils.calculateInterceptionPoint(currentHero.entity, currentHero.target);


        } else {
            if (currentHero.target != null) {
                currentHero.target.isTargeted = false;
                currentHero.target = null;
            }
            for (Entity target : gameState.entityMap.values()) {
                if (target.isMonster() && target.isAlive(gameState.turn) && !target.isTargeted) {
                    System.err.println(target);
                    Point2D interceptionPoint = Utils.calculateInterceptionPoint(currentHero.entity, target);
                    System.err.println(interceptionPoint);

                    double currDist = Utils.distance(currentHero.entity.getPosition(), interceptionPoint);
                    if (currDist < selectedDistance ) {
                        selectedDistance = currDist;
                        selectedMonster = target;
                        selectedInterceptionPoint = interceptionPoint;
                    }
                    if (currDist < selectedDistanceBaseThreat && target.isBaseThreat(gameState.map)) {
                        selectedDistanceBaseThreat = currDist;
                        selectedMonsterBaseThreat = target;
                        selectedInterceptionPointBaseThreat = interceptionPoint;
                    }
                }

            }
            if (selectedMonsterBaseThreat != null) {
                currentHero.target = selectedMonsterBaseThreat;
                currentHero.targetPoint = selectedInterceptionPointBaseThreat;
                selectedMonsterBaseThreat.isTargeted = true;
            } else if (selectedMonster != null) {
                currentHero.target = selectedMonster;
                currentHero.targetPoint = selectedInterceptionPoint;
                selectedMonster.isTargeted = true;
            }
        }
        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");
        if (currentHero.target == null ) {
            if (currentHero.patrolPoint == null || Utils.distance(currentHero.entity.getPosition(), gameState.map.farmPatrol.point1) < 1600) {
                currentHero.patrolPoint = gameState.map.farmPatrol.point2;
            } else if (Utils.distance(currentHero.entity.getPosition(), gameState.map.farmPatrol.point2) < 1600) {
                currentHero.patrolPoint = gameState.map.farmPatrol.point1;
            }
            currentHero.targetPoint = currentHero.patrolPoint;
        }
    }
    
}
