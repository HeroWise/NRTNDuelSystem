
package com.topobon.duel.arena;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import com.topobon.duel.DuelNRTN;
import com.topobon.duel.utils.ConfigManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Also NOT thread safe
public class Arena {
	// Individual arena info here

	// Ofc, this CAN'T be the ID COULD IT? (jk)
	private final int id;
	private Location locA;
	private Location locB;
	private List<UUID> players = new ArrayList<UUID>();

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

	public void load() {
		setLocationA();
		setLocationB();
	}

	public void setLocationA() {
		ConfigManager cm = new ConfigManager(DuelNRTN.instance,id);
		if (!cm.exists()) {

			FileConfiguration f = cm.getConfig();

			double x = f.getDouble("location1.x");
			double y = f.getDouble("location1.y");
			double z = f.getDouble("location1.z");
			World world = (World) f.get("location1.world");

			cm.saveConfig();
			this.locA = new Location(world, x, y, z);
		}
	}

	public void setLocationB() {
		ConfigManager cm = new ConfigManager(DuelNRTN.instance,id);
		if (!cm.exists()) {

			FileConfiguration f = cm.getConfig();

			double x = f.getDouble("location2.x");
			double y = f.getDouble("location2.y");
			double z = f.getDouble("location2.z");
			World world = (World) f.get("location2.world");

			cm.saveConfig();
			this.locB = new Location(world, x, y, z);
		}
	}

	public Location getLocationA() {
		return locA;
	}

	public Location getLocationB() {
		return locB;
	}
}