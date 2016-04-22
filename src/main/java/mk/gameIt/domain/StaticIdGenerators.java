package mk.gameIt.domain;

/**
 * Created by Stefan on 22.04.2016.
 */
public class StaticIdGenerators {
    private static long commentGameIdGenerator = 0;
    private static long commentHardwareIdGenerator = 0;

    public StaticIdGenerators() {

    }

    public static synchronized long getCommentGameIdGenerator() {
        commentGameIdGenerator++;
        return commentGameIdGenerator;
    }

    public static synchronized long getCommentHardwareIdGenerator() {
        commentHardwareIdGenerator++;
        return commentHardwareIdGenerator;
    }
}
