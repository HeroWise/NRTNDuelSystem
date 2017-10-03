package com.topobon.duel;

/**
 * Copyright (c) 2017, HeroWise. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * The name of the author may not be used to endorse or promote products derived
 * from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.topobon.duel.arena.ArenaManager;
import com.topobon.duel.commands.DuelCommands;
import com.topobon.duel.utils.ConfigManager;

/**
 * <b>Team Death Match Extreme - NRTN </b>
 * <p>
 * This class is the Main class which @extends {@link - JavaPlugin}
 * <p>
 * The main purpose of this class is to:
 * <ul>
 * <li>Hold a custom class called Logo which holds Logo Object and make a new
 * Initial / Chevron
 * <li>Registers Commands
 * <li>Registers Events
 * </ul>
 * 
 * <i>It would be nice if you provide credit to me if you use this class or
 * plugin in a published / a already created project</i>
 * 
 * @author HeroWise
 * @version HW1.2_0TDMNRTN (Plugin Version)
 * @version 1.10 (Minecraft Server Version)
 * 
 */
public class DuelNRTN extends JavaPlugin {
	private static Logo logo; // Logo object

	private static Logo infoLogo;
	public static DuelNRTN instance;

	/**
	 * Method: Calls when class runs {@link #getPluginLoader()}
	 * <p>
	 * It's work is to:
	 * <p>
	 * <ul>
	 * <li>Set Logo/Chevron
	 * <li>Register Classes
	 * <li>Register Events
	 * <li>Register Events
	 * <li>Register Commands
	 */

	public void onEnable() {
		// new TeamDeathMatch(this);
		// new GameManager(this);
		instance = this;
		
//
		ConfigManager cm = new ConfigManager(DuelNRTN.instance, 0);
		// System.out.println("test");
		if (cm.exists()) {
			FileConfiguration fc = cm.getConfig();
			// System.out.println("test created");
			int arenaSize = fc.getInt("latestArena");
			ArenaManager.getManager().arenaSize = arenaSize;
			
		}
		ArenaManager.getManager().loadArenas();
	
		//getConfig().addDefault("latestArena", 1);
		setLogo(ChatColor.translateAlternateColorCodes('&', "&7&l[&6&lNaruto &c&lRTN&7&l] &r"));
		setInfoLogo(ChatColor.translateAlternateColorCodes('&', "&8&l[&6Info&8&l] &r"));
		/**
		 * Talks about the TDM plugin and its state
		 * 
		 */
	
		// Registering Commands
		this.getCommand("duel").setExecutor(new DuelCommands(this));
		// Registering Events
		// Bukkit.getPluginManager().registerEvents(new PlayerDeath(this),
		// this);
		// Bukkit.getPluginManager().registerEvents(new
		// PlayerFriendlyFire(this), this);
		
	}
	


	/**
	 * Method: returns logo Object and calls #getLogo() Method from the
	 * {@linkplain Logo.class}
	 * 
	 * @return Logo Object to #getLogo()
	 */
	public static String getInitials() {

		return logo.getLogo();
	}

	/**
	 * 
	 * @param string
	 *            - to set Logo
	 */
	public static void setLogo(String string) {
		logo = new Logo(string);
	}

	/**
	 * Method: returns logo Object and calls #getLogo() Method from the
	 * {@linkplain Logo.class}
	 * 
	 * @return Logo Object to #getLogo()
	 */
	public static String getInfoInitials() {

		return infoLogo.getLogo();
	}

	/**
	 * 
	 * @param string
	 *            - to set Logo
	 */
	public static void setInfoLogo(String string) {
		infoLogo = new Logo(string);
	}

}
