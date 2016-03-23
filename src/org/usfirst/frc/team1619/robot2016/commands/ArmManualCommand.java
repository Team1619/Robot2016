package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.framework.Command;

public class ArmManualCommand extends Command {
  double speed;

  /**
   * Use this command with 0 timeout in order to get a passive arm motor
   * command.
   * 
   * @param speed
   * @param timeOut
   */
  public ArmManualCommand(double speed, int timeOut) {
    super(timeOut);
    this.speed = speed;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void update() {
    robotOutput.setDartMotorNonZeroed(speed);
  }

  @Override
  public void pause() {
    robotOutput.setDartMotorNonZeroed(0);
  }

  @Override
  public void destruct() {
    robotOutput.setDartMotorNonZeroed(0);
  }
}
