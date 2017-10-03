package com.topobon.duel.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.topobon.duel.DuelNRTN;
import com.topobon.duel.arena.Arena;
import com.topobon.duel.arena.ArenaManager;

public class PlayerDeath implements Listener {

	DuelNRTN instance;

	public PlayerDeath(DuelNRTN instance) {
		this.instance = instance;
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		Player killedPlayer = e.getEntity().getPlayer();

		for (Arena a : ArenaManager.getManager().getAllArenas()) {
			if (a.getPlayers().contains(killedPlayer)) {
				if (a.getPlayers().get(0) == killedPlayer) {
					Player killer = a.getPlayers().get(1);
					ArenaManager.getManager().setWinner(killer, killedPlayer);
				} else if (a.getPlayers().get(1) == killedPlayer) {

					Player killer = a.getPlayers().get(0);
					ArenaManager.getManager().setWinner(killer, killedPlayer);
				}

			}
		}
	}
}
