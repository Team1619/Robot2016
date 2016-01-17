package org.usfirst.frc.team1619.robot2016.util.logger;

/**
 * Created by DanielHathcock on 10/20/15. Project: Logger Package:
 * org.usfirst.frc.team1619.logger
 */
@SuppressWarnings("serial")
public class Properties extends java.util.Properties {

  private static final Properties sProperties = new Properties();

  private Properties() {
    put("TEST_MODE", Boolean.FALSE);
    put("COMPETITION_MODE", Boolean.FALSE);
    put("LOGGING_LEVEL", LoggingLevels.DEBUG);
    put("LOG_FOLDER_PATH", "/home/lvuser/log/");
  }

  /**
   * Gets a value statically from the hash table.
   *
   * @param key
   *          The String the value is being mapped to.
   * @param type
   *          The data type of the desired value.
   * @return The value which corresponds to the specified key, as the specified
   *         type.
   */
  public static <T> T getProperty(String key, Class<T> type) {

    return sProperties.get(key, type);
  }

  /**
   * Gets a value from the hash table.
   *
   * @param key
   *          The String the value is being mapped to.
   * @param type
   *          The data type of the desired value.
   * @return The value which corresponds to the specified key, as the specified
   *         type.
   */
  @SuppressWarnings("unchecked")
  private <T> T get(String key, Class<T> type) {

    return (T)get(key);
  }

  /**
   * Checks to see if in TEST_MODE.
   *
   * @return Whether or not is in TEST_MODE.
   */
  public static boolean isTestMode() {

    return sProperties.get("TEST_MODE", Boolean.class);
  }

  /**
   * Gets the current logging level.
   *
   * @return The logging level.
   */
  public static LoggingLevels getLoggingLevel() {

    return sProperties.get("LOGGING_LEVEL", LoggingLevels.class);
  }
}
