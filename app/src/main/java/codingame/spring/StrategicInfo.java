package codingame.spring;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import codingame.spring.entities.Entity;
import codingame.spring.utils.Utils;

public class StrategicInfo {

    public List<Entity> top3Threats;

    public void computeTop3Threats(GameState gameState) {
        top3Threats = gameState.entityMap.values().stream()
        .filter( entity -> entity.isMonster() && entity.isAlive(gameState.turn))
        .sorted(new Comparator<Entity>() {
            @Override
            public int compare(Entity arg0, Entity arg1) {
                if (arg0.isBaseThreat(gameState.map) && !arg1.isBaseThreat(gameState.map)) {
                    return 1;
                } else if (!arg0.isBaseThreat(gameState.map) && arg1.isBaseThreat(gameState.map)) {
                    return -1;
                }
                return (int) (Utils.distance(arg0.getPosition(), gameState.map.base) - Utils.distance(arg1.getPosition(), gameState.map.base));
            }
            
        })
        .limit(3)
        .collect(Collectors.toList());
    }

}
