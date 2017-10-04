package com.topobon.duel.gameprocessing;

import org.bukkit.entity.Player;

public class Request
{
  private Player sender;
  private Player target;

  private final long time;
  
  public Request(Player sender, Player target)
  {
    this.sender = sender;
    this.target = target;
    this.time = System.currentTimeMillis();
  }
  
  public Player getSender()
  {
    return this.sender;
  }
  
  public Player getTarget()
  {
    return this.target;
  }

  public long getTime()
  {
    return this.time;
  }
}
