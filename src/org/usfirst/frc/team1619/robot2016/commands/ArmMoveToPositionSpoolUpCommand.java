package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;

public class ArmMoveToPositionSpoolUpCommand extends ArmMoveToPositionCommand {

  public ArmMoveToPositionSpoolUpCommand(double position, int timeOut) {
    super(position, timeOut);
  }

  @Override
  protected void update() {
    super.update();
    robotOutput.setShooterMotor(Constants.SHOOTER_SPOOL_UP_SPEED);
  }

  @Override
  public void pause() {
    super.pause();
    robotOutput.setShooterMotor(0);
  }

  @Override
  public void destruct() {
    super.destruct();
    robotOutput.setShooterMotor(0);
  }

}
