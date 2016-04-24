package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.RobotState;
import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.commands.ArmZeroCommand;
import org.usfirst.frc.team1619.robot2016.framework.SequencerState;

public class ArmZeroToTop extends SequencerState {

  private RobotState robotState;

  private static SubsystemID[] subsystems;

  static {
    subsystems = new SubsystemID[] {SubsystemID.UTILITY_ARM};
  }

  ArmZeroCommand zeroCommand;

  public ArmZeroToTop() {
    super(subsystems);

    robotState = RobotState.getInstance();
  }

  @Override
  protected void addCommands() {
    add(new ArmZeroCommand());

    if (!robotState.getArmZeroed()) {
      robotState.setArmToDefault(true);
    }
  }

  @Override
  public boolean isReadyForActive() {
    return (!robotState.getArmZeroed()
      || driverInput.getOperatorButton(Constants.OPERATOR_BUTTON_ZERO_DART));
  }

}
