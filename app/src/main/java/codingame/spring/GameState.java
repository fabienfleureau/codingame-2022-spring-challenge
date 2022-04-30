package codingame.spring;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GameState {

    public GameMap map;
    public int heroesPerPlayer; // Always 3
    public GamePlayer[] players = new GamePlayer[2];
    public GamePlayer me;
    public GamePlayer ennemy;
    public Map<Integer, Entity> entityMap = new HashMap<>();
    public Hero[] myHeroes = new Hero[3];
    public int turn = 0;


    public void initState(Scanner in) {
        map = new GameMap(in.nextInt(),in.nextInt());   
        heroesPerPlayer = in.nextInt();
        players[0] = new GamePlayer();
        players[1] = new GamePlayer();
    }

    public void newTurn(Scanner in) {
        turn++;
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
                if (myHeroes[myHeroesIndex] == null) {
                    myHeroes[myHeroesIndex++] = new Hero(currentEntity);
                }
            }
        }


    }
    
}
