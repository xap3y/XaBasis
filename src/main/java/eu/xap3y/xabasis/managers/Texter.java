package eu.xap3y.xabasis.managers;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * A class that manages text messages
 */
@Getter
@Setter
@SuppressWarnings("ALL")
public class Texter  {

    private String PREFIX;

    /**
     * @param text the text to color
     * @return the colored text
     */
    public static String colored(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    /**
     * Logs a message to the console without a prefix
     *
     * @param text the text to log
     */
    public static void logConsoleRaw(String text) {
        Bukkit.getConsoleSender().sendMessage(colored(text));
    }

    /**
     * Logs a message to the console with a prefix
     *
     * @param text the text to log
     */
    public void logConsole(String text) {
        Bukkit.getConsoleSender().sendMessage(colored(this.PREFIX + text));
    }

    /**
     * Sends a message to a player with a prefix
     *
     * @param p0 the player to send the message to
     * @param p1 the message to send
     */
    public static void responseRaw(CommandSender p0, String p1) {
        p0.sendMessage(colored(p1));
    }

    /**
     * Sends a message to a player with a prefix
     *
     * @param p0 the player to send the message to
     * @param p1 the message to send
     */
    public void response(CommandSender p0, String p1) {
        p0.sendMessage(colored(this.PREFIX + p1));
    }

    /**
     * Creates a new texter with the given prefix
     *
     * @param prefix the prefix to use
     */
    public Texter(String prefix) {
        PREFIX = prefix;
    }
}
