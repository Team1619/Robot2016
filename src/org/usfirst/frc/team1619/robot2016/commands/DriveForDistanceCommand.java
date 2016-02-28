package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.framework.Command;
import org.usfirst.frc.team1619.robot2016.util.GenericTimer;
import org.usfirst.frc.team1619.robot2016.util.PID.DriveRotationPID;
import org.usfirst.frc.team1619.robot2016.util.PID.DriveTranslationPID;

public class DriveForDistanceCommand extends Command {

  private double distance;
  private double targetPosition;
  private GenericTimer endTimer;
  private DriveTranslationPID driveTranslationPID;
  private DriveRotationPID driveRotationPID;

  public DriveForDistanceCommand(double distance) {
    super();

    this.distance = distance;
    targetPosition = 0;
    endTimer = new GenericTimer();
    driveTranslationPID = new DriveTranslationPID();
    driveRotationPID = new DriveRotationPID();
  }

  @Override
  protected void initialize() {
    targetPosition = sensorInput.getDriveRightEncoderPosition() + distance;
    endTimer.setDuration(1000);
    endTimer.start();

    driveRotationPID.reset();
    driveRotationPID.setTarget(sensorInput.getNavXHeading());
    driveRotationPID.setRotationKachig(false);

    driveTranslationPID.reset();
    driveTranslationPID.setTarget(targetPosition);
  }

  @Override
  protected void update() {
    robotOutput.arcadeDrive(driveTranslationPID.get(), driveRotationPID.get());

    if (Math.abs(driveTranslationPID.getError()) <= 
        Constants.DRIVE_PID_TRANSLATION_DEADZONE) {
      if(endTimer.isFinished()) {
        setFinished();
      }
    }
    else {
      endTimer.start();
    }
  }

  @Override
  public void pause() {
    robotOutput.arcadeDrive(0.0, 0.0);
  }

  @Override
  public void destruct() {
    robotOutput.arcadeDrive(0.0, 0.0);
    endTimer.reset();
  }

}
