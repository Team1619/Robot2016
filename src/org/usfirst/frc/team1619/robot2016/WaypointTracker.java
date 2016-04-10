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

  private SensorInput sensorInput;
  private SmashBoard smashBoard;

  private double positionX;
  private double positionY;
  private double previousTranslation;
  private double centerAngle;

  WaypointTracker() {
    sensorInput = SensorInput.getInstance();
    smashBoard = SmashBoard.getInstance();

    positionX = 0;
    positionY = 0;
    previousTranslation = 0;
  }

  public void initialize() {
    positionX = 0;
    positionY = 0;
    previousTranslation = sensorInput.getDriveTranslation();
    centerAngle = sensorInput.getNavXHeading();
  }

  public void update() {
    updatePosition();

//    smashBoard.setWaypointPosition(positionX, positionY);
//    smashBoard.setWaypointOffsets(getAngleToTarget(0, 0), getDistanceToTarget(0, 0));
    System.out.print("Position: " + Math.rint(positionX) + ", " + Math.rint(positionY));
    System.out.println("    Angle: " + Math.rint(getAngleToTarget(0, 0)) + "    Distance: " + Math.rint(getDistanceToTarget(0, 0)));

    // More processing expensive, but I'm lazy and it makes sense.
    previousTranslation += getTranslationChange();
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
    return Math.toDegrees((Math.PI / 2) + Math.atan2(positionY - y, positionX - x));
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

  private void updatePosition() {
    positionX +=
      Math.sin(Math.toRadians(getAngle())) * getTranslationChange();
    positionY +=
      Math.cos(Math.toRadians(getAngle())) * getTranslationChange();
  }

  private double getAngle() {
    return sensorInput.getCenteredNavXHeading(centerAngle);
//    return sensorInput.getNavXHeading();
  }

  private double getTranslationChange() {
    return sensorInput.getDriveTranslation() - previousTranslation;
  }
}
