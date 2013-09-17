package com.austingantner.codingchallengerewritelib;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    /*
     // todo: implement this
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
     */
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
    public Player() {
    }

    public Player(int ID) {
        this.ID = ID;
    }

    public Player(int ID, String name, int attacks, int assists, int CS, int damageDone, int damageTaken,
            int deaths, int escapes, int hits, int kills, boolean leave,
            int spellsCasted, int spellDamageDone, int spellDamageTaken) {
        this.ID = ID;
        this.name = name;
        this.attacks = attacks;
        this.assists = assists;
        this.CS = CS;
        this.damageDone = damageDone;
        this.damageTaken = damageTaken;
        this.deaths = deaths;
        this.escapes = escapes;
        this.hits = hits;
        this.kills = kills;
        this.leave = leave;
        this.spellsCasted = spellsCasted;
        this.spellDamageDone = spellDamageDone;
        this.spellDamageTaken = spellDamageTaken;
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    public void loadCareerStats() {
        ConnectionManager.ResultSetHandler handler = new ConnectionManager.ResultSetHandler() {
            public void Handle(ResultSet rs) throws SQLException {
                if (rs.next()) {
                    name = rs.getString("name");
                    careerAttacks = rs.getInt("attacks");
                    careerAssists = rs.getInt("assists");
                    careerCS = rs.getInt("CS");
                    careerDamageDone = rs.getInt("damageDone");
                    careerDamageTaken = rs.getInt("damageTaken");
                    careerDeaths = rs.getInt("deaths");
                    careerEscapes = rs.getInt("escapes");
                    careerHits = rs.getInt("hits");
                    careerKills = rs.getInt("kills");
                    careerLeaves = rs.getInt("leaves");
                    careerLosses = rs.getInt("losses");
                    careerSpellsCasted = rs.getInt("spellsCasted");
                    careerSpellDamageDone = rs.getInt("spellDamageDone");
                    careerSpellDamageTaken = rs.getInt("spellDamageTaken");
                    careerWins = rs.getInt("wins");
                }
            }
        };
        ConnectionManager.Query("Select * from Players where id = " + ID, handler);
    }

    public void updateCareerStats() {
        StringBuilder s = new StringBuilder();
        s.append("update Players set ");
        s.append("attacks = attacks + ").append(attacks);
        s.append(", assists = assists + ").append(assists);
        s.append(", CS = CS + ").append(CS);
        s.append(", damageDone = damageDone + ").append(damageDone);
        s.append(", damageTaken = damageTaken +").append(damageTaken);
        s.append(", deaths = deaths + ").append(deaths);
        s.append(", escapes = escapes + ").append(escapes);
        s.append(", hits = hits + ").append(hits);
        s.append(", kills = kills + ").append(kills);
        if (leave) {
            s.append(", leaves = leaves + 1");
        }
        s.append(", spellsCasted = spellsCasted + ").append(spellsCasted);
        s.append(", spellDamageDone = spellDamageDone + ").append(spellDamageDone);
        s.append(", spellDamageTaken = spellDamageTaken + ").append(spellDamageTaken);
        s.append(" where id = ").append(ID);
        ConnectionManager.executeNonQuery(s.toString());
    }
}
