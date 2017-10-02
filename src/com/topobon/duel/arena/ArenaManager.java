package com.topobon.duel.arena;

import java.awt.List;
import java.util.ArrayList;

import org.bukkit.entity.Player;

public class ArenaManager {

	  private static ArrayList<Arena> arenas = new ArrayList<Arena>();
	  
	  public static Arena getArena(Integer arenaID)
	  {
	    for (Arena arena : arenas) {
	      if (arena.doesArenaExist(arenaID)) {
	        return arena;
	      }
	    }
	    return null;
	  }
	  
	  public static boolean createArena(Integer arenaID)
	  {
	    if (getArena(arenaID) == null)
	    {
	      arenas.add(new Arena(arenaID));
	      return true;
	    }
	    return false;
	  }
	  
	  public static boolean deleteParty(Player player)
	  {
	    if (getParty(player) != null)
	    {
	      if (getParty(player).isLeader(player))
	      {
	        for (Player p : getParty(player).getPlayers()) {
	          getParty(player).removePlayer(p);
	        }
	        parties.remove(getParty(player));
	        return true;
	      }
	      return false;
	    }
	    return false;
	  }
	  
	  public static List<Party> getParties()
	  {
	    return parties;
	  }
	  
	  public static void deleteParty(Party party)
	  {
	    if (party != null)
	    {
	      for (int i = 0; i < party.getPlayers().size(); i++) {
	        if (party.getPlayers().get(i) != null) {
	          party.getPlayers().remove(i);
	        }
	      }
	      parties.remove(party);
	    }
	  }
	}