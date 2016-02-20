package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.IO.ArmPID;
import org.usfirst.frc.team1619.robot2016.subsystems.SubsystemID;

public class ArmToAngle extends State {

  private static SubsystemID[] subsystems;

  protected ArmPID armPID;

  static {
    subsystems = new SubsystemID[] {SubsystemID.UTILITY_ARM};
  }

  public ArmToAngle() {
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

    if ((voltage > 0.0 && sensorInput.getUpperHallEffect())
      || (voltage < 0.0 && sensorInput.getLowerHallEffect())) {
      voltage = 0.0;
    }

    robotOutput.setDartMotor(voltage);
  }

  @Override
  protected void pause() {
    robotOutput.setDartMotor(0.0);
    armPID.resetError();
  }

  @Override
  protected void destroy() {
    robotOutput.setDartMotor(0.0);
    armPID.resetError();
  }

  @Override
  public boolean isReadyForActive() {
    return driverInput.getOperatorButton(4);
  }

}
