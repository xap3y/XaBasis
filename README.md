# XaBasis

Simple API for Bukkit plugins \
Also support Adventure components on modern PaperMC versions

# Features

- Texter
- DebugLogger
- ProgressbarCreator
- ItemCreator

## Features details
<details>
<summary>Texter</summary>

Texter is a simple class that allows to send messages to players with a predefined prefix.

### Example of usage

```java
public static Texter texter;

private void testTexters() {
    final String PREFIX = "&7[&6XaBasis&7] &r";
    texter = new Texter(PREFIX);

    texter.logConsole("&aThis is a test message");
    Texter.logConsoleRaw("&aThis is a also test message, but without a prefix :(");

    Player player = Bukkit.getPlayer("PlayerName");
    texter.response(player, "&aHello!");
}
```

</details>

<br>

<details>
<summary>DebugLogger</summary>

DebugLogger allows to log messages into a file. It is useful for debugging purposes.

### Example of usage

```java
public static DebugLogger debugLogger;

File logFile = new File(getDataFolder(), "log.txt");

debugLogger = new DebugLogger(logFile);
debugLogger.setIgnoreErrors(true);
debugLogger.setPattern("{%d--%i} -> %t | %m | %o");
debugLogger.init();

// Let's imagine that we are calling a SQL query, and we want to log it and also log the elapsed time that it took to execute the query
debugLogger.log("Calling SQL QUERY", LogType.CALL);
debugLogger.log("COLUMS: 2", LogType.RESULT, 871L, TimeUnit.MILLISECONDS);
```

</details>