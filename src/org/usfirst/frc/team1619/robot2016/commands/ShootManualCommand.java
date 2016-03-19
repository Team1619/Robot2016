package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.framework.Command;

public class ShootManualCommand extends Command {
  double speed;

  public ShootManualCommand(double speed, int timeOut) {
    super(timeOut);
    this.speed = speed;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void update() {
    robotOutput.setShooterMotor(speed);
  }

  @Override
  public void pause() {
    // TODO Auto-generated method stub

  }

  @Override
  public void destruct() {
    robotOutput.setShooterMotor(0);
  }

}
