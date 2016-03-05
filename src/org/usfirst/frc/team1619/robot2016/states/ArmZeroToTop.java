package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.framework.RobotState;
import org.usfirst.frc.team1619.robot2016.framework.State;

public class ArmZeroToTop extends State {

  private RobotState robotState;

  private static SubsystemID[] subsystems;

  static {
    subsystems = new SubsystemID[] {SubsystemID.UTILITY_ARM};
  }

  public ArmZeroToTop() {
    super(subsystems);

    robotState = RobotState.getInstance();
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void update() {
    robotOutput.setDartMotorNonZeroed(0.75);

    if (sensorInput.getUpperHallEffect()) {
      setFinished();
    }
  }

  @Override
  protected void destruct() {
    robotOutput.setDartMotorNonZeroed(0.0);
  }

  @Override
  protected void pause() {
    robotOutput.setDartMotorNonZeroed(0.0);
  }

  @Override
  public boolean isReadyForActive() {
    return (!robotState.getArmZeroed()
      || driverInput.getOperatorButton(Constants.OPERATOR_BUTTON_ZERO_DART))
      || !getFinished();
  }

}
