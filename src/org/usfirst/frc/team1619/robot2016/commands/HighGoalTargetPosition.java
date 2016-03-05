package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;

public enum HighGoalTargetPosition {
  LEFT(Constants.LEFT_GOAL_TARGET_X, Constants.LEFT_GOAL_TARGET_Y,
    Constants.LEFT_GOAL_OFFSET_ANGLE), MIDDLE(Constants.MIDDLE_GOAL_TARGET_X,
      Constants.MIDDLE_GOAL_TARGET_Y,
      Constants.MIDDLE_GOAL_OFFSET_ANGLE), RIGHT(Constants.RIGHT_GOAL_TARGET_X,
        Constants.RIGHT_GOAL_TARGET_Y, Constants.RIGHT_GOAL_OFFSET_ANGLE);

  private double xCoordinate;
  private double yCoordinate;
  private int offsetAngle;

  private HighGoalTargetPosition(double xCoordinate, double yCoordinate,
    int offsetAngle) {
    this.xCoordinate = xCoordinate;
    this.yCoordinate = yCoordinate;
    this.offsetAngle = offsetAngle;
  }

  public double getTargetXCoordinate() {
    return xCoordinate;
  }

  public double getTargetYCoordinate() {
    return yCoordinate;
  }

  public int getOffsetAngle() {
    return offsetAngle;
  }
}
