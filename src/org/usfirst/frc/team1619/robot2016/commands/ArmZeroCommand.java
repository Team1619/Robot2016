package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.framework.Command;

public class ArmZeroCommand extends Command {

  @Override
  protected void initialize() {
  }

  @Override
  protected void update() {
    if (!robotOutput.setDartMotorNonZeroed(Constants.ARM_ZERO_SPEED)) {
      setFinished();
    }
  }

  @Override
  public void pause() {
    robotOutput.setDartMotor(0.0);
  }

  @Override
  public void destruct() {
    robotOutput.setDartMotor(0.0);
  }

}
