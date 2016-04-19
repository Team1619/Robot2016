package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.commands.CrossDefenseCommand;
import org.usfirst.frc.team1619.robot2016.framework.SequencerState;

public class AutoLowBar extends SequencerState {

  private static SubsystemID[] subsystems;

  static {
    subsystems = new SubsystemID[] {SubsystemID.INTAKE, SubsystemID.SHOOTER,
      SubsystemID.DRIVE_TRAIN, SubsystemID.UTILITY_ARM};
  }

  public AutoLowBar() {
    super(subsystems);
  }

  @Override
  protected void addCommands() {
    // double angle = sensorInput.getNavXHeading();
    //
    // add(new ArmZeroCommand());
    // add(new CrossRockWallCommand(40.0));
    // add(new DriveRotateToAbsoluteCommand(angle, 1000));
    // add(new LaneToHighGoalCommand(5, 15.0));
    // add(new FindContourCommand(true));
    // add(new ShootFromAnywhereCommand(2));
    // add(new HighGoalToLaneCommand(angle, 5, 20.0));
    // add(new CrossRockWallCommand(0.0));

    add(new CrossDefenseCommand(Defenses.LOW_BAR, Constants.AUTO_DISTANCE_LINE_TO_PLATFORM));
  }

  @Override
  protected void destruct() {
    super.destruct();

    setFinished();
  }
}
