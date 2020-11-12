package util;

import java.util.logging.Logger;

public class IntMonitor {
    public enum Mode {
        LINEAR,
        EXPONENTIAL
    }

    private String label;
    private int step;
    private Integer last = null;
    private Mode mode;

    public IntMonitor(String label, int step, Mode mode) {
        this.label = label;
        this.step = step;
        this.mode = mode;
    }

    public void update(int current) {
        if (last == null) {
            Logger.getGlobal().info(label + ": " + current);
            last = current;
        }
        if (Math.abs(last - current) >  + step) {
            last = current;
            Logger.getGlobal().info(label + ": " + current);
        }
    }
}
