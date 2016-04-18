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
    this(distance, 0);
  }

  public DriveTranslateCommand(double distance, int timeOut) {
    this(distance, Constants.AUTO_DRIVE_TRANSLATION_MAX_OUTPUT, timeOut);
  }

  public DriveTranslateCommand(double distance, double maxSpeed, int timeOut) {
    this(distance, maxSpeed, Constants.DRIVE_PID_TRANSLATION_DEADZONE, timeOut);
  }

  public DriveTranslateCommand(double distance, double maxSpeed,
    double tolerance, int timeOut) {
    super(timeOut);

    this.distance = distance;
    this.maxSpeed = maxSpeed;
    this.tolerance = tolerance;

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

    driveTranslationPID.reset();
    driveTranslationPID.setTarget(distance);
  }

  @Override
  protected void update() {
    driveTranslationPID.calculate();
    driveRotationPID.calculate();

    robotOutput.arcadeDrive(
      -MathUtility.constrain(driveTranslationPID.get(), maxSpeed, -maxSpeed),
      driveRotationPID.get());

    if (Math.abs(driveTranslationPID.getError()) <= tolerance) {
      if (endTimer.isFinished()
        || Math.abs((sensorInput.getDriveLeftEncoderVelocity()
          + sensorInput.getDriveRightEncoderVelocity())
          / 2) <= Constants.AUTO_DRIVE_TRANSLATION_STOP_SPEED) {
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
