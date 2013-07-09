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
public class GameNGTest {
    
    public GameNGTest() {
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
    /**
     * Test of getTeam1Stat method, of class Game.
     */
    @Test
    public void testGetTeam1Stat() throws Exception {
        Game instance = new Game();
        instance.team1.add(new Player());
        instance.team1.get(0).attacks = 4;        
        instance.team1.add(new Player());
        instance.team1.get(1).attacks = 5;
        assertEquals(9, instance.getTeam1Stat("attacks"));
    }

    /**
     * Test of getTeam2Stat method, of class Game.
     */
    @Test
    public void testGetTeam2Stat() throws Exception {
        Game instance = new Game();
        instance.team2.add(new Player());
        instance.team2.get(0).attacks = 4;        
        instance.team2.add(new Player());
        instance.team2.get(1).attacks = 5;
        assertEquals(9, instance.getTeam2Stat("attacks"));
    }
}