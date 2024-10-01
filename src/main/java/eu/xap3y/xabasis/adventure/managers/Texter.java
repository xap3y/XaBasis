package eu.xap3y.xabasis.adventure.managers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

/**
 * A class that manages text messages using adventure components
 */
@Setter
@Getter
public class Texter {

    private String PREFIX;

    /**
     * @param prefix the prefix to use
     */
    public Texter(String prefix) {
        PREFIX = prefix.replaceAll("&", "§");
    }

    /**
     * Logs a message to the console without a prefix
     *
     * @param comp the text to log
     */
    private ComponentLike addPrefix(ComponentLike comp) {
        return Component.text(PREFIX).append(comp);
    }

    /**
     * Logs a message to the console without a prefix
     *
     * @param comp the text to log
     */
    public void logConsole(ComponentLike comp) {
        Bukkit.getConsoleSender().sendMessage(addPrefix(comp));
    }

    /**
     * Sends a message to a player with a prefix
     *
     * @param p0 the player to send the message to
     * @param p1 the message to send
     */
    public void response(CommandSender p0, ComponentLike p1) {
        p0.sendMessage(addPrefix(p1));
    }

    /**
     * Logs a message to the console without a prefix
     *
     * @param comp the text to log
     */
    public static void logConsoleRaw(ComponentLike comp) {
        Bukkit.getConsoleSender().sendMessage(comp);
    }
}
