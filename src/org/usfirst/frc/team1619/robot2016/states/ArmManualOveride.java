package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;

public class ArmManualOveride extends ArmManual {
  @Override
  public boolean isReadyForActive() {
    return Math.abs(driverInput.getOperatorY()) >= Constants.JOYSTICK_DEADZONE;
  }
}
