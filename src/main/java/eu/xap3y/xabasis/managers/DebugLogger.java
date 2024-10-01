package eu.xap3y.xabasis.managers;

import eu.xap3y.xabasis.api.enums.LogType;
import eu.xap3y.xabasis.util.Util;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("CallToPrintStackTrace")
@Setter
@Getter
public class DebugLogger {

    private boolean ignoreErrors = true;

    private File file;

    // %d - date            (dd.MM)
    // %i - time            (HH:mm:ss.S)
    // %m - log message
    // %t - type
    // %o - timeout
    private String pattern = "[%d-%i] %t %m %o";

    public DebugLogger(File file) {
        this.file = file;

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                if (!ignoreErrors) throw new RuntimeException(e);
            }
        }
    }

    /**
     * Initializes the logger
     */
    public void init() {
        logRaw("-+------------------------------------------------------------------------------------------+-");
        logRaw("Debug logger has been initialized at " + Util.getTimeDate() + "-" + Util.getTimeWithoutDate());
        logRaw("-+------------------------------------------------------------------------------------------+-");
    }

    /**
     * @param message the message to log
     */
    public void logRaw(String message) {
        appendToFileAsync(message);
    }

    /**
     * @param message the message to log
     */
    public void log(String message) {
        appendToFileAsync(format(message, LogType.INFO, ""));
    }

    /**
     * @param message the message to log
     * @param type    the type of the message
     */
    public void log(String message, LogType type) {
        appendToFileAsync(format(message, type, ""));
    }

    /**
     * @param message the message to log
     * @param type    the type of the message
     * @param timeTook the time elapsed
     * @param timeUnit the time unit of the elapsed time
     */
    public void log(String message, LogType type, long timeTook, TimeUnit timeUnit) {
        String time = timeUnit.toMillis(timeTook) + "ms";
        appendToFileAsync(format(message, type, time));
    }

    private String format(String message, LogType type, String time) {
        return pattern
                .replace("%d", Util.getTimeDate())
                .replace("%i", Util.getTimeWithoutDate())
                .replace("%m", message)
                .replace("%t", "(" + type.toString() + ")")
                .replace("%o", time);
    }

    private void appendToFileAsync(String message) {
        CompletableFuture.runAsync(() -> {
            try {
                Files.write(Path.of(file.getPath()), (message + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            } catch (IOException e) {
                if (!ignoreErrors) e.printStackTrace();
            }
        });
    }
}
