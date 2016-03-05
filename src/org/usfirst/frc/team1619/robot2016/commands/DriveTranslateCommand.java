package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.framework.Command;
import org.usfirst.frc.team1619.robot2016.util.GenericTimer;
import org.usfirst.frc.team1619.robot2016.util.MathUtility;
import org.usfirst.frc.team1619.robot2016.util.PID.DriveRotationPID;
import org.usfirst.frc.team1619.robot2016.util.PID.DriveTranslationPID;

public class DriveTranslateCommand extends Command {

  private double distance;
  private double maxSpeed;
  private double tolerance;

  private GenericTimer endTimer;
  private DriveTranslationPID driveTranslationPID;
  private DriveRotationPID driveRotationPID;

  public DriveTranslateCommand(double distance) {
    this(distance, Constants.AUTO_DRIVE_TRANSLATION_MAX_OUTPUT);
  }
  public DriveTranslateCommand(double distance, double maxSpeed) {
    this(distance, maxSpeed, Constants.DRIVE_PID_TRANSLATION_DEADZONE);
  }
  public DriveTranslateCommand(double distance, double maxSpeed, double tolerance) {
    super();

    this.distance = distance;
    endTimer = new GenericTimer();
    driveTranslationPID = new DriveTranslationPID();
    driveRotationPID = new DriveRotationPID();
  }

  @Override
  protected void initialize() {
    endTimer.setDuration(Constants.AUTO_DRIVE_TRANSLATION_DEADTIME);
    endTimer.start();

    driveRotationPID.reset();
    driveRotationPID.setTarget(0);
    driveRotationPID.setKachigBand(0);

    driveTranslationPID.reset();
    driveTranslationPID.setTarget(distance);
  }

  @Override
  protected void update() {
    driveTranslationPID.calculate();
    driveRotationPID.calculate();

    robotOutput.arcadeDrive(-MathUtility.constrain(driveTranslationPID.get(),
      maxSpeed, -maxSpeed), driveRotationPID.get());

    if (Math.abs(driveTranslationPID.getError()) <= tolerance) {
      if (endTimer.isFinished()) {
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
