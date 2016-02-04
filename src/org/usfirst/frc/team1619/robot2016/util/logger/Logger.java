package org.usfirst.frc.team1619.robot2016.util.logger;

import org.usfirst.frc.team1619.robot2016.Constants;

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

  private static Logger instance;
  static {
    instance = new Logger();
  }
  
  public static Logger getInstance() {
    return instance;
  }
  
  /**
   * Enum which describes the logging levels:
   * 
   * Error: only log errors (calls to error())
   * Warning: log errors and any warnings (calls to error() or warning())
   * Info: log information about errors and warnings as well (error(), warning(), and info())
   * Debug: log everything (all calls to logger functions) 
   */
  public enum LoggingLevel {
    ERROR, WARNING, INFO, DEBUG;

    @Override
    public String toString() {
      return name().charAt(0) + name().substring(1).toLowerCase();
    }
  }
  
  private LoggingLevel loggingLevel; 
  
  private Logger() {
    super("UACRRobot" + Constants.LOGGING_LEVEL + "Log", ".txt");
    nextLog();
  }

  public void setLoggingLevel(LoggingLevel newLevel) {
    loggingLevel = newLevel;
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
    if (loggingLevel.compareTo(LoggingLevel.WARNING) >= 0) {
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
    if (loggingLevel.compareTo(LoggingLevel.INFO) >= 0) {
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
    if (loggingLevel.compareTo(LoggingLevel.DEBUG) >= 0) {
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
