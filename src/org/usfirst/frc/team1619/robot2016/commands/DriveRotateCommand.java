package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.framework.Command;
import org.usfirst.frc.team1619.robot2016.util.GenericTimer;
import org.usfirst.frc.team1619.robot2016.util.PID.DriveRotationPID;

public class DriveRotateCommand extends Command {

  private double angle;
  private double tolerance;
  private GenericTimer endTimer;
  private DriveRotationPID rotationPID;

  public DriveRotateCommand(double angle) {
    this(angle, 0);
  }

  public DriveRotateCommand(double angle, int timeout) {
    this(angle, Constants.AUTO_DRIVE_ROTATION_TOLERANCE, timeout);
  }

  public DriveRotateCommand(double angle, double tolerance, int timeout) {
    this(angle, tolerance, Constants.DRIVE_PID_ROTATION_DEADZONE, timeout);
  }

  public DriveRotateCommand(double angle, double tolerance, double deadBand,
    int timeout) {
    super(timeout);

    this.angle = angle;
    this.tolerance = tolerance;

    endTimer = new GenericTimer();
    rotationPID = new DriveRotationPID();
    rotationPID.setDeadBand(deadBand);
  }

  @Override
  protected void initialize() {
    endTimer.setDuration(Constants.AUTO_DRIVE_ROTATION_DEADTIME);
    endTimer.start();

    rotationPID.reset();
    rotationPID.setTarget(angle);
  }

  @Override
  protected void update() {
    rotationPID.calculate();
    robotOutput.arcadeDrive(0, rotationPID.get());

    if (Math.abs(rotationPID.getError()) <= tolerance) {
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
