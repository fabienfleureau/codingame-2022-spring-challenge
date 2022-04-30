package codingame.spring;

import java.util.*;

import codingame.spring.entities.Entity;
import codingame.spring.entities.Hero;
import codingame.spring.utils.Point2D;
import codingame.spring.utils.Utils;

import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {


    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Initialization of game state
        long initStart = System.currentTimeMillis();
        
        GameState gameState = new GameState();
        gameState.initState(in);

        long initEnd = System.currentTimeMillis();
        System.err.println("Initialization duration: " + (initEnd-initStart));
        // game loop
        while (true) {
            long start = System.currentTimeMillis();
            gameState.newTurn(in);
           
            for (int i = 0; i < gameState.heroesPerPlayer; i++) {
                double selectedDistance = 999999999;
                Entity selectedMonster = null;
                Point2D selectedInterceptionPoint = null;
                double selectedDistanceBaseThreat = 999999999;
                Entity selectedMonsterBaseThreat = null;
                Point2D selectedInterceptionPointBaseThreat = null;
                Hero currentHero = gameState.myHeroes[i];
                
                    System.err.println(currentHero);
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
                    if (currentHero.patrolPoint == null || Utils.distance(currentHero.entity.getPosition(), gameState.map.patrolPoint1) < 1600) {
                        currentHero.patrolPoint = gameState.map.patrolPoint2;
                    } else if (Utils.distance(currentHero.entity.getPosition(), gameState.map.patrolPoint2) < 1600) {
                        currentHero.patrolPoint = gameState.map.patrolPoint1;
                    }
                    currentHero.targetPoint = currentHero.patrolPoint;
                }

                // In the first league: MOVE <x> <y> | WAIT; In later leagues: | SPELL <spellParams>;
                if (currentHero.target != null ) {
                    System.out.println("MOVE " + (int) currentHero.targetPoint.x + " " + (int) currentHero.targetPoint.y + " ATK");
                } else if (currentHero.patrolPoint != null) {
                    System.out.println("MOVE " + (int) currentHero.targetPoint.x + " " + (int) currentHero.targetPoint.y + " PAT");
                } else {
                    System.out.println("WAIT");
                }
            }
            
            long end = System.currentTimeMillis();
            System.err.println(end-start);
        }
    }


}