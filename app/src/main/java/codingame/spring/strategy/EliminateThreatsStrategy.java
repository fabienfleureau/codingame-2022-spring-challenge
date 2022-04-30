package codingame.spring.strategy;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import codingame.spring.GameState;
import codingame.spring.entities.Action;
import codingame.spring.entities.Entity;
import codingame.spring.entities.Hero;
import codingame.spring.utils.Point2D;
import codingame.spring.utils.Utils;

public class EliminateThreatsStrategy implements MultiHeroStrategy {

    public void computeMoves(GameState gameState) {
        Hero[] heroes = gameState.myHeroes;


        
        for (int i=0; i<=2 ; i++) {
            if (heroes[i].action == Action.ATK && !heroes[i].target.isAlive(gameState.turn)) {
                heroes[i].reset();
                System.err.println("Reset Hero:" + heroes[i]);
            }
        }
        for (Entity monster : gameState.strategicInfo.top3Threats) {
            System.err.println("threat: " + monster);
            Hero selectedHero = null;
            Double distance = Double.MAX_VALUE;
            for (int i=0; i<=2 ; i++) {
                Hero hero = heroes[i];
                if (!heroes[i].isAssigned()) {
                    Point2D interceptionPoint = Utils.calculateInterceptionPoint(heroes[i].entity, monster);
                    Double distanceTmp = Utils.distance(heroes[i].entity.getPosition(), interceptionPoint);
                    System.err.println("distance:" + distanceTmp + " for " + hero);
                    if (distanceTmp < distance) {
                        distance = distanceTmp;
                        selectedHero = heroes[i];
                    }
                }
            }
            if (selectedHero != null) {
                selectedHero.target(monster);
            }
        }
        for (int i=0; i<=2 ; i++) {
            if (!heroes[i].isAssigned()) {
                heroes[i].patrol();
            }
            heroes[i].updateTargetPoint();
        }
    }

}
