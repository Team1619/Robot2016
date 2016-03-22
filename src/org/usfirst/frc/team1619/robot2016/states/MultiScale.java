package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.commands.ArmManualCommand;
import org.usfirst.frc.team1619.robot2016.commands.ScalerManualCommand;
import org.usfirst.frc.team1619.robot2016.framework.CommandGroup;
import org.usfirst.frc.team1619.robot2016.framework.SequencerState;

public class MultiScale extends SequencerState {

  private static SubsystemID[] subsystems;

  static {
    subsystems =
      new SubsystemID[] {SubsystemID.SCALER, SubsystemID.UTILITY_ARM};
  }

  public MultiScale() {
    super(subsystems);
  }

  @Override
  protected void addCommands() {
    CommandGroup scalerArmTimedGroup = new CommandGroup();
    scalerArmTimedGroup.add(new ArmManualCommand(Constants.ARM_SCALE_SPEED,
      Constants.ARM_SCALE_TIME));
    scalerArmTimedGroup.add(new ScalerManualCommand(
      Constants.SCALER_SCALE_SPEED, Constants.SCALER_SCALE_TIME));
    add(scalerArmTimedGroup);
  }

  @Override
  public boolean isReadyForActive() {
    return (driverInput.getOperatorButton(Constants.OPERATOR_BUTTON_SCALER_SCALE)
      || isActive())
      && !getFinished();
  }
}
