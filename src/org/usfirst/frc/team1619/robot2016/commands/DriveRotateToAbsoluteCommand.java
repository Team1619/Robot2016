package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;

public class DriveRotateToAbsoluteCommand extends DriveRotateCommand {

  public DriveRotateToAbsoluteCommand(double angle) {
    super(angle, 0);
  }

  public DriveRotateToAbsoluteCommand(double angle, int timeout) {
    super(angle, Constants.AUTO_DRIVE_ROTATION_TOLERANCE, timeout);
  }

  public DriveRotateToAbsoluteCommand(double angle, double tolerance,
    int timeout) {
    super(angle, tolerance, Constants.DRIVE_PID_ROTATION_DEADZONE, timeout);
  }

  public DriveRotateToAbsoluteCommand(double angle, double tolerance,
    double deadBand, int timeout) {
    super(angle, tolerance, deadBand, timeout);
  }

  @Override
  protected void initialize() {
    super.initialize();

    rotationPID.setTarget(angle - sensorInput.getNavXHeading());
  }

}
