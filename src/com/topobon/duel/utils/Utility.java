package com.topobon.duel.utils;

import org.bukkit.ChatColor;

import com.topobon.duel.DuelNRTN;



public class Utility {
	public static String messageToPlayer(String message) {
        return message = ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&', DuelNRTN.getInitials() + " " + message));
    }
	
	public static String decodeMessage(String message) {
        return message = ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&',message));
    }
	public static String sendInfo(String message) {
        return message = ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&', DuelNRTN.getInfoInitials() +  message));
    }

}