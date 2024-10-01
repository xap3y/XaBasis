package eu.xap3y.xabasistest.listeners;

import eu.xap3y.xabasistest.XaBasisTest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().getInventory().addItem(XaBasisTest.testItem());

        XaBasisTest.texter.response(event.getPlayer(), "Welcome to the server!");
    }
}
