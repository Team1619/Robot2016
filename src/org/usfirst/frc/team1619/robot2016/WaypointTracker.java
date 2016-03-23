package org.usfirst.frc.team1619.robot2016;

import org.usfirst.frc.team1619.robot2016.IO.SensorInput;
import org.usfirst.frc.team1619.robot2016.IO.SocketTables.SmashBoard;

public class WaypointTracker {
  private static WaypointTracker instance;

  static {
    try {
      instance = new WaypointTracker();
    }
    catch (Throwable exception) {
      exception.printStackTrace();
      throw exception;
    }
  }

  public static WaypointTracker getInstance() {
    return instance;
  }

  protected SensorInput sensorInput;
  protected SmashBoard smashBoard;

  protected double positionX;
  protected double positionY;
  protected double previousTranslation;
  protected double previousAngle;
  protected double centerAngle;

  WaypointTracker() {
    positionX = 0;
    positionY = 0;
    previousTranslation = 0;
    previousAngle = 0;
  }

  public void initialize() {
    positionX = 0;
    positionY = 0;
    previousTranslation = sensorInput.getDriveTranslation();
    centerAngle = sensorInput.getNavXHeading();
    previousAngle = sensorInput.getCenteredNavXHeading(centerAngle);
  }

  public void update() {
    updatePosition();

    smashBoard.setWaypointPosition(positionX, positionY);
    smashBoard.setWaypointOffsets(getAngleToTarget(0, 0), getDistanceToTarget(0, 0));

    // More processing expensive, but I'm lazy and it makes sense.
    previousTranslation += getTranslationChange();
    previousAngle += getAngleChange();
  }

  /**
   * Get the differance in angle between the current angle and position and a
   * given position.
   * 
   * @param x
   * @param y
   * @return Difference in angle in degrees
   */
  public double getAngleToTarget(double x, double y) {
    return Math.toDegrees(Math.atan2(positionY - y, positionX - x));
  }

  /**
   * Get the distance from the robot's current position to a target position.
   * 
   * @param x
   * @param y
   * @return Distance in inches
   */
  public double getDistanceToTarget(double x, double y) {
    return Math.sqrt(
      (positionX - x) * (positionX - x) + (positionY - y) * (positionY - y));
  }

  public double getPositionX() {
    return positionX;
  }

  public double getPositionY() {
    return positionY;
  }

  protected void updatePosition() {
    positionX +=
      Math.cos(Math.toRadians(getAngleChange())) * getTranslationChange();
    positionY +=
      Math.sin(Math.toRadians(getAngleChange())) * getTranslationChange();
  }

  protected double getAngleChange() {
    return sensorInput.getCenteredNavXHeading(centerAngle) - previousAngle;
  }

  protected double getTranslationChange() {
    return sensorInput.getDriveTranslation() - previousTranslation;
  }
}
