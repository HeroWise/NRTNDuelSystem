package com.topobon.duel.gameprocessing;

import org.bukkit.scheduler.BukkitRunnable;

import com.topobon.duel.DuelNRTN;

public class Timer {
	DuelNRTN instance;
	public Timer(DuelNRTN instance){
		timer();
	}
	public void timer(){
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				RequestManager.getManager().iterateValues();
				
			}
		}.runTaskTimer(DuelNRTN.instance, 0, 20);
	}
}
