package com.austingantner.codingchallengerewritelib;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author austingantner
 */
public class Game {
    //Id only important if loaded from database
    public int ID = -1;
    public boolean team1Wins = true;
    public int runningTime = 0;//in seconds (could have used an object meant for time but this is simple)
    public List<Player> team1 = new ArrayList<Player>();
    public List<Player> team2 = new ArrayList<Player>();

    //reflection might not be the safest solution but its fun so I did it anyway
    public int getTeam1Stat(String statName) {
        try {
            int stat = 0;
            for (Player p : team1) {
                stat += (Integer) (Player.class.getField(statName).get(p));
            }
            return stat;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    public int getTeam2Stat(String statName) {
        try {
            int stat = 0;
            for (Player p : team2) {
                stat += (Integer) Player.class.getField(statName).get(p);
            }
            return stat;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }
}
