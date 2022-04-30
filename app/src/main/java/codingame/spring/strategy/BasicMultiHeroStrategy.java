package codingame.spring.strategy;

import codingame.spring.GameState;
import codingame.spring.entities.Hero;

public class BasicMultiHeroStrategy implements MultiHeroStrategy {

    @Override
    public void computeMoves(GameState gameState) {
        for (int i = 0; i < gameState.heroesPerPlayer; i++) {
            Hero currentHero = gameState.myHeroes[i];
            System.err.println(currentHero);
            currentHero.strategy.computeMove(currentHero, gameState);
                
        }
    }
    

}
