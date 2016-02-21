package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.IO.RobotOutput;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;
import org.usfirst.frc.team1619.robot2016.IO.SmashBoard;
import org.usfirst.frc.team1619.robot2016.util.GenericPID;

public class DriveStraightForDistance implements Command {

  private RobotOutput robotOutput;
  private SensorInput sensorInput;
  private SmashBoard smashBoard;

  private GenericPID translationPID;
  private GenericPID rotationPID;

  private double initialAngle;

  private double targetDistance;
  private double targetPosition;

  public DriveStraightForDistance(double distance) {
    targetDistance = distance;

    robotOutput = RobotOutput.getInstance();
    sensorInput = SensorInput.getInstance();
    smashBoard = SmashBoard.getInstance();

    translationPID = new GenericPID();
    translationPID.setValues(Constants.DRIVE_PID_TRANSLATION);
    translationPID.setIRange(Constants.DRIVE_PID_ROTATION_IRANGE);
    rotationPID = new GenericPID();
    rotationPID.setValues(Constants.DRIVE_PID_ROTATION);
    rotationPID.setIRange(Constants.DRIVE_PID_ROTATION_IRANGE);
  }

  @Override
  public void initialize() {
    initialAngle = sensorInput.getNavXHeading();
    targetPosition =
      targetDistance + sensorInput.getDriveRightEncoderPosition();

    translationPID.setTarget(0.0);
    rotationPID.setTarget(0.0);
  }

  @Override
  public void update() {
    translationPID
      .calculate(sensorInput.getDriveRightEncoderPosition() - targetPosition);
    rotationPID.calculate(
      (sensorInput.getNavXHeading() - initialAngle + 180) % 360 - 180);
    robotOutput.arcadeDrive(
      Math.max(Math.min(-translationPID.get(), 0.75), -0.75),
      rotationPID.get());
  }

  @Override
  public void destruct() {
    robotOutput.arcadeDrive(0.0, 0.0);
    smashBoard.setDistanceFrom(1000.0);
  }

  @Override
  public boolean finished() {
    double distanceFrom =
      targetPosition - sensorInput.getDriveRightEncoderPosition();
    smashBoard.setDistanceFrom(distanceFrom);
    return Math.abs(distanceFrom) < 0.5;
  }

}
