
package com.topobon.duel.arena;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Also NOT thread safe
public class Arena {
    // Individual arena info here

    // Ofc, this CAN'T be the ID COULD IT? (jk)
    private final int id;
    private final Location locA;
    private final Location locB;
    private final List<UUID> players = new ArrayList<UUID>();

    public Arena(Location location1, Location location2, int id) {
        this.locA = location1;
        this.locB = location2;
        this.id = id;
    }

    // Getters

    public int getId() {
        return this.id;
    }

    public List<UUID> getPlayers() {
        return this.players;
    }

	public Location getLocationA() {
		return locA;
	}
	

	public Location getLocationB() {
		return locB;
	}
}