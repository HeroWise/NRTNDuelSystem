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
import com.topobon.duel.gameprocessing.RequestManager;
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
		if (cmd.getName().equalsIgnoreCase("nduel")) {
			if (args.length == 0) {
				CommandSender player = sender;
				if (!player.isOp()) {
					player.sendMessage(Utility.messageToPlayer(("&8&m========&b&lDuel NRTN&8&m============")));
					player.sendMessage(Utility
							.messageToPlayer("&7/&c&lnd &8- &aduel <player> &8-&b Challenge a player to a duel"));
					player.sendMessage(Utility.messageToPlayer("&7/&c&lnd &8- &aa <player> &8- &bAccept duel request"));
					player.sendMessage(Utility.messageToPlayer("&8&m=============================="));
				} else {
					RequestManager.getManager().iterateValues();
					player.sendMessage(Utility.messageToPlayer(("&8&m========&b&lArena configuration&8&m========")));
					player.sendMessage(Utility.messageToPlayer("&7/&c&lnd &8- &acreate <name>"));
					player.sendMessage(Utility.messageToPlayer("&7/&c&lnd &8- &asetName <id>"));
					player.sendMessage(Utility.messageToPlayer("&7/&c&lnd &8- &alist"));
					player.sendMessage(Utility.messageToPlayer("&7/&c&lnd &8- &astateloc <id>"));
					player.sendMessage(Utility.messageToPlayer("&7/&c&lnd &8- &aSetLocationA <id>"));
					player.sendMessage(Utility.messageToPlayer("&7/&c&lnd &8- &aSetLocationB <id>"));
					player.sendMessage(Utility.messageToPlayer("&7/&c&lnd &8- &aEnable <id>"));
					player.sendMessage(Utility.messageToPlayer("&7/&c&lnd &8- &aDisable <id>"));
					player.sendMessage(Utility.messageToPlayer("&8&m============================================"));

				}

			}

			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("create") && sender instanceof Player) {
					Player p = (Player) sender;
					if (args.length < 2) {
						p.sendMessage(Utility.sendInfo("&4Usage for command is: /d create <Name for Arena>"));
						return true;
					}
					ArenaManager.getManager().createArena(p.getLocation(), p.getLocation().add(0, 0, 2), args[1]);
					p.sendMessage(Utility.sendInfo("&aSuccessfully created Arena!"));

					p.sendMessage(Utility.sendInfo("This Arena's ID is: &6" + ArenaManager.getManager().arenaSize
							+ "&r\n Please use this if you want to edit arena information!"));
					// Create

				}

				if (args[0].equalsIgnoreCase("setName") && sender instanceof Player) {

					Player p = (Player) sender;
					if (args.length < 2) {
						p.sendMessage(Utility.sendInfo("&4usage for command is: /d setName <Name for Arena>"));
						return true;
					}
					ArenaManager.getManager().getArena(Integer.valueOf(args[1])).setName(args[2]);
					p.sendMessage(Utility.sendInfo("&aSuccessfully changed arena name to&8:&6 " + args[2]
							+ " &r\nArena ID is&8: &6" + Integer.valueOf(args[1])));
					ConfigManager cm1 = new ConfigManager(DuelNRTN.instance, Integer.valueOf(args[1]));
					if (!cm1.exists()) {
						FileConfiguration f1 = cm1.getConfig();
						f1.set("Arena-Name", args[2]);
						// System.out.println("Set new Arena Size");
						cm1.saveConfig();
					}
					if (cm1.exists()) {
						FileConfiguration f1 = cm1.getConfig();
						f1.set("Arena-Name", args[2]);
						// System.out.println("Set new Arena Size");
						cm1.saveConfig();
					}

					// Create

				}
				if (args[0].equalsIgnoreCase("list") && sender instanceof Player) {
				
					Player p = (Player) sender;
					for (Arena arena : ArenaManager.getManager().getAllArenas()) {
						if (arena.isEnabled()) {
							p.sendMessage(Utility.decodeMessage(
									"&b " + arena.getId() + " &7- &6" + arena.getName() + " &8[&aEnabled&8] "));
						} else {
							p.sendMessage(Utility.decodeMessage(
									"&b " + arena.getId() + " &7- &6" + arena.getName() + " &8[&cDisabled&8] "));
						}

					}
					p.sendMessage(Utility.decodeMessage("&bare the Arena IDs"));

				}
				if (args[0].equalsIgnoreCase("stateloc") && sender instanceof Player) {

					Player p = (Player) sender;

					p.sendMessage(Utility.decodeMessage("&bA: " + ArenaManager.getManager().serializeLoc(
							ArenaManager.getManager().getArena(Integer.valueOf(args[1])).getLocationA())));
					p.sendMessage(Utility.decodeMessage("&bB: " + ArenaManager.getManager().serializeLoc(
							ArenaManager.getManager().getArena(Integer.valueOf(args[1])).getLocationB())));
				}

				if (args[0].equalsIgnoreCase("enable") && sender instanceof Player) {

					Player p = (Player) sender;
					if (args.length < 2) {
						p.sendMessage(Utility.sendInfo("&4Usage for command is: /d setName <Name for Arena>"));
						return true;
					}
					ConfigManager cm1 = new ConfigManager(DuelNRTN.instance, Integer.valueOf(args[1]));
					if (!cm1.exists()) {
						FileConfiguration f1 = cm1.getConfig();
						f1.set("isEnabled", true);
						// System.out.println("Set new Arena Size");
						ArenaManager.getManager().getArena(Integer.valueOf(args[1])).setEnabled(true);
						cm1.saveConfig();
						p.sendMessage(Utility.sendInfo("&aSuccessfully enabled &6" + Integer.valueOf(args[1]) + "&b!"));
					}
					if (cm1.exists()) {
						FileConfiguration f1 = cm1.getConfig();
						f1.set("isEnabled", true);
						// System.out.println("Set new Arena Size");
						ArenaManager.getManager().getArena(Integer.valueOf(args[1])).setEnabled(true);
						cm1.saveConfig();
						p.sendMessage(Utility.sendInfo("&aSuccessfully enabled &6" + Integer.valueOf(args[1]) + "&b!"));
					}

				}

				// FIX
				if (args[0].equalsIgnoreCase("disable") && sender instanceof Player) {
					Player pSender = (Player) sender;
					Player p = (Player) sender;
					if (args.length < 2) {
						p.sendMessage(Utility.sendInfo("&4Usage for command is: /nd setName <Name for Arena>"));
						return true;
					}
					ConfigManager cm1 = new ConfigManager(DuelNRTN.instance, Integer.valueOf(args[1]));
					if (!cm1.exists()) {
						FileConfiguration f1 = cm1.getConfig();
						f1.set("isEnabled", false);
						p.sendMessage(
								Utility.sendInfo("&aSuccessfully disabled &6" + Integer.valueOf(args[1]) + "&b!"));
						ArenaManager.getManager().getArena(Integer.valueOf(args[1])).setEnabled(false);
						cm1.saveConfig();
					}
					if (cm1.exists()) {
						FileConfiguration f1 = cm1.getConfig();
						f1.set("isEnabled", false);
						p.sendMessage(
								Utility.sendInfo("&aSuccessfully disabled &6" + Integer.valueOf(args[1]) + "&b!"));
						ArenaManager.getManager().getArena(Integer.valueOf(args[1])).setEnabled(false);
						cm1.saveConfig();
					}

				}
				if (args[0].equalsIgnoreCase("duel") && sender instanceof Player) {
					Player p = (Player) sender;
					Player target = Bukkit.getServer().getPlayer(args[1]);
					if (target == null) {
						p.sendMessage(Utility.decodeMessage(("&cPlease Specify a proper player!")));
						return true;
					}
					if (target == p) {
						p.sendMessage(
								Utility.decodeMessage(("&cPlease Specify a proper player! You cant fight yourself!")));
						return true;
					}
					RequestManager.getManager().sendRequest(p, target);

				}
				if (args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("accept") && sender instanceof Player) {
					Player p = (Player) sender;
					Player target = Bukkit.getServer().getPlayer(args[1]);
					if (target == null) {
						p.sendMessage(Utility.decodeMessage(("&cPlease Specify a proper player!")));
						return true;
					}
					if (target == p) {
						p.sendMessage(
								Utility.decodeMessage(("&cPlease Specify a proper player! You cant fight yourself!")));
						return true;
					}
					if (RequestManager.getManager().doesSenderExist(p, target)) {

						if (ArenaManager.getManager().isInGame(p)) {

							p.sendMessage(Utility.messageToPlayer("&cYour are already in a game!"));

							return true;
						}
						if (ArenaManager.getManager().isInGame(target)) {
							p.sendMessage(
									Utility.messageToPlayer("&6" + target.getName() + " &cis already in a duel!"));
							return true;
						}
						if (ArenaManager.getManager().addPlayers(p, target)) {
							p.sendMessage(Utility.messageToPlayer(
									"&bYou are fighting against&8: &6" + target.getDisplayName() + "&b!"));
							target.sendMessage(Utility
									.messageToPlayer("&bYou are fighting against&8: &6" + p.getDisplayName() + "&b!"));
						} else {
							p.sendMessage(Utility.messageToPlayer("&cWait till other duels are finished!"));

						}
					}

				}

				if (args[0].equalsIgnoreCase("initiate") && sender instanceof Player) {
					Player pSender = (Player) sender;
					Player p = (Player) sender;
					p.sendMessage(
							Utility.sendInfo("&aInitiated Duel plugin! Instantiating DefaultArenaConfig with id '0'"));
					p.sendMessage(Utility.sendInfo("&aComplete!"));
					ConfigManager cm1 = new ConfigManager(DuelNRTN.instance, 0);
					if (!cm1.exists()) {
						FileConfiguration f1 = cm1.getConfig();
						f1.set("latestArena", 0);
						// System.out.println("Set new Arena Size");
						cm1.saveConfig();
					}
					if (cm1.exists()) {
						FileConfiguration f1 = cm1.getConfig();
						f1.set("latestArena", ArenaManager.getManager().arenaSize);
						// System.out.println("Set new Arena Size");
						cm1.saveConfig();
					}

				}

				if (args[0].equalsIgnoreCase("setLocationA") && sender instanceof Player) {
					Player pSender = (Player) sender;
					Player p = (Player) sender;
					if (args.length < 2) {
						p.sendMessage(Utility.sendInfo("&4usage for command is: /d setLocationA <id for arena>"));
						return true;
					}
					Arena arena = ArenaManager.getManager().getArena(Integer.valueOf(args[1]));
					arena.setLocationA(p.getLocation());
					p.sendMessage(Utility.sendInfo("&aSuccessfully set Location A for Arena: " + args[1]));

				}
				if (args[0].equalsIgnoreCase("setLocationB") && sender instanceof Player) {
					Player pSender = (Player) sender;
					Player p = (Player) sender;
					if (args.length < 2) {
						p.sendMessage(Utility.sendInfo("&4usage for command is: /d setLocationB <id for arena>"));
						return true;
					}
					Arena arena = ArenaManager.getManager().getArena(Integer.valueOf(args[1]));
					arena.setLocationB(p.getLocation());
					p.sendMessage(Utility.sendInfo("&aSuccessfully set Location B for Arena: " + args[1]));

				}

			}
		}
		return true;
	}
}
