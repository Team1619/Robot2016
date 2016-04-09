package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.RobotState;
import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.commands.ArmZeroCommand;
import org.usfirst.frc.team1619.robot2016.framework.State;

public class ArmZeroToTop extends State {

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
  protected void initialize() {
    zeroCommand = new ArmZeroCommand();

    zeroCommand.initializeCommand();
  }

  @Override
  protected void update() {
    zeroCommand.updateCommand();
  }

  @Override
  protected void destruct() {
    zeroCommand.destruct();
  }

  @Override
  protected void pause() {
    zeroCommand.pause();
  }

  @Override
  public boolean isReadyForActive() {
    return (!robotState.getArmZeroed()
      || driverInput.getOperatorButton(Constants.OPERATOR_BUTTON_ZERO_DART));
  }

}
