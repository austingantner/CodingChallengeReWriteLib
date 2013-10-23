package com.austingantner.codingchallengerewritelib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author austingantner
 */
public class Simulator {

    public static Random rand = new Random();

    static public Player RandomPlayer() {
        return RandomPlayer(-1);
    }

    static public Player RandomPlayer(int ID) {
        Player TempPlayer = new Player();
        TempPlayer.ID = ID;
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
        if (ID > 0) {
            TempPlayer.loadCareerStats();
            TempPlayer.updateCareerStats();
        }
        return TempPlayer;
    }

    static public Game RandomGame() {
        int numPlayers = 3 + rand.nextInt(3);
        return RandomGame(numPlayers, true);
    }

    static public Game RandomGame(boolean useDatabase) {
        int numPlayers = 3 + rand.nextInt(3);
        return RandomGame(numPlayers, useDatabase);
    }

    static public Game RandomGame(int numPlayers) {
        return RandomGame(numPlayers, true);
    }

    static public Game RandomGame(int numPlayers, boolean useDatabase) {
        if (numPlayers > 5 || numPlayers < 3) {
            return null;
        }
        Game TempGame = new Game();


        if (useDatabase) {
            List<Integer> RandIds = GetRandomIDs(numPlayers);
            for (int i = 0; i < numPlayers; i++) {
                TempGame.team1.add(RandomPlayer(RandIds.get(i)));
                TempGame.team2.add(RandomPlayer(RandIds.get(RandIds.size() - 1 - i)));
            }
        } else {
            for (int i = 1; i <= numPlayers; i++) {
                TempGame.team1.add(RandomPlayer());
                TempGame.team2.add(RandomPlayer());
            }
        }
        //b/w 20min and 60min
        TempGame.runningTime = 1200 + rand.nextInt(2400);
        //this will give the team with more kills a better chance at winning
        //since that would make sense in game
        // (+1 because random can't take 0)
        TempGame.team1Wins = ((rand.nextInt(1 + TempGame.getTeam1Stat("kills") + TempGame.getTeam2Stat("kills"))) <= TempGame.getTeam1Stat("kills"));

        //update wins and losses
        if (useDatabase) {
            if (TempGame.team1Wins) {
                StringBuilder wonsb = new StringBuilder();
                wonsb.append("update Players set wins = wins + 1 where");
                wonsb.append(" id = ").append(TempGame.team1.get(0).ID);
                for (int i = 1; i < TempGame.team1.size(); i++) {
                    wonsb.append(" or id = ").append(TempGame.team1.get(i).ID);
                }
                ConnectionManager.executeNonQuery(wonsb.toString());

                StringBuilder lossedsb = new StringBuilder();
                lossedsb.append("update Players set losses = losses + 1 where");
                lossedsb.append(" id = ").append(TempGame.team2.get(0).ID);
                for (int i = 1; i < TempGame.team2.size(); i++) {
                    lossedsb.append(" or id = ").append(TempGame.team2.get(i).ID);
                }
                ConnectionManager.executeNonQuery(lossedsb.toString());
            } else {
                StringBuilder wonsb = new StringBuilder();
                wonsb.append("update Players set wins = wins + 1 where");
                wonsb.append(" id = ").append(TempGame.team2.get(0).ID);
                for (int i = 1; i < TempGame.team2.size(); i++) {
                    wonsb.append(" or id = ").append(TempGame.team2.get(i).ID);
                }
                ConnectionManager.executeNonQuery(wonsb.toString());

                StringBuilder lossedsb = new StringBuilder();
                lossedsb.append("update Players set losses = losses + 1 where");
                lossedsb.append(" id = ").append(TempGame.team1.get(0).ID);
                for (int i = 1; i < TempGame.team1.size(); i++) {
                    lossedsb.append(" or id = ").append(TempGame.team1.get(i).ID);
                }
                ConnectionManager.executeNonQuery(lossedsb.toString());
            }
        }

        AchievementEvaluator.evaluateGame(TempGame);
        return TempGame;
    }

    static public List<Integer> GetRandomIDs(int numPlayers) {
        List<Integer> RandIDs = new ArrayList<Integer>();
        //todo: replace this loop with a list of ID's from database
        for (int i = 1; i <= 16; i++) {
            RandIDs.add(i);
        }
        Collections.shuffle(RandIDs);
        return RandIDs.subList(1, 1 + 2 * numPlayers);
    }
}
