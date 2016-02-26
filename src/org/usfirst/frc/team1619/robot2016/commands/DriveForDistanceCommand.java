package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.framework.Command;

public class DriveForDistanceCommand extends Command {

  private double distance;
  private double targetPosition;

  public DriveForDistanceCommand(double distance) {
    super();

    this.distance = distance;
  }

  @Override
  protected void initialize() {
    targetPosition = sensorInput.getDriveRightEncoderPosition() + distance;

    drivePID.resetRotation();
    drivePID.resetTranslation();
    drivePID.setRotationTarget(sensorInput.getNavXHeading());
    drivePID.setTranslationTarget(targetPosition);
  }

  @Override
  protected void update() {
    robotOutput.arcadeDrive(-drivePID.getTranslation(), drivePID.getRotation());
  }

  @Override
  public void pause() {
    robotOutput.arcadeDrive(0.0, 0.0);
  }

  @Override
  public void destruct() {
    robotOutput.arcadeDrive(0.0, 0.0);
  }

}
