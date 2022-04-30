package codingame.spring;

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {


    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        GameMap map = new GameMap(in.nextInt(),in.nextInt());   
        int heroesPerPlayer = in.nextInt(); // Always 3
        GamePlayer[] players = new GamePlayer[2];
        players[0] = new GamePlayer();
        players[1] = new GamePlayer();
        Map<Integer, Entity> entityMap = new HashMap<>();
        Entity[] myHeroes = new Entity[3];
        int turn = 0;

        // game loop
        while (true) {
            turn++;

            long start = System.currentTimeMillis();
            for (int i = 0; i < 2; i++) {
                players[i].health = in.nextInt();
                players[i].mana = in.nextInt(); 
            }
            int entityCount = in.nextInt(); // Amount of heros and monsters you can see
            int myHeroesIndex = 0;
            for (int i = 0; i < entityCount; i++) {
                int id = in.nextInt(); // Unique identifier
                if (!entityMap.containsKey(id)) {
                    entityMap.put(id, new Entity());
                }
                Entity currentEntity = entityMap.get(id);
                currentEntity.type = in.nextInt(); // 0=monster, 1=your hero, 2=opponent hero
                currentEntity.x = in.nextInt(); // Position of this entity
                currentEntity.y = in.nextInt();
                currentEntity.shieldLife = in.nextInt(); // Ignore for this league; Count down until shield spell fades
                currentEntity.isControlled = in.nextInt(); // Ignore for this league; Equals 1 when this entity is under a control spell
                currentEntity.health = in.nextInt(); // Remaining health of this monster
                currentEntity.vx = in.nextInt(); // Trajectory of this monster
                currentEntity.vy = in.nextInt();
                currentEntity.nearBase = in.nextInt(); // 0=monster with no target yet, 1=monster targeting a base
                currentEntity.threatFor = in.nextInt(); // Given this monster's trajectory, is it a threat to 1=your base, 2=your opponent's base, 0=neither
                currentEntity.lastSeenTurn = turn;
                if (currentEntity.isMyHero()) {
                    myHeroes[myHeroesIndex++] = currentEntity;
                }
            }




            for (int i = 0; i < heroesPerPlayer; i++) {
                double selectedDistance = 999999999;
                Entity selectedMonster = null;
                Point2D selectedInterceptionPoint = null;
                double selectedDistanceBaseThreat = 999999999;
                Entity selectedMonsterBaseThreat = null;
                Point2D selectedInterceptionPointBaseThreat = null;
                Entity currentHero = myHeroes[i];
                
                    System.err.println(currentHero);
                // hastarget
                if (currentHero.target != null
                && currentHero.target.isAlive(turn)
                && Utils.distance(currentHero, currentHero.target) < 1600) {

                    currentHero.targetPoint = Utils.calculateInterceptionPoint(currentHero, currentHero.target);


                } else {
                    if (currentHero.target != null) {
                        currentHero.target.isTargeted = false;
                        currentHero.target = null;
                    }
                    for (Entity target : entityMap.values()) {
                        if (target.isMonster() && target.isAlive(turn) && !target.isTargeted) {
                            System.err.println(target);
                            Point2D interceptionPoint = Utils.calculateInterceptionPoint(currentHero, target);
                            System.err.println(interceptionPoint);

                            double currDist = Utils.distance(currentHero.getPosition(), interceptionPoint);
                            if (currDist < selectedDistance ) {
                                selectedDistance = currDist;
                                selectedMonster = target;
                                selectedInterceptionPoint = interceptionPoint;
                            }
                            if (currDist < selectedDistanceBaseThreat && target.isBaseThreat(map)) {
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
                    if (currentHero.patrolPoint == null || Utils.distance(currentHero.getPosition(), map.patrolPoint1) < 1600) {
                        currentHero.patrolPoint = map.patrolPoint2;
                    } else if (Utils.distance(currentHero.getPosition(), map.patrolPoint2) < 1600) {
                        currentHero.patrolPoint = map.patrolPoint1;
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