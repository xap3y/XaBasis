package eu.xap3y.xabasis.managers;

import lombok.Getter;
import lombok.Setter;

@Setter
public class ProgressBarCreator {

    @Getter
    private int max;

    @Getter
    private int current;

    private char CHAR_FILL = '■';

    private char CHAR_EMPTY = '■';

    private char FILL_COLOR = 'a';

    private char EMPTY_COLOR = 'f';

    /**
     * Creates a new progress bar with the given max and current values
     *
     * @param max     the maximum value of the progress bar
     * @param current the current value of the progress bar
     */
    public ProgressBarCreator(int max, int current) {
        this.max = Math.min(max, 50);
        this.current = Math.min(current, max);
        if (this.current < 0) {
            this.current = 0;
        }
    }

    /**
     * @return a string representation of the progress bar
     */
    public String create() {
        String fillText = String.valueOf(CHAR_FILL).repeat(current);
        int repeat = max - fillText.length();
        if (repeat < 0) {
            repeat = 0;
        }
        String emptyText = String.valueOf(CHAR_EMPTY).repeat(repeat);
        return "&" + FILL_COLOR + fillText + "&" + EMPTY_COLOR + emptyText;
    }

    /**
     * Resets the default values of the progress bar
     */
    public void resetDefaults() {
        CHAR_FILL = '■';
        CHAR_EMPTY = '■';
        FILL_COLOR = 'a';
        EMPTY_COLOR = 'f';
    }
}
