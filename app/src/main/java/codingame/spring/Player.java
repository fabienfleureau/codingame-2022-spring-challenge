package codingame.spring;

import java.util.*;

import codingame.spring.entities.Entity;
import codingame.spring.entities.Hero;
import codingame.spring.strategy.BasicMultiHeroStrategy;
import codingame.spring.strategy.EliminateThreatsStrategy;
import codingame.spring.strategy.MultiHeroStrategy;
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
        // MultiHeroStrategy strategy = new BasicMultiHeroStrategy();
        MultiHeroStrategy strategy = new EliminateThreatsStrategy();
        while (true) {
            long start = System.currentTimeMillis();
            gameState.newTurn(in);
           
            strategy.computeMoves(gameState);
            
            for (int i = 0; i < gameState.heroesPerPlayer; i++) {
                gameState.myHeroes[i].printMove();
            }
            
            long end = System.currentTimeMillis();
            System.err.println(end-start);
        }
    }


}