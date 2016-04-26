package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.framework.State;

public class ArmManual extends State {

  private static SubsystemID[] subsystems;

  static {
    subsystems = new SubsystemID[] {SubsystemID.UTILITY_ARM};
  }

  public ArmManual() {
    super(subsystems);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void update() {
    if (driverInput.getOperatorButton(Constants.OPERATOR_BUTTON_ARM_TO_TOP)) {
      robotOutput.setDartMotorIgnoreSensors(Constants.ARM_IGNORE_SENSORS_SPEED);
    }
    else {
      double armVelocity = driverInput.getOperatorY();
      robotOutput.setDartMotor(armVelocity);
    }
  }

  @Override
  protected void pause() {
    robotOutput.setDartMotor(0.0);
  }

  @Override
  protected void destruct() {
    robotOutput.setDartMotor(0.0);
  }

  @Override
  public boolean isReadyForActive() {
    return true;
  }

}
