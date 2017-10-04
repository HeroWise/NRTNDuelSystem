package com.topobon.duel.events;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.topobon.duel.DuelNRTN;
import com.topobon.duel.arena.ArenaManager;

public class CommandPreprocessEvent implements Listener {
	DuelNRTN instance;
	public CommandPreprocessEvent(DuelNRTN instance) {
	this.instance = instance;
	}
	@EventHandler
	public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
	  Player p = event.getPlayer();

	  if(!p.isOp()) {
		  if(ArenaManager.getManager().isInGame(p)){
	      List<String> cmds = DuelNRTN.getBannedCommands();
	      for (String command : cmds) {
	        if (event.getMessage().equalsIgnoreCase("/" + command)) {
	          event.setCancelled(true);
	          p.sendMessage("§cYou do not have permission to do that during duels!");
	        
	      }}
	    }
	  }
	}
}
