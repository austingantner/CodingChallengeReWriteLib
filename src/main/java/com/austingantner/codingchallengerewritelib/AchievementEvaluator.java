package com.austingantner.codingchallengerewritelib;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 *
 * @author austingantner
 */
public class AchievementEvaluator {
    //todo: bigwinner for team game
    //todo: couchpotato...needs game
    
    public static boolean evaluatePlayer(Player p) {
        return evaluateAchievement(p, "'Player.hits'/'Player.attacks'>=.75");
    }
    
    //not very secure...but very extendable
    public static boolean evaluateAchievement(Player player, String achievementString) {
        try {
            String[] parts = achievementString.split("'");
            String evalString = "";
            for (String s : parts) {
                if (s.trim().length() >= 7 && s.trim().substring(0, 7).equalsIgnoreCase("Player.")) {
                    s = Player.class.getField(s.trim().substring(7)).get(player).toString();
                }
                evalString += s;
            }
            
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            
            return (Boolean) engine.eval(evalString);
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
