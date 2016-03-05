package org.usfirst.frc.team1619.robot2016.util;

public class Range {
  public double max;
  public double min;

  public Range(double max, double min) {
    this.max = max;
    this.min = min;
  }

  public static boolean within(double a, double max, double min) {
    return a <= max && a >= min;
  }

  public static boolean within(double a, Range r) {
    return a <= r.max && a >= r.min;
  }

  public boolean within(double a) {
    return a <= max && a >= min;
  }
}
