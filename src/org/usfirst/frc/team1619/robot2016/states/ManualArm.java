package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.IO.DriverInput;
import org.usfirst.frc.team1619.robot2016.IO.RobotOutput;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;
import org.usfirst.frc.team1619.robot2016.subsystems.SubsystemID;

public class ManualArm extends State {

  private static SubsystemID[] subsystems;

  private DriverInput driverInput;
  private RobotOutput robotOutput;
  private SensorInput sensorInput;

  static {
    subsystems = new SubsystemID[] {SubsystemID.UTILITY_ARM};
  }

  public ManualArm() {
    super(subsystems);

    driverInput = DriverInput.getInstance();
    robotOutput = RobotOutput.getInstance();
    sensorInput = SensorInput.getInstance();
  }

  @Override
  protected void setup() {
  }

  @Override
  protected void execute() {
    double armVelocity = driverInput.getOperatorY();

    if ((armVelocity > 0.0 && sensorInput.getUpperHallEffect())
      || (armVelocity < 0.0 && sensorInput.getLowerHallEffect())) {
      armVelocity = 0.0;
    }

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
