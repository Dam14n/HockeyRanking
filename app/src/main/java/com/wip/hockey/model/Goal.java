package com.wip.hockey.model;

/**
 * Created by djorda on 23/05/2017.
 */

public class Goal {

    private Match match;
    private Player player;

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
