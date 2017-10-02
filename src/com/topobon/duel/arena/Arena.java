package com.topobon.duel.arena;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class Arena {
	private int arenaID;
	private List<Player> players;
	private List<Player> invitations;
	
	public Arena(int arenaID) {
		this.arenaID = arenaID;
		this.players = new ArrayList<Player>();
		this.invitations = new ArrayList<Player>();
	}
//
//	public boolean isLeader(Player player) {
//		return this.leader == player;
//	}

	public List<Player> getPlayers() {
		return this.players;
	}

//	public Player getLeader() {
//		return this.leader;
//	}

	public boolean doesArenaExist(int idNumberForArena) {
			
		if (this.arenaID == idNumberForArena) {
			return true;
		}
		
		return false;
	}

	public boolean addPlayer(Player player) {
		if ((!this.players.contains(player)) && (this.invitations.contains(player))) {
			this.players.add(player);
			this.invitations.remove(player);
			return true;
		}
		return false;
	}

	public boolean removePlayer(Player player) {
		if (this.players.contains(player)) {
			this.players.remove(player);
			return true;
		}
		return false;
	}

//	public void invite(final Player p) {
//		this.invitations.add(p);
//		p.sendMessage("You are invited in" + this.leader.getName() + "'s party.");
//		p.sendMessage("" + this.leader.getName());
//		new BukkitRunnable() {
//
//			public void run() {
//				if (Party.this.invitations.contains(p)) {
//					Party.this.invitations.remove(p);
//					p.sendMessage("7Your invitations has been expired.");
//					Party.this.leader.sendMessage("The invitation to " + p.getName() + " has been expired.");
//				}
//			}
//		};
//
//	}
}


