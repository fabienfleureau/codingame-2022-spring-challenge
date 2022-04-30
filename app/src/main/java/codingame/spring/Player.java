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
                Hero currentHero = gameState.myHeroes[i];
                System.err.println(currentHero);
                currentHero.strategy.computeMove(currentHero, gameState);
                
            }
            
            for (int i = 0; i < gameState.heroesPerPlayer; i++) {
                Hero currentHero = gameState.myHeroes[i];
                currentHero.printMove();
            }
            
            long end = System.currentTimeMillis();
            System.err.println(end-start);
        }
    }


}