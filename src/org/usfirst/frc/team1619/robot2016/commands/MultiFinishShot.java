package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.framework.Command;
import org.usfirst.frc.team1619.robot2016.util.GenericTimer;
import org.usfirst.frc.team1619.robot2016.util.PID.DriveRotationPID;

public class MultiFinishShot extends Command {

  private int targetShooterSpeed;
  private boolean shooting;

  private DriveRotationPID driveRotationPID;

  private GenericTimer runningIntakeOutTimer;

  public MultiFinishShot(double distance) {
    targetShooterSpeed = getTargetShooterSpeedForDistance(distance);

    driveRotationPID = new DriveRotationPID();

    runningIntakeOutTimer = new GenericTimer();
    runningIntakeOutTimer.setDuration(Constants.SHOOTING_INTAKE_OUT_TIME);
  }

  @Override
  protected void initialize() {
    driveRotationPID.reset();
    driveRotationPID.setTarget(0.0);

    runningIntakeOutTimer.reset();
    runningIntakeOutTimer.start();

    shooting = false;
  }

  @Override
  protected void update() {
    driveRotationPID.calculate();
    robotOutput.arcadeDrive(0.0, driveRotationPID.get());

    if (shooting || Math.abs(
      driveRotationPID.getError()) < Constants.DRIVE_PID_ROTATION_DEADZONE) {
      robotOutput.setShooterMotor(1.0);

      if (shooting
        || sensorInput.getShooterEncoderVelocity() > targetShooterSpeed) {
        shooting = true;
        robotOutput.setIntakeMotor(Constants.SHOOTER_SHOOT_SPEED);

        if (runningIntakeOutTimer.isFinished()) {
          setFinished();
        }
      }
      else {
        runningIntakeOutTimer.start();
      }
    }
    else {
      robotOutput.setShooterMotor(0.5);
      runningIntakeOutTimer.start();
    }
  }

  @Override
  public void pause() {
    robotOutput.arcadeDrive(0.0, 0.0);
    robotOutput.setShooterMotor(0.0);
  }

  @Override
  public void destruct() {
    robotOutput.arcadeDrive(0.0, 0.0);
    robotOutput.setShooterMotor(0.0);
  }

  private int getTargetShooterSpeedForDistance(double distance) {
    return 27500;
  }

}