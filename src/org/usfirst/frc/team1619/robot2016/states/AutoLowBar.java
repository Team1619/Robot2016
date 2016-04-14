package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.commands.ArmManualCommand;
import org.usfirst.frc.team1619.robot2016.commands.ArmZeroCommand;
import org.usfirst.frc.team1619.robot2016.commands.CrossRockWallCommand;
import org.usfirst.frc.team1619.robot2016.commands.DriveRotateCommand;
import org.usfirst.frc.team1619.robot2016.commands.DriveRotateToAbsoluteCommand;
import org.usfirst.frc.team1619.robot2016.commands.DriveRotateToHighGoalCommand;
import org.usfirst.frc.team1619.robot2016.commands.HighGoalToLaneCommand;
import org.usfirst.frc.team1619.robot2016.commands.LaneToHighGoalCommand;
import org.usfirst.frc.team1619.robot2016.commands.PauseCommand;
import org.usfirst.frc.team1619.robot2016.framework.CommandGroup;
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
    double angle = sensorInput.getNavXHeading();

    add(new ArmZeroCommand());
    add(new CrossRockWallCommand(40.0));
    add(new DriveRotateToAbsoluteCommand(angle, 1000));
    add(new LaneToHighGoalCommand(5, 15.0));
    add(new PauseCommand(250));
    add(new DriveRotateToHighGoalCommand());
    add(new HighGoalToLaneCommand(angle, 5, 10.0));
    add(new CrossRockWallCommand(0.0));

    CommandGroup prepareForIntake = new CommandGroup();
    prepareForIntake.add(new DriveRotateToAbsoluteCommand((angle + 180.0 + 15.0) % 360));
    prepareForIntake.add(new ArmManualCommand(-1.0, 0));
    
    add(prepareForIntake);
  }

  @Override
  protected void destruct() {
    super.destruct();

    setFinished();
  }
}
