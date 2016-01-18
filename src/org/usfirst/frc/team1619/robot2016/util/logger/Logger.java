package org.usfirst.frc.team1619.robot2016.util.logger;

import org.usfirst.frc.team1619.robot2016.Properties;

/**
 * Created by DanielHathcock on 10/6/15. Project: Logger Package:
 * org.usfirst.frc.team1619.logger
 *
 * This is the subclass of UGenericLogger which is meant to be used to log
 * generic information.
 *
 * All of this information can be categorized under the four main
 * "Logging Levels" which determines whether or not the information is actually
 * logged (via some property).
 */
public class Logger extends GenericLogger {

  public Logger() {
    super("UACRRobot" + Properties.getLoggingLevel() + "Log", ".txt");
    nextLog();
  }

  /**
   * Logs an error message to the log file.
   *
   * @param message
   *          The error message. All arguments separated by a space.
   */
  public void error(String... message) {
    log("ERROR", message);
  }

  /**
   * Logs a warning to the log file. The logging level must be greater than or
   * equal to WARNING
   *
   * @param message
   *          The warning message. All arguments separated by a space.
   */
  public void warning(String... message) {
    if (Properties.getLoggingLevel().compareTo(LoggingLevels.WARNING) >= 0) {
      log("WARNING", message);
    }
  }

  /**
   * Logs the info message to the log file. The logging level must be greater
   * than or equal to INFO
   *
   * @param message
   *          The info message. All arguments separated by a space.
   */
  public void info(String... message) {
    if (Properties.getLoggingLevel().compareTo(LoggingLevels.INFO) >= 0) {
      log("INFO", message);
    }
  }

  /**
   * Logs the debug message to the log file. The logging level must be greater
   * than or equal to DEBUG
   *
   * @param message
   *          The debug message. All arguments separated by a space.
   */
  public void debug(String... message) {
    if (Properties.getLoggingLevel().compareTo(LoggingLevels.DEBUG) >= 0) {
      log("DEBUG", message);
    }
  }

  /**
   * Adds the message to the loggingQueue to be written
   *
   * @param level
   *          The text version of the logging level
   * @param message
   *          The log message
   */
  protected void log(String level, String[] message) {
    message[0] = String.format("[%s] - %s", level, message[0]);
    fLoggingQueue.add(message);
  }
}
