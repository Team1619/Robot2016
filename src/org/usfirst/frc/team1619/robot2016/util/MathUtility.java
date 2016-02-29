package org.usfirst.frc.team1619.robot2016.util;

public class MathUtility {
  public static double constrain(double value, double max, double min) {
    return Math.min(Math.max(value, min), max);
  }

  public static float constrain(float value, float max, float min) {
    return Math.min(Math.max(value, min), max);
  }

  public static long constrain(long value, long max, long min) {
    return Math.min(Math.max(value, min), max);
  }

  public static int constrain(int value, int max, int min) {
    return Math.min(Math.max(value, min), max);
  }

  /**
   * Ensure that value < min and value > max
   * If not, return min or max respectively
   */
  public static double absMax(double value, double max, double min) {
    if (value > 0) {
      return Math.max(value, max);
    }
    else {
      return Math.min(value, min);
    }
  }

  /**
   * Ensure that value < min and value > max
   * If not, return min or max respectively
   */
  public static float absMax(float value, float max, float min) {
    if (value > 0) {
      return Math.max(value, max);
    }
    else {
      return Math.min(value, min);
    }
  }

  /**
   * Ensure that value < min and value > max
   * If not, return min or max respectively
   */
  public static long absMax(long value, long max, long min) {
    if (value > 0) {
      return Math.max(value, max);
    }
    else {
      return Math.min(value, min);
    }
  }

  /**
   * Ensure that value < min and value > max
   * If not, return min or max respectively
   */
  public static int absMax(int value, int max, int min) {
    if (value > 0) {
      return Math.max(value, max);
    }
    else {
      return Math.min(value, min);
    }
  }
}
