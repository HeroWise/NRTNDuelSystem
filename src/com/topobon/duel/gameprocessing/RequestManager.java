package com.topobon.duel.gameprocessing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.entity.Player;

import com.topobon.duel.utils.Utility;

public class RequestManager {
	private static RequestManager rm;
	List<Request> senders;
	private Map<Player, List<Request>> requests = new HashMap<Player, List<Request>>();

	private RequestManager() {

	}

	public static RequestManager getManager() {
		if (rm == null)
			rm = new RequestManager();
		return rm;

	}

	public void sendRequest(Player sender, Player recipient) {
		if (senders == null) {
			senders = new ArrayList<Request>();
		}
		
		Request rq = new Request(sender, recipient);
		sender.sendMessage(
				Utility.messageToPlayer("&bYou have sent a duel request to &6" + recipient.getDisplayName() + "&b!"));
		recipient.sendMessage(
				Utility.messageToPlayer("&6" + sender.getDisplayName() + " &bhas sent you a duel request! do /d a <Player>"));
		senders.add(rq);
		requests.put(recipient, senders);

	}

	public void iterateValues() {

		for (Entry<Player, List<Request>> entry : requests.entrySet()) {
			Player key = entry.getKey();
			List<Request> values = entry.getValue();
			for (int i = 0; i < values.size(); i++) {
				// System.out.println(i+" and "+ values.size()+" time:
				// "+(System.currentTimeMillis() - ((Long)
				// values.get(i).getTime())));
				if ((System.currentTimeMillis() - ((Long) values.get(i).getTime()) > 10000L)) {

					key.sendMessage(Utility.messageToPlayer("&6" + values.get(i).getSender().getDisplayName()
							+ "'s &b duel request has been cancelled!"));
					entry.getValue().remove(i);

				}
			}

		}
	}

	public boolean doesSenderExist(Player receiver, Player sender) {
		for (Entry<Player, List<Request>> entry : requests.entrySet()) {
			Player key = entry.getKey();
			if (key == receiver) {
				List<Request> values = entry.getValue();
				for (int i = 0; i < values.size(); i++) {

					if (values.get(i).getSender() == sender) {
						entry.getValue().clear();
//						entry.getValue().remove(i);
						
						return true;

					}
				}

			}
		}

		return false;
	}

	// public RequestManager() {
	// this.requests = new HashMap();
	// }
	//

}
// for (Entry<Player, List<Request>> entry : requests.entrySet()) {
// Player key = entry.getKey();
// List<Request> values = entry.getValue();
// for(int i = 0; i<= values.size(); i++){
// if(values.get(i).getTime()){
//
// }
// }
//
//
// System.out.println("Key = " + key);
// System.out.println("Values = " + values + "n");
// }