package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.commands.ArmMoveToPositionCommand;
import org.usfirst.frc.team1619.robot2016.framework.SequencerState;

public class ArmMoveToDefault extends SequencerState {

  private static SubsystemID[] subsystems;

  static {
    subsystems = new SubsystemID[] {SubsystemID.UTILITY_ARM};
  }

  public ArmMoveToDefault() {
    super(subsystems);
  }

  @Override
  protected void addCommands() {
    add(new ArmMoveToPositionCommand(Constants.ARM_POSITION_DEFAULT));
    robotState.setArmToDefault(false);
  }

  @Override
  protected void destruct() {
    super.destruct();

    setFinished(false);
  }

  @Override
  public boolean isReadyForActive() {
    return (driverInput.getOperatorButton(Constants.OPERATOR_BUTTON_ARM_DEFAULT)
      || (robotState.getBallPresenceRisingEdge()
        && sensorInput.getDartPosition() < Constants.ARM_POSITION_DEFAULT)
      || robotState.getArmToDefault() || isActive())
      && !(Math.abs(driverInput.getOperatorY()) > Constants.JOYSTICK_DEADZONE)
      && !getFinished();
  }

}
