package org.usfirst.frc.team1619.robot2016.IO.SocketTables;

import java.util.concurrent.ConcurrentHashMap;

public class SmashBoardValues {

  private static SmashBoardValues instance;

  static {
    instance = new SmashBoardValues();
  }

  private ConcurrentHashMap<String, Long> longMap;
  private ConcurrentHashMap<String, Double> doubleMap;
  private ConcurrentHashMap<String, String> stringMap;

  public static SmashBoardValues getInstance() {
    return instance;
  }

  public SmashBoardValues() {
    longMap = new ConcurrentHashMap<>();
    doubleMap = new ConcurrentHashMap<>();
    stringMap = new ConcurrentHashMap<>();
  }

  public ConcurrentHashMap<String, Long> getLongs() {
    return longMap;
  }

  public long getLong(String key) {
    return longMap.get(key);
  }

  public void setLong(String key, long value) {
    longMap.put(key, value);
  }

  public ConcurrentHashMap<String, Double> getDoubles() {
    return doubleMap;
  }

  public double getDouble(String key) {
    return doubleMap.get(key);
  }

  public void setDouble(String key, double value) {
    doubleMap.put(key, value);
  }

  public ConcurrentHashMap<String, String> getStrings() {
    return stringMap;
  }

  public String getString(String key) {
    return stringMap.get(key);
  }

  public void setString(String key, String value) {
    stringMap.put(key, value);
  }

}
