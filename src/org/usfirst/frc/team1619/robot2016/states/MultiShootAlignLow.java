package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;

public class MultiShootAlignLow extends MultiShootAlign {

  public MultiShootAlignLow() {
    super();
  }

  @Override
  protected int getSpeedTarget() {
    return Constants.SHOOTER_SHOOT_SPEED_TARGET_LOW;
  }

  @Override
  public boolean isReadyForActive() {
    return driverInput.getDriverButton(Constants.DRIVER_BUTTON_SHOOT_LOW);
  }

}
