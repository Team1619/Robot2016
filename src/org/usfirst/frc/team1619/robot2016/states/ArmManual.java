package org.usfirst.frc.team1619.robot2016.states;

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
    double armVelocity = driverInput.getOperatorY();
    robotOutput.setDartMotor(armVelocity);
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
