package com.topobon.duel.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.topobon.duel.DuelNRTN;
import com.topobon.duel.arena.Arena;
import com.topobon.duel.arena.ArenaManager;
import com.topobon.duel.utils.ConfigManager;
import com.topobon.duel.utils.Utility;

public class DuelCommands implements CommandExecutor {
	DuelNRTN instance;

	protected int numberOfSeconds;

	public DuelCommands(DuelNRTN instance) {
		this.instance = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Check for permissions
		if (cmd.getName().equalsIgnoreCase("duel")) {
			if (args.length == 0) {
				CommandSender player = sender;

				player.sendMessage(Utility.messageToPlayer(("&8&m========&b&lTeamDeathMatch&8&m========")));
				player.sendMessage(Utility.messageToPlayer("&7/&c&ltdm &8- &ajoin"));
				player.sendMessage(Utility.messageToPlayer("&8&m=============================="));
			}

			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("list") && sender instanceof Player) {
					Player pSender = (Player) sender;
					Player p = (Player) sender;

					// ArenaManager.getManager().loadArenas(p);
					// for(int i = 1; i <=
					// DuelNRTN.instance.getConfig().getInt("latestArena");
					// i++){
					// ConfigManager cm = new ConfigManager(DuelNRTN.instance,
					// i);
					//
					// p.sendMessage(cm.getConfig().getDouble("Location1.x")+ "
					// TESTSTT");
					// }

				}

				if (args[0].equalsIgnoreCase("initiate") && sender instanceof Player) {
					Player pSender = (Player) sender;
					Player p = (Player) sender;
					p.sendMessage(Utility.sendInfo("&aInitiated Duel plugin! Instantiating DefaultArenaConfig with id '0'"));
					p.sendMessage(Utility.sendInfo("&aComplete!"));
					ConfigManager cm1 = new ConfigManager(DuelNRTN.instance, 0);
					if (!cm1.exists()) {
						FileConfiguration f1 = cm1.getConfig();
						f1.set("latestArena", 0);
						System.out.println("Set new Arena Size");
						cm1.saveConfig();
					}
					if (cm1.exists()) {
						FileConfiguration f1 = cm1.getConfig();
						f1.set("latestArena", ArenaManager.getManager().arenaSize);
						System.out.println("Set new Arena Size");
						cm1.saveConfig();
					}

				}
				if (args[0].equalsIgnoreCase("create") && sender instanceof Player) {
					Player pSender = (Player) sender;
					Player p = (Player) sender;
					p.sendMessage(Utility.sendInfo("&aSuccessfully created Arena!"));
					// Create
					ArenaManager.getManager().createArena(p.getLocation(), p.getLocation().add(0, 0, 2));

					// Join
					// int num = 0;
					// try {
					// num = Integer.parseInt(args[1]);
					// } catch (NumberFormatException e) {
					// p.sendMessage("Invalid arena ID");
					// return;
					// }
					// ArenaManager.getManager().addPlayer(p, num);

					// Leave
					// ArenaManager.getManager().removePlayer(p);

				}
			}
		}
		return true;
	}
}
