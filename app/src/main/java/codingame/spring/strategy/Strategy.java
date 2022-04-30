package codingame.spring.strategy;

import codingame.spring.GameState;
import codingame.spring.entities.Hero;

public interface Strategy {

    void computeMove(Hero currentHero, GameState gameState);

}
