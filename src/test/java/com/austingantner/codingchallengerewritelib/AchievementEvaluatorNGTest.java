/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austingantner.codingchallengerewritelib;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author austingantner
 */
public class AchievementEvaluatorNGTest {

    public AchievementEvaluatorNGTest() {
    }

    @Test
    public void testSharpShooter() {
        try {
            Achievement testAchievement = new Achievement(1, "SharpShooter", "", "'Player.hits'/'Player.attacks'>=.75");
            Game testGame = new Game();
            Player testPlayer = new Player();

            testPlayer.attacks = 100;
            testPlayer.hits = 70;
            assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, testGame, testAchievement));

            testPlayer.hits = 90;
            assertTrue(AchievementEvaluator.evaluateAchievement(testPlayer, testGame, testAchievement));
        } catch (Exception ex) {
            fail("failed");
        }
    }

    @Test
    public void testBruiser() {
        try {
            Achievement testAchievement = new Achievement(1, "Bruiser", "", "'Player.damageDone'>=500");
            Game testGame = new Game();
            Player testPlayer = new Player();

            testPlayer.damageDone = 400;
            assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, testGame, testAchievement));

            testPlayer.damageDone = 600;
            assertTrue(AchievementEvaluator.evaluateAchievement(testPlayer, testGame, testAchievement));
        } catch (Exception ex) {
            fail("failed");
        }
    }

    @Test
    public void testVeteran() {
        try {
            Achievement testAchievement = new Achievement(1, "Veteran", "", "'Player.careerLosses'+'Player.careerWins'==999");
            Game testGame = new Game();
            Player testPlayer = new Player();

            testPlayer.careerWins = 500;
            testPlayer.careerLosses = 498;
            assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, testGame, testAchievement));

            testPlayer.careerLosses = 499;
            assertTrue(AchievementEvaluator.evaluateAchievement(testPlayer, testGame, testAchievement));

            testPlayer.careerLosses = 500;
            assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, testGame, testAchievement));
        } catch (Exception ex) {
            fail("failed");
        }
    }
/*//todo: implement CouchPotato
    @Test
    public void testCouchPotato() {
        try {
            Achievement testAchievement = new Achievement(1, "CouchPotato", "", "'Player.careerLosses'+'Player.careerWins'==999");
            Game testGame = new Game();
            Player testPlayer = new Player();

            testPlayer.careerWins = 500;
            testPlayer.careerLosses = 498;
            assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, testGame, testAchievement));

            testPlayer.careerLosses = 499;
            assertTrue(AchievementEvaluator.evaluateAchievement(testPlayer, testGame, testAchievement));

            testPlayer.careerLosses = 500;
            assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, testGame, testAchievement));
        } catch (Exception ex) {
            fail("failed");
        }
    }
*/
    @Test
    public void testMageRage() {
        try {
            Achievement testAchievement = new Achievement(1, "MageRage", "", "'Player.spellDamageDone'>=500");
            Game testGame = new Game();
            Player testPlayer = new Player();

            testPlayer.spellDamageDone = 400;
            assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, testGame, testAchievement));

            testPlayer.spellDamageDone = 600;
            assertTrue(AchievementEvaluator.evaluateAchievement(testPlayer, testGame, testAchievement));
        } catch (Exception ex) {
            fail("failed");
        }
    }

    @Test
    public void testOneManWreckingCrew() {
        try {
            Achievement testAchievement = new Achievement(1, "OneManWreckingCrew", "", "'Player.careerKills'<5000 && 'Player.kills'+'Player.careerKills'>=5000");
            Game testGame = new Game();
            Player testPlayer = new Player();

            testPlayer.careerKills = 400;
            testPlayer.kills = 10;
            assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, testGame, testAchievement));

            testPlayer.careerKills = 4999;
            assertTrue(AchievementEvaluator.evaluateAchievement(testPlayer, testGame, testAchievement));

            //has already recieved it
            testPlayer.careerKills = 5001;
            assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, testGame, testAchievement));

        } catch (Exception ex) {
            fail("failed");
        }
    }

    @Test
    public void testWingman() {
        try {
            Achievement testAchievement = new Achievement(1, "Wingman", "", "'Player.careerAssists'<8000 && 'Player.assists'+'Player.careerAssists'>=8000");
            Game testGame = new Game();
            Player testPlayer = new Player();

            testPlayer.careerAssists = 400;
            testPlayer.assists = 10;
            assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, testGame, testAchievement));

            testPlayer.careerAssists = 7999;
            assertTrue(AchievementEvaluator.evaluateAchievement(testPlayer, testGame, testAchievement));

            //has already recieved it
            testPlayer.careerAssists = 8001;
            assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, testGame, testAchievement));

        } catch (Exception ex) {
            fail("failed");
        }
    }

    @Test
    public void testFinishingTouch() {
        try {
            Achievement testAchievement = new Achievement(1, "FinishingTouch", "", "'Player.CS'>=250");
            Game testGame = new Game();
            Player testPlayer = new Player();

            testPlayer.CS = 200;
            assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, testGame, testAchievement));

            testPlayer.CS = 255;
            assertTrue(AchievementEvaluator.evaluateAchievement(testPlayer, testGame, testAchievement));
        } catch (Exception ex) {
            fail("failed");
        }
    }

    @Test
    public void testWorthless() {
        try {
            Achievement testAchievement = new Achievement(1, "Worthless", "", "'Player.leave'&&'Player.careerLeaves'==99");
            Game testGame = new Game();
            Player testPlayer = new Player();

            testPlayer.careerLeaves = 98;
            testPlayer.leave = true;
            assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, testGame, testAchievement));

            testPlayer.careerLeaves = 99;
            assertTrue(AchievementEvaluator.evaluateAchievement(testPlayer, testGame, testAchievement));

            testPlayer.leave = false;
            assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, testGame, testAchievement));
        } catch (Exception ex) {
            fail("failed");
        }
    }

    @Test
    public void testHoudini() {
        try {
            Achievement testAchievement = new Achievement(1, "Houdini", "", "'Player.escapes'>'Player.deaths'");
            Game testGame = new Game();
            Player testPlayer = new Player();

            testPlayer.deaths = 5;
            testPlayer.escapes = 5;
            assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, testGame, testAchievement));

            testPlayer.deaths = 4;
            assertTrue(AchievementEvaluator.evaluateAchievement(testPlayer, testGame, testAchievement));
        } catch (Exception ex) {
            fail("failed");
        }
    }

    @Test
    public void testWarrior() {
        try {
            Achievement testAchievement = new Achievement(1, "Warrior", "", "'Player.kills'>0 && 'Player.kills'>=2*'Player.deaths'");
            Game testGame = new Game();
            Player testPlayer = new Player();

            testPlayer.deaths = 5;
            testPlayer.kills = 5;
            assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, testGame, testAchievement));

            testPlayer.deaths = 2;
            assertTrue(AchievementEvaluator.evaluateAchievement(testPlayer, testGame, testAchievement));
        } catch (Exception ex) {
            fail("failed");
        }
    }

    @Test
    public void testEvaluatePlayer() {
        try {
            Game testGame = new Game();
            Player testPlayer = new Player();
            testPlayer.damageDone = 600;
            testPlayer.CS = 300;
            AchievementEvaluator.evaluatePlayer(testPlayer, testGame);
            assertTrue(testPlayer.achievements.size() == 2);
        } catch (Exception ex) {
            fail("failed");
        }
    }
}
