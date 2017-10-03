package com.topobon.duel.arena;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.topobon.duel.DuelNRTN;
import com.topobon.duel.utils.ConfigManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

// NOT THREAD SAFE!
public class ArenaManager {
	// Singleton instance
	private static ArenaManager am;

	// Player data
	private final Map<UUID, Location> locs = new HashMap<UUID, Location>();
	// private final Map<UUID, ItemStack[]> inv = new HashMap<UUID,
	// ItemStack[]>();
	// private final Map<UUID, ItemStack[]> armor = new HashMap<UUID,
	// ItemStack[]>();

	private final List<Arena> arenas = new ArrayList<Arena>();
	// Keep track of the current arena ID
	public int arenaSize = 0;

	private ArenaManager() {
	} // Prevent instantiation

	// Singleton accessor with lazy initialization
	public static ArenaManager getManager() {
		if (am == null)
			am = new ArenaManager();

		return am;
	}

	/**
	 * Acquires an arena based on its ID number
	 *
	 * @param i
	 *            the ID to search the arenas for
	 * @return the arena possessing the specified ID
	 */
	public Arena getArena(int i) {
		for (Arena a : this.arenas) {
			if (a.getId() == i) {
				return a;
			}
		}

		return null; // Not found
	}
	
	public List<Arena> getAllArenas(){
		return arenas;
	}

	public void loadArenas() {

		// ConfigManager cm = new ConfigManager(DuelNRTN.instance, 0);
		// // System.out.println("test");
		// if (cm.exists()) {
		// FileConfiguration fc = cm.getConfig();
		// // System.out.println("test created");
		//
		// ArenaManager.getManager().arenaSize = arenaSize;
		// System.out.println(arenaSize);
		// }
		try {
			for (int i = 1; i <= arenaSize; i++) {
				ConfigManager cm = new ConfigManager(DuelNRTN.instance, i);
				cm.getFile().getName();	
				if (cm.exists()) {
					FileConfiguration fc = cm.getConfig();
					// System.out.println("test created");
					int arenaSize = fc.getInt("latestArena");
					ArenaManager.getManager().arenaSize = arenaSize;
					//System.out.println(arenaSize);
				}
				
				double x = 3603;
				double y = 55;
				double z = -4158;

				// Temp locations
				Arena a = new Arena(new Location(Bukkit.getWorld("FlatLands"), x, y, z),
						new Location(Bukkit.getWorld("FlatLands"), x, y, z), i);
				a.load();
				this.arenas.add(a);
			}
		} catch (Exception e) {

		}
	}

	/**
	 * Adds the player to an arena
	 *
	 * <p>
	 * Gets the arena by ID, checks that it exists, and check the player isn't
	 * already in a game.
	 * </p>
	 *
	 * @param p
	 *            the player to add
	 * @param i
	 *            the arena ID. A check will be done to ensure its validity.
	 */
	public void addPlayer(Player p, int i) {
		Arena a = this.getArena(i);
		if (a == null) {
			p.sendMessage("Invalid arena!");
			return;
		}

		if (this.isInGame(p)) {
			p.sendMessage("Cannot join more than 1 game!");
			return;
		}

		// Adds the player to the arena player list
		a.getPlayers().add(p.getUniqueId());

		// Clear inventory and armor
		// p.getInventory().setArmorContents(null);
		// p.getInventory().clear();

		// Save the players's last location before joining,
		// then teleporting them to the arena spawn
		locs.put(p.getUniqueId(), p.getLocation());

		if (a.getPlayers().size() == 0) {
			p.teleport(a.getLocationA());
		} else {
			p.teleport(a.getLocationB());
		}

	}

	/**
	 * Removes the player from their current arena.
	 *
	 * <p>
	 * The player is allowed to not be in game, a check will be performed to
	 * ensure the validity of the arena
	 * </p>
	 *
	 * @param p
	 *            the player to remove from the arena
	 */
	public void removePlayer(Player p) {
		Arena a = null;

		// Searches each arena for the player
		for (Arena arena : this.arenas) {
			if (arena.getPlayers().contains(p.getUniqueId()))
				a = arena;
		}

		// Check arena validity
		if (a == null) {
			p.sendMessage("Invalid operation!");
			return;
		}

		// Remove from arena player lost
		a.getPlayers().remove(p.getName());

		// Teleport to original location, remove it too
		p.teleport(locs.get(p.getUniqueId()));
		locs.remove(p.getUniqueId());

		// Heh, you're safe now :)
		p.setFireTicks(0);
	}

	/**
	 * Creates an arena at the specified location
	 *
	 * @param l
	 *            the location for arena spawn
	 * @return the arena created
	 */
	public Arena createArena(String nameOfArena, Location l1, Location l2) {
		this.arenaSize++;

		Arena a = new Arena(l1, l2, this.arenaSize);
		this.arenas.add(a);
		a.setName(nameOfArena);
		ConfigManager cm = new ConfigManager(DuelNRTN.instance, arenaSize);
		// System.out.println("test");
		if (!cm.exists()) {

			FileConfiguration f = cm.getConfig();
			// f.set("Arena-object", a);
			// f.set("Arena-no", arenaSize);
			f.set("Arena-Name", nameOfArena);
			f.set("location1.x", l1.getX());

			f.set("location1.y", l1.getY());
			f.set("location1.z", l1.getY());

			f.set("location2.x", l2.getX());
			f.set("location2.y", l2.getY());
			f.set("location2.z", l2.getY());

			cm.saveConfig();
		}
		ConfigManager cm1 = new ConfigManager(DuelNRTN.instance, 0);
		if (!cm1.exists()) {
			FileConfiguration f1 = cm1.getConfig();
			f1.set("latestArena", arenaSize);
			// System.out.println("Set new Arena Size");
			cm1.saveConfig();
		}
		if (cm1.exists()) {
			FileConfiguration f1 = cm1.getConfig();
			f1.set("latestArena", arenaSize);
			// System.out.println("Set new Arena Size");
			cm1.saveConfig();
		}

		return a;
	}

	/**
	 * Checks if the player is currently in an arena
	 *
	 * @param p
	 *            the player to check
	 * @return {@code true} if the player is in game
	 */
	public boolean isInGame(Player p) {
		for (Arena a : this.arenas) {
			if (a.getPlayers().contains(p.getUniqueId()))
				return true;
		}
		return false;
	}

	public String serializeLoc(Location l) {
		return l.getWorld().getName() + "," + l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ();
	}

	public Location deserializeLoc(String s) {
		String[] st = s.split(",");
		return new Location(Bukkit.getWorld(st[0]), Integer.parseInt(st[1]), Integer.parseInt(st[2]),
				Integer.parseInt(st[3]));
	}
}