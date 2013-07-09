package com.austingantner.codingchallengerewritelib;

import java.util.Random;

/**
 *
 * @author austingantner
 */
public class Simulator {

    public static Random rand = new Random();

    static public Player RandomPlayer() {
        Player TempPlayer = new Player();
        //TempPlayer.ID = ID;
        //The random limits of these variables are proportional to each other in
        //ways that make the data look better.  They don't neccessarily
        //have any real meaning to how they are generated.
        TempPlayer.attacks = rand.nextInt(200);
        TempPlayer.assists = rand.nextInt(25);
        TempPlayer.CS = rand.nextInt(300);
        TempPlayer.damageTaken = rand.nextInt(1000);
        TempPlayer.deaths = rand.nextInt(35);
        TempPlayer.escapes = rand.nextInt(10);
        TempPlayer.hits = TempPlayer.attacks == 0 ? 0 : rand.nextInt(TempPlayer.attacks);
        TempPlayer.damageDone = TempPlayer.hits == 0 ? 0 : TempPlayer.hits + rand.nextInt(5 * TempPlayer.hits);
        TempPlayer.kills = TempPlayer.hits == 0 ? 0 : rand.nextInt(1 + (int) (TempPlayer.hits / 4));
        //For testings sake there is a 1/50 chance that the player leaves
        //That number doesn't really come from anywhere
        TempPlayer.leave = (0 == (rand.nextInt(50)));
        TempPlayer.spellsCasted = rand.nextInt(100);
        TempPlayer.spellDamageDone = TempPlayer.spellsCasted == 0 ? 0 : rand.nextInt(8 * TempPlayer.spellsCasted);
        TempPlayer.spellDamageTaken = rand.nextInt(800);
        return TempPlayer;
    }

    static public Game RandomGame() {
        int numPlayers = 3 + rand.nextInt(3);
        return RandomGame(numPlayers);
    }

    static public Game RandomGame(int numPlayers) {
        if (numPlayers > 5 || numPlayers < 3) {
            return null;
        }
        Game TempGame = new Game();
        //List<int> RandIds = GetRandomIDs();
        for (int i = 1; i <= numPlayers; i++) {
            //TempGame.team1.Add(RandomPlayer(RandIds[(2 * i - 2)]));
            //TempGame.team2.Add(RandomPlayer(RandIds[2 * i - 1]));
            TempGame.team1.add(RandomPlayer());
            TempGame.team2.add(RandomPlayer());
        }
        //b/w 20min and 60min
        TempGame.runningTime = 1200 + rand.nextInt(2400);
        //this will give the team with more kills a better chance at winning
        //since that would make sense in game
        // (+1 because random can't take 0)
        TempGame.team1Wins = ((rand.nextInt(1 + TempGame.getTeam1Stat("kills") + TempGame.getTeam2Stat("kills"))) <= TempGame.getTeam1Stat("kills"));
        return TempGame;
    }
}
