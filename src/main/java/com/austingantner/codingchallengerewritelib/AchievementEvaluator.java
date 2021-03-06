package com.austingantner.codingchallengerewritelib;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 *
 * @author austingantner
 */
public class AchievementEvaluator {
    //todo: bigwinner for team game
    //todo: finish couch potato
    //load from database in future

    public static List<Achievement> achievements = new ArrayList<Achievement>()
     /*/ Don't have a database? Here are the values manually. 
      // Just remove the * on the previous line and you should be fine. 
    {
        {
            add(new Achievement(0, "SharpShooter", "", "'Player.hits'/'Player.attacks'>=.75"));
            add(new Achievement(1, "Bruiser", "", "'Player.damageDone'>=500"));
            add(new Achievement(2, "Veteran", "", "'Player.careerLosses'+'Player.careerWins'==999"));
            //still needs to be implemented
            //add(new Achievement(3, "CouchPotato", "", "'Player.careerLosses'+'Player.careerWins'==999"));
            add(new Achievement(4, "MageRage", "", "'Player.spellDamageDone'>=500"));
            add(new Achievement(5, "OneManWreckingCrew", "", "'Player.careerKills'<5000 && 'Player.kills'+'Player.careerKills'>=5000"));
            add(new Achievement(6, "Wingman", "", "'Player.careerAssists'<8000 && 'Player.assists'+'Player.careerAssists'>=8000"));
            add(new Achievement(7, "FinishingTouch", "", "'Player.CS'>=250"));
            add(new Achievement(8, "Worthless", "", "'Player.leave'&&'Player.careerLeaves'==99"));
            add(new Achievement(9, "Houdini", "", "'Player.escapes'>'Player.deaths'"));
            add(new Achievement(10, "Warrior", "", "'Player.kills'>0 && 'Player.kills'>=2*'Player.deaths'"));
        }
    };//*/;
    
    private static void populateAchievements(){
        ConnectionManager.ResultSetHandler handler = new ConnectionManager.ResultSetHandler(){
            public void Handle(ResultSet rs) throws SQLException{
                while(rs.next()){
                    achievements.add(new Achievement(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getString("evalString")
                            ));
                }
            }
        };
        ConnectionManager.Query("Select * from Achievements", handler);
    }

    public static void evaluateGame(Game game) {
        if(achievements.isEmpty()){
            populateAchievements();
        }
        for (Player p : game.team1) {
            evaluatePlayer(p, game);
        }
        for (Player p : game.team2) {
            evaluatePlayer(p, game);
        }
    }

    public static void evaluatePlayer(Player player, Game game) {
        if(achievements.isEmpty()){
            populateAchievements();
        }
        for (Achievement a : achievements) {
            if (evaluateAchievement(player, game, a)) {
                player.achievements.add(a);
            }
        }
    }

    //not terribly secure but fairly well sandboxed and very extendable.
    //besides, reflection is fun
    public static boolean evaluateAchievement(Player player, Game game, Achievement achievement) {
        try {
            String[] parts = achievement.evalString.split("'");
            String evalString = "";
            for (String s : parts) {
                if (s.trim().length() >= 7 && s.trim().substring(0, 7).equalsIgnoreCase("Player.")) {
                    evalString += Player.class.getField(s.trim().substring(7)).get(player).toString();
                } else if (s.trim().length() >= 7 && s.trim().substring(0, 5).equalsIgnoreCase("Game.")) {
                    evalString += Game.class.getField(s.trim().substring(5)).get(game).toString();
                } else {
                    evalString += s;
                }
            }

            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            //todo: upload achievement to database here
            return (Boolean) engine.eval(evalString);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
