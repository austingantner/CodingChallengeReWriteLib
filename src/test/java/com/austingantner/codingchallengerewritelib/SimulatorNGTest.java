/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austingantner.codingchallengerewritelib;

import java.util.Random;
import static org.testng.Assert.*;
/*
 import org.testng.annotations.AfterClass;
 import org.testng.annotations.AfterMethod;
 import org.testng.annotations.BeforeClass;
 import org.testng.annotations.BeforeMethod;*/
import org.testng.annotations.Test;

/**
 *
 * @author austingantner
 */
public class SimulatorNGTest {

    public SimulatorNGTest() {
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
     * Test of RandomPlayer method, of class Simulate.
     */
    @Test
    public void testRandomPlayer() {
        //just make sure no exceptions were thrown
        //do it alot since this is random
        //I tested this 10 million times at first
        for (int i = 0; i < 10000; i++) {
            assertNotNull(Simulator.RandomPlayer());
        }
    }

    /**
     * Test of RandomGame method, of class Simulate.
     */
    @Test
    public void testRandomGame_0args() {
        //just make sure no exceptions were thrown
        for (int i = 0; i < 10000; i++) {
            assertNotNull(Simulator.RandomGame());
        }
    }

    /**
     * Test of RandomGame method, of class Simulate.
     */
    @Test
    public void testRandomGame_int() {
        //just make sure no exceptions were thrown and that
        // it returns null for wrong number of players
        for (int i = 0; i < 10000; i++) {
            assertNull(Simulator.RandomGame(2));
            assertNotNull(Simulator.RandomGame(3));
            assertNotNull(Simulator.RandomGame(4));
            assertNotNull(Simulator.RandomGame(5));
            assertNull(Simulator.RandomGame(6));
        }
    }
}