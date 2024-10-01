package eu.xap3y.xabasistest;

import eu.xap3y.xabasis.api.enums.LogType;
import eu.xap3y.xabasis.managers.DebugLogger;
import eu.xap3y.xabasis.managers.ItemCreator;
import eu.xap3y.xabasis.managers.ProgressBarCreator;
import eu.xap3y.xabasis.managers.Texter;
import eu.xap3y.xabasistest.listeners.PlayerJoinListener;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class XaBasisTest extends JavaPlugin {

    public static DebugLogger debugLogger;
    public static Texter texter;
    public static eu.xap3y.xabasis.adventure.managers.Texter adventureTexter;

    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        // Debug logger TESTS
        testDebugger();

        // Texter TESTS
        testTexters();

        ////////////////////////////////
        //         Other tests        //
        ////////////////////////////////

        // Create progressbar that have 10 characters and 5 of them are filled so the progress is 50%
        ProgressBarCreator pbr = new ProgressBarCreator(10, 5);
        pbr.setCHAR_EMPTY('|');
        pbr.setCHAR_FILL('|');
        pbr.setEMPTY_COLOR('e');
        pbr.setFILL_COLOR('9');
        Texter.logConsoleRaw("BAR #1 (50%): " + pbr.create());
        pbr.resetDefaults();
        Texter.logConsoleRaw("BAR #2 (50%): " + pbr.create());
        pbr.setCurrent(9);
        Texter.logConsoleRaw("BAR #3 (90%): " + pbr.create());
        pbr.setCurrent(22);
        Texter.logConsoleRaw("BAR #4 (220%): " + pbr.create());

        // Perfect example of how to use debug logger to log a message with a type and a time elapsed
        debugLogger.log("Registering listener", LogType.CALL);
        long start = System.nanoTime();
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        long end = System.nanoTime();
        long elapsedTime = end - start;
        debugLogger.log("Registered 1 listener", LogType.RESULT, elapsedTime, TimeUnit.NANOSECONDS);
    }

    @Override
    public void onDisable() {
        getLogger().info("XaBasisTest has been disabled!");
    }

    private void testDebugger() {
        File logFile = new File(getDataFolder(), "log.txt");

        debugLogger = new DebugLogger(logFile);
        debugLogger.setIgnoreErrors(true);
        debugLogger.setPattern("{%d--%i} -> %t | %m | %o");
        debugLogger.init();

        // Let's imagine that we are calling a SQL query, and we want to log it and also log the elapsed time that it took to execute the query
        debugLogger.log("Calling SQL QUERY", LogType.CALL);
        debugLogger.log("COLUMS: 2", LogType.RESULT, 871L, TimeUnit.MILLISECONDS);
    }

    private void testTexters() {
        final String PREFIX = "&7[&6XaBasis&7] &r";
        texter = new Texter(PREFIX);
        adventureTexter = new eu.xap3y.xabasis.adventure.managers.Texter(PREFIX);

        texter.logConsole("&aThis is a test message");
        Texter.logConsoleRaw("&aThis is a also test message, but without a prefix :(");

        adventureTexter.logConsole(Component.text("§aThis is a test message"));
        eu.xap3y.xabasis.adventure.managers.Texter.logConsoleRaw(Component.text("§aThis is a also test message, but without a prefix :("));
    }

    public static ItemStack testItem() {
        ItemCreator itemCreator = new ItemCreator(Material.NETHERITE_SWORD).setName("&aTEST SWORD").setLore("", "&8THIS SWORD IS TEST");
        itemCreator.addEnchants(1, Enchantment.DAMAGE_ALL, Enchantment.MENDING, Enchantment.DURABILITY);
        itemCreator.addEnchant(Enchantment.FIRE_ASPECT, 2);
        itemCreator.addEnchants(3, Enchantment.SWEEPING_EDGE, Enchantment.LOOT_BONUS_MOBS);
        itemCreator.setUnbreakable(true);
        itemCreator.setFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
        return itemCreator.create();
    }
}
