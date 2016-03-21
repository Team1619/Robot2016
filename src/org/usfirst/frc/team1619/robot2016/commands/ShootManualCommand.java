package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.framework.Command;

public class ShootManualCommand extends Command {
  double speed;

  /**
   * Use this command with 0 timeout in order to get a passive shooter motor
   * command.
   * 
   * @param speed
   * @param timeOut
   */
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
  }

  @Override
  public void destruct() {
    robotOutput.setShooterMotor(0);
  }

}
