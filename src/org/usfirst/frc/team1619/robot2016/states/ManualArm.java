package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.subsystems.SubsystemID;

public class ManualArm extends State {

  private static SubsystemID[] subsystems;

  static {
    subsystems = new SubsystemID[] {SubsystemID.UTILITY_ARM};
  }

  public ManualArm() {
    super(subsystems);
  }

  @Override
  protected void setup() {
  }

  @Override
  protected void execute() {
    double armVelocity = driverInput.getOperatorY();
    
    robotOutput.setDartMotor(armVelocity);
  }

  @Override
  protected void pause() {
    robotOutput.setDartMotor(0.0);
  }

  @Override
  protected void destroy() {
    robotOutput.setDartMotor(0.0);
  }

  @Override
  public boolean isReadyForActive() {
    return true;
  }

}
