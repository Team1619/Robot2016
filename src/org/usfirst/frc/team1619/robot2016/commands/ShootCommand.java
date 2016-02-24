package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.framework.Command;
import org.usfirst.frc.team1619.robot2016.util.GenericTimer;

public class ShootCommand extends Command {

  private int targetSpeed;
  private boolean shooting;

  private GenericTimer shootingTimer;

  public ShootCommand(int targetSpeed, int timeout) {
    super(timeout);

    this.targetSpeed = targetSpeed;
  }

  @Override
  protected void initialize() {
    shooting = false;

    shootingTimer = new GenericTimer();
    shootingTimer.setDuration(1000);
  }

  @Override
  protected void update() {
    if (!shooting && sensorInput.getShooterEncoderVelocity() > targetSpeed) {
      shooting = true;
      shootingTimer.start();
    }
    else if (shooting && shootingTimer.isFinished()) {
      setFinished();
    }

    robotOutput.setShooterMotor(1.0);
    if (shooting) {
      robotOutput.setIntakeMotor(1.0);
    }
    else {
      robotOutput.setIntakeMotor(0.0);
    }
  }

  @Override
  protected void handleTimeout() {
    if (!shootingTimer.isStarted()) {
      shootingTimer.start();
    }
    else if (shootingTimer.isFinished()) {
      setFinished();
    }

    robotOutput.setShooterMotor(1.0);
    robotOutput.setIntakeMotor(1.0);
  }

  @Override
  public void pause() {
    shooting = false;

    robotOutput.setShooterMotor(0.0);
    robotOutput.setIntakeMotor(0.0);
  }

  @Override
  public void destruct() {
    robotOutput.setShooterMotor(0.0);
    robotOutput.setIntakeMotor(0.0);
  }

}
