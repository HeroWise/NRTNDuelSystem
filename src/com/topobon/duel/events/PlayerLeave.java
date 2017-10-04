package com.topobon.duel.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.topobon.duel.DuelNRTN;
import com.topobon.duel.arena.Arena;
import com.topobon.duel.arena.ArenaManager;

public class PlayerLeave implements Listener {

	DuelNRTN instance;

	public PlayerLeave(DuelNRTN instance) {
		this.instance = instance;
	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		
		Player playerWhoLeft = e.getPlayer();

		for (Arena a : ArenaManager.getManager().getAllArenas()) {
			if (a.getPlayers().contains(playerWhoLeft)) {
				if (a.getPlayers().get(0) == playerWhoLeft) {
					Player killer = a.getPlayers().get(1);
					ArenaManager.getManager().setWinner(killer, playerWhoLeft);
					
				} else if (a.getPlayers().get(1) == playerWhoLeft) {

					Player killer = a.getPlayers().get(0);
					ArenaManager.getManager().setWinner(killer, playerWhoLeft);
				}

			}
		}
	}
}
