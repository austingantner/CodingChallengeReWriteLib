/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austingantner.codingchallengerewritelib;

import static org.testng.Assert.*;
/*
 import org.testng.annotations.AfterClass;
 import org.testng.annotations.AfterMethod;
 import org.testng.annotations.BeforeClass;
 import org.testng.annotations.BeforeMethod;
 */
import org.testng.annotations.Test;

/**
 *
 * @author austingantner
 */
public class AchievementEvaluatorNGTest {

    public AchievementEvaluatorNGTest() {
    }
    /*
     @BeforeClass
     public static void setUpClass() throws Exception {
     }

     @AfterClass
     public static void tearDownClass() throws Exception {
     }

     @BeforeMethod
     public void setUpMethod() throws Exception {
     }

     @AfterMethod
     public void tearDownMethod() throws Exception {
     }
     */

    @Test
    public void testSharpShooter() {
        try {
            String evalString = "'Player.hits'/'Player.attacks'>=.75";
            Player testPlayer = new Player();

            testPlayer.attacks = 100;
            testPlayer.hits = 70;
            assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, evalString));

            testPlayer.hits = 90;
            assertTrue(AchievementEvaluator.evaluateAchievement(testPlayer, evalString));
        } catch (Exception ex) {
            fail("failed");
        }
    }

    @Test
    public void testBruiser() {
        try {
            String evalString = "'Player.damageDone'>=500";
            Player testPlayer = new Player();

            testPlayer.damageDone = 400;
            assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, evalString));

            testPlayer.damageDone = 600;
            assertTrue(AchievementEvaluator.evaluateAchievement(testPlayer, evalString));
        } catch (Exception ex) {
            fail("failed");
        }
    }

    @Test
    public void testVeteran() {
        try {
            String evalString = "'Player.careerLosses'+'Player.careerWins'==999";
            Player testPlayer = new Player();
            testPlayer.careerWins = 500;
            testPlayer.careerLosses = 498;
            assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, evalString));

            testPlayer.careerLosses = 499;
            assertTrue(AchievementEvaluator.evaluateAchievement(testPlayer, evalString));

            testPlayer.careerLosses = 500;
            assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, evalString));
        } catch (Exception ex) {
            fail("failed");
        }
    }
    /*
     @Test
     public void testCouchPotato() {
     try {
     String evalString = " >= 1800000";
     Player testPlayer = new Player();
     testPlayer.careerWins = 500;
     testPlayer.careerLosses = 498;
     assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, evalString));
            
     testPlayer.careerLosses = 499;
     assertTrue(AchievementEvaluator.evaluateAchievement(testPlayer, evalString));
            
     testPlayer.careerLosses = 500;
     assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, evalString));
     } catch (Exception ex) {
     fail("failed");
     }
     }
     */

    @Test
    public void testMageRage() {
        try {
            String evalString = "'Player.spellDamageDone'>=500";
            Player testPlayer = new Player();

            testPlayer.spellDamageDone = 400;
            assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, evalString));

            testPlayer.spellDamageDone = 600;
            assertTrue(AchievementEvaluator.evaluateAchievement(testPlayer, evalString));
        } catch (Exception ex) {
            fail("failed");
        }
    }

    @Test
    public void testOneManWreckingCrew() {
        try {
            String evalString = "'Player.careerKills'<5000 && 'Player.kills'+'Player.careerKills'>=5000";
            Player testPlayer = new Player();

            testPlayer.careerKills = 400;
            testPlayer.kills = 10;
            assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, evalString));

            testPlayer.careerKills = 4999;
            assertTrue(AchievementEvaluator.evaluateAchievement(testPlayer, evalString));

            //has already recieved it
            testPlayer.careerKills = 5001;
            assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, evalString));

        } catch (Exception ex) {
            fail("failed");
        }
    }

    @Test
    public void testWingman() {
        try {
            String evalString = "'Player.careerAssists'<8000 && 'Player.assists'+'Player.careerAssists'>=8000";
            Player testPlayer = new Player();

            testPlayer.careerAssists = 400;
            testPlayer.assists = 10;
            assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, evalString));

            testPlayer.careerAssists = 7999;
            assertTrue(AchievementEvaluator.evaluateAchievement(testPlayer, evalString));

            //has already recieved it
            testPlayer.careerAssists = 8001;
            assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, evalString));

        } catch (Exception ex) {
            fail("failed");
        }
    }

    @Test
    public void testFinishingTouch() {
        try {
            String evalString = "'Player.CS'>=250";
            Player testPlayer = new Player();

            testPlayer.CS = 200;
            assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, evalString));

            testPlayer.CS = 255;
            assertTrue(AchievementEvaluator.evaluateAchievement(testPlayer, evalString));
        } catch (Exception ex) {
            fail("failed");
        }
    }

    @Test
    public void testWorthless() {
        try {
            String evalString = "'Player.leave'&&'Player.careerLeaves'==99";
            Player testPlayer = new Player();

            testPlayer.careerLeaves = 98;
            testPlayer.leave = true;
            assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, evalString));

            testPlayer.careerLeaves = 99;
            assertTrue(AchievementEvaluator.evaluateAchievement(testPlayer, evalString));

            testPlayer.leave = false;
            assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, evalString));

        } catch (Exception ex) {
            fail("failed");
        }
    }

    @Test
    public void testHoudini() {
        try {
            String evalString = "'Player.escapes'>'Player.deaths'";
            Player testPlayer = new Player();

            testPlayer.deaths = 5;
            testPlayer.escapes = 5;
            assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, evalString));

            testPlayer.deaths = 4;
            assertTrue(AchievementEvaluator.evaluateAchievement(testPlayer, evalString));
        } catch (Exception ex) {
            fail("failed");
        }
    }

    @Test
    public void testWarrior() {
        try {
            String evalString = "'Player.kills'>0 && 'Player.kills'>=2*'Player.deaths'";
            Player testPlayer = new Player();

            testPlayer.deaths = 5;
            testPlayer.kills = 5;
            assertFalse(AchievementEvaluator.evaluateAchievement(testPlayer, evalString));

            testPlayer.deaths = 2;
            assertTrue(AchievementEvaluator.evaluateAchievement(testPlayer, evalString));
        } catch (Exception ex) {
            fail("failed");
        }
    }
}
