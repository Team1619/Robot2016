package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.framework.State;
import org.usfirst.frc.team1619.robot2016.subsystems.ArmPID;

public class ArmMoveToAngle extends State {

  private static SubsystemID[] subsystems;

  protected ArmPID armPID;

  static {
    subsystems = new SubsystemID[] {SubsystemID.UTILITY_ARM};
  }

  public ArmMoveToAngle() {
    super(subsystems);

    armPID = ArmPID.getInstance();
  }

  @Override
  protected void setup() {
    armPID.setTarget(0.0);
  }

  @Override
  protected void execute() {
    double voltage = armPID.get();

    robotOutput.setDartMotor(voltage);
  }

  @Override
  protected void pause() {
    robotOutput.setDartMotor(0.0);
    armPID.reset();
  }

  @Override
  protected void destroy() {
    robotOutput.setDartMotor(0.0);
    armPID.resetIntegral();
  }

  @Override
  public boolean isReadyForActive() {
    return driverInput.getOperatorButton(4);
  }

}
