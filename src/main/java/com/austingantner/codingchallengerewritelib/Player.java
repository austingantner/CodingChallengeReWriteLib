package com.austingantner.codingchallengerewritelib;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author austingantner
 * 
 */
public class Player {

    public int ID = -1;
    public String name = "Player";
    //per game stats
    public int attacks = 0;
    public int assists = 0;
    public int CS = 0; // "last hit" kills
    public int damageDone = 0;
    public int damageTaken = 0;
    public int deaths = 0;
    // an escape is when player survives a fight with less than 10% health
    public int escapes = 0;
    public int hits = 0;
    public int kills = 0;
    public boolean leave = false;
    public int spellsCasted = 0;
    public int spellDamageDone = 0;
    public int spellDamageTaken = 0;
    //tournament stats
    public int tournamentAttacks = 0;
    public int tournamentAssists = 0;
    public int tournamentCS = 0;
    public int tournamentDamageDone = 0;
    public int tournamentDamageTaken = 0;
    public int tournamentDeaths = 0;
    public int tournamentEscapes = 0;
    public int tournamentHits = 0;
    public int tournamentKills = 0;
    public int tournamentLeaves = 0;
    public int tournamentLosses = 0;
    public int tournamentSpellsCasted = 0;
    public int tournamentSpellDamageDone = 0;
    public int tournamentSpellDamageTaken = 0;
    public int tournamentWins = 0;
    //career stats
    public int careerAttacks = 0;
    public int careerAssists = 0;
    public int careerCS = 0;
    public int careerDamageDone = 0;
    public int careerDamageTaken = 0;
    public int careerDeaths = 0;
    public int careerEscapes = 0;
    public int careerHits = 0;
    public int careerKills = 0;
    public int careerLeaves = 0;
    public int careerLosses = 0;
    public int careerSpellsCasted = 0;
    public int careerSpellDamageDone = 0;
    public int careerSpellDamageTaken = 0;
    public int careerWins = 0;
    //todo: change this to achievement
    public List<Achievement> achievements = new ArrayList<Achievement>();
    public List<Achievement> careerAchievements = new ArrayList<Achievement>();
    
//============= CONSTRUCTORS ================
    
    public Player(){}
    
    public Player(int ID)
    {
        this.ID = ID;
    }
    
    public Player(int ID, String name, int attacks, int assists, int CS, int damageDone, int damageTaken,
                  int deaths, int escapes, int hits, int kills, boolean leave, 
                  int spellsCasted, int spellDamageDone, int spellDamageTaken)
    {
        this.ID = ID;
        this.name = name;
        this.attacks = attacks;
        this.assists = assists;
        this.CS =CS;
        this.damageDone=damageDone;
        this.damageTaken=damageTaken;
        this.deaths=deaths;
        this.escapes=escapes;
        this.hits=hits;
        this.kills =kills;
        this.leave=leave;
        this.spellsCasted=spellsCasted;
        this.spellDamageDone=spellDamageDone;
        this.spellDamageTaken=spellDamageTaken;
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

}
