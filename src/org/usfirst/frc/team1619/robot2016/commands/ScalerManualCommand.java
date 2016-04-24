package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.framework.Command;

public class ScalerManualCommand extends Command {
  double speed;

  /**
   * Use this command with 0 timeout in order to get a passive arm motor
   * command.
   * 
   * @param speed
   * @param timeOut
   */
  public ScalerManualCommand(double speed, int timeOut) {
    super(timeOut);
    this.speed = speed;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void update() {
    robotOutput.setScalerMotor(speed);
  }

  @Override
  public void pause() {
    robotOutput.setScalerMotor(0.0);
  }

  @Override
  public void destruct() {
    robotOutput.setScalerMotor(0.0);
  }
}
