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
public class SimulatorNGTest {

    public SimulatorNGTest() {
    }

    /**
     * Test of RandomPlayer method, of class Simulate.
     */
    @Test
    public void testRandomPlayer() {
        //just make sure no exceptions were thrown
        //do it alot since this is random
        //I tested this 10 million times at first
        for (int i = 0; i < 100; i++) {
            assertNotNull(Simulator.RandomPlayer());
        }
    }

    /**
     * Test of RandomGame method, of class Simulate.
     */
    @Test
    public void testRandomGame_0args() {
        //just make sure no exceptions were thrown
        for (int i = 0; i < 10; i++) {
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
        for (int i = 0; i < 10; i++) {
            assertNull(Simulator.RandomGame(2));
            assertNotNull(Simulator.RandomGame(3));
            assertNotNull(Simulator.RandomGame(4));
            assertNotNull(Simulator.RandomGame(5));
            assertNull(Simulator.RandomGame(6));
        }
    }
}