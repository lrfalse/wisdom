package com.wisdom.log;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 日志工具类
 *
 * @author YYF
 */
public class LogWriter {
    private static final Level ACCESS = Level.forName("ACCESS", 320);
    private static final Level MONITOR = Level.forName("MONITOR", 340);
    private static Logger logger = LogManager.getLogger(LogWriter.class);

    public static void error(String... args) {
        String msg = format(args);
        logger.error(msg);
    }

    public static void error(Throwable t, String... args) {
        StringBuffer buffer = new StringBuffer();
        String msg = format(args);
        buffer.append(msg).append(t.getMessage()).append("\r\n");
        StackTraceElement[] stackTrace = t.getStackTrace();
        if (stackTrace != null && stackTrace.length > 0) {
            for (int i = 0; i < stackTrace.length; i++) {
                if (i > 10) {
                    break;
                }
                buffer.append("\t").append(stackTrace[i]).append("\r\n");
            }
        }
        buffer.append("\t").append("...");
        logger.error(buffer.toString());
    }

    public static void access(String... args) {
        String msg = format(args);
        logger.log(ACCESS, msg);
    }

    public static void monitor(String... args) {
        String msg = format(args);
        logger.log(MONITOR, msg);
    }

    public static void info(String... args) {
        String msg = format(args);
        logger.info(msg);
    }

    private static String format(String... args) {
        if (args == null || args.length <= 0) {
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        for (String arg : args) {
            buffer.append(arg).append(" -");
        }
        return buffer.toString();
    }
}
