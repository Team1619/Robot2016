package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;

public class DriveRotateToCameraTarget extends DriveRotateToAngle {

  @Override
  public boolean isReadyForActive() {
    return driverInput
      .getDriverButton(Constants.DRIVER_BUTTON_DRIVE_TURN_TO_CAMERA);
  }

  @Override
  protected double getRotationTarget() {
    return -smashBoard.getRotationOffsetToAligned();
  }
}