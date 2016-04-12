package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.commands.ArmMoveToPositionCommand;
import org.usfirst.frc.team1619.robot2016.commands.ArmZeroCommand;
import org.usfirst.frc.team1619.robot2016.commands.DriveTranslateCommand;
import org.usfirst.frc.team1619.robot2016.framework.SequencerState;

public class AutoTwoBall extends SequencerState {

  private static SubsystemID[] subsystems;

  static {
    subsystems = new SubsystemID[] {SubsystemID.INTAKE, SubsystemID.SHOOTER,
      SubsystemID.DRIVE_TRAIN, SubsystemID.UTILITY_ARM};
  }

  public AutoTwoBall() {
    super(subsystems);
  }

  @Override
  protected void addCommands() {
    add(new ArmZeroCommand());
    add(new ArmMoveToPositionCommand(Constants.ARM_POSITION_DEFAULT - 1.25,
      2000));
    add(new DriveTranslateCommand(-25.0, 1500));
  }

}
