
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
	private String name;
	private boolean isEnabled;
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
		loadLocationA();
		loadLocationB();
	}
	
	public void loadLocationA() {
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
	public void loadLocationB() {
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
	public void loadName() {
		ConfigManager cm = new ConfigManager(DuelNRTN.instance,id);
		if (!cm.exists()) {

			FileConfiguration f = cm.getConfig();

			name = f.getString("Areba-Name");
			isEnabled = f.getBoolean("isEnabled");
			
			cm.saveConfig();
			
		}
	}
	public void setLocationA(Location location) {
		ConfigManager cm = new ConfigManager(DuelNRTN.instance,id);
		Location l1 = location;
		FileConfiguration f = cm.getConfig();
		if (!cm.exists()) {

			f.set("Arena-Name", name);
			f.set("isEnabled", false);
			f.set("location1.x", l1.getX());
			f.set("location1.x", l1.getX());
			f.set("location1.y", l1.getY());
			f.set("location1.z", l1.getY());
	
			cm.saveConfig();
		} else {
			f.set("Arena-Name", name);
			f.set("isEnabled", false);
			f.set("location1.x", l1.getX());
			f.set("location1.x", l1.getX());
			f.set("location1.y", l1.getY());
			f.set("location1.z", l1.getY());
	
		
			cm.saveConfig();
		}
	}
	public void setLocationB(Location location) {
		ConfigManager cm = new ConfigManager(DuelNRTN.instance,id);
		Location l1 = location;
		FileConfiguration f = cm.getConfig();
		if (!cm.exists()) {

			f.set("Arena-Name", name);
			f.set("isEnabled", false);
			f.set("location2.x", l1.getX());
			f.set("location2.x", l1.getX());
			f.set("location2.y", l1.getY());
			f.set("location2.z", l1.getY());
		
		
			cm.saveConfig();
		} else {
			f.set("Arena-Name", name);
			f.set("isEnabled", false);
			f.set("location2.x", l1.getX());
			f.set("location2.x", l1.getX());
			f.set("location2.y", l1.getY());
			f.set("location2.z", l1.getY());

			cm.saveConfig();
		}
	}

	
	
	

	public Location getLocationA() {
		return locA;
	}

	public Location getLocationB() {
		return locB;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}